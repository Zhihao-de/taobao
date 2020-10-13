package com.leo.taobao.security;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.leo.taobao.error.ErrCodes;
import org.apache.shiro.web.util.WebUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.ref.WeakReference;

/**
 *
 */
public class JwtRefreshFilter extends JwtFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAccessFilter.class);

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String authzHeader = this.getAuthzHeader(request);
        return authzHeader != null && 0 < authzHeader.length();
    }

    @Override
    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(HttpHeaderKeys.REFRESH_TOKEN);
    }

    @Override
    protected boolean onAccessDenied(@NotNull ServletRequest request, ServletResponse response) {
        int errCode;
        String errMsg;
        Object ref = request.getAttribute("appCredential");
        if (null != ref) {
            @SuppressWarnings("unchecked")
            AppCredential ac = ((WeakReference<AppCredential>) ref).get();
            assert null != ac;
            String jwt = getAuthzHeader(request);
            String userId = ac.getUserId();
            String appId = ac.getAppId();
            String credential = ac.getPrimaryCredential();
            try {
                jwtManipulator.verify(jwt, userId, appId, credential);
                return true;
            } catch (SignatureVerificationException ex) {
                errCode = ErrCodes.TOKEN_VERIFICATION_ERROR;
                errMsg = "invalid_long_term_token";
            } catch (TokenExpiredException ex) {
                errCode = ErrCodes.EXPIRED_TOKEN_ERROR;
                errMsg = "expired_long_term_token";
            } catch (JWTDecodeException ex) {
                errCode = ErrCodes.TOKEN_ENCODING_ERROR;
                errMsg = "illegal_long_term_token";
            }
        } else {
            errCode = ErrCodes.TOKEN_REFRESHING_ERROR;
            errMsg = "unauthorized";
        }
        flushError(errCode, errMsg, response);
        return false;
    }
}
