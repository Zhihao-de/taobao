package com.leo.taobao.security;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.error.RuntimeServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 根据JWT认证令牌进行身份验证和授权的过滤器
 */
public class JwtAccessFilter extends JwtFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAccessFilter.class);

    public JwtAccessFilter() {
        setAuthzScheme("BEARER ");
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);
        HttpServletResponse res = WebUtils.toHttp(response);
        res.setHeader(HttpHeaderKeys.ACCESS_CONTROL_ALLOW_ORIGIN, req.getHeader(HttpHeaderKeys.ORIGIN));
        res.setHeader(HttpHeaderKeys.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST,OPTIONS,PUT,PATCH,DELETE");
        res.setHeader(HttpHeaderKeys.ACCESS_CONTROL_ALLOW_HEADERS, req.getHeader(HttpHeaderKeys.ACCESS_CONTROL_REQUEST_HEADERS));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 创建JWT认证令牌。
     *
     * @param request  当前请求
     * @param response 请求响应
     * @return 返回<code>JsonWebToken</code>类型的认证令牌。
     * 该方法从请求头中匹配 Authorization: Bear XXX 模式，从中提取XXX部分作为JWT字符串，解析后构造JWT认证令牌。
     * 当解析因令牌格式有误时，将返回<code>null</code>。
     * 当返回值为<code>null</code>时，过滤器将拒绝当前请求，并返回 401 错误。
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String jwt = getAuthzHeader(request).substring(getAuthzScheme().length());
        return super.createToken(jwt);
    }

    /**
     * 签入失败时的处理
     *
     * @param authcToken JWT认证令牌
     * @param ex         异常对象
     * @param request    当前请求
     * @param response   请求响应
     * @return 当JWT认证令牌过期又续签成功时，返回<code>true</code>，否则返回<code>false</code>。
     * 当返回<code>false</code>时，错误信息写入响应缓冲，
     * 并最终以<code>ResponseResult</code>对象的形式返回JSON，这与Controller的行为一致。
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken authcToken, AuthenticationException ex,
                                     ServletRequest request, ServletResponse response) {
        int errCode;
        String errMsg;
        JsonWebToken token = (JsonWebToken) authcToken;
        if (null != token.getThrowable()) {
            Throwable innerException = token.getThrowable();
            errMsg = innerException.getMessage();
            if (innerException instanceof SignatureVerificationException) {
                // 传入JWT的签名不正确，这可能是因为使用了重新签入或续签之前的令牌（该令牌逻辑上已作废），或者使用了伪造令牌
                errCode = ErrCodes.TOKEN_VERIFICATION_ERROR;
            } else if (innerException instanceof TokenExpiredException) {
                // 令牌过期
                HttpServletRequest req = WebUtils.toHttp(request);
                if (req.getRequestURI().toLowerCase(Locale.ENGLISH).endsWith("/v2/auth/refresh")) {
                    request.setAttribute("appCredential", token.getAppCredential());
                    return true;
                }
                errCode = ErrCodes.EXPIRED_TOKEN_ERROR;
            } else if (innerException instanceof JWTDecodeException) {
                // JWT令牌编码有误，这可能是使用了伪造令牌
                // 应该不会走到这里，因为在 filter.createToken() 时进行过异常处理
                errCode = ErrCodes.TOKEN_ENCODING_ERROR;
            } else if (innerException instanceof RuntimeServiceException) {
                errCode = ((RuntimeServiceException) innerException).getCode();
            } else {
                errCode = ErrCodes.ACCESS_CONTROL_ERROR;
            }
        } else {
            Throwable cause = ex.getCause();
            errMsg = (null != cause ? cause.getMessage() : ex.getMessage());
            if (cause instanceof RuntimeServiceException) {
                errCode = ((RuntimeServiceException) cause).getCode();
            } else {
                errCode = ErrCodes.ACCESS_CONTROL_ERROR;
            }
        }
        flushError(errCode, errMsg, response);
        return super.onLoginFailure(authcToken, ex, request, response);
    }
}
