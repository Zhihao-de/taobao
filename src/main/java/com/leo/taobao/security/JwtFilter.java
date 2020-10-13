package com.leo.taobao.security;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.leo.taobao.util.ResponseResult;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
public abstract class JwtFilter extends BasicHttpAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(cn.ac.iscas.security.JwtFilter.class);

    @Autowired
    protected JwtManipulator jwtManipulator;

    /**
     * 是否允许当前请求继续下一步处理。
     *
     * @param request     当前请求
     * @param response    请求响应
     * @param mappedValue 权限映射
     * @return 该方法总是返回<code>false</code>，表示所有请求都被直接拒绝，因此需要进行身份认证。
     * 身份认证由<code>subject.login(token)</code>方法完成，该方法在父类的<code>executeLogin()</code>方法中调用。
     * 当login成功时，执行<code>onLoginSuccess</code>方法，即进入下一步处理。
     * 当login失败时，执行<code>onLoginFailure</code>方法，进行错误处理。
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    /**
     * 创建JWT认证令牌
     *
     * @param jwt JWT令牌描述
     * @return <code>JsonWebToken</code>类型的JWT认证令牌
     */
    AuthenticationToken createToken(String jwt) {
        JsonWebToken token;
        try {
            DecodedJWT decoder = jwtManipulator.decode(jwt);
            String userId = decoder.getSubject();
            String appId = decoder.getAudience().get(0);
            token = new JsonWebToken(jwt, userId, appId);
        } catch (JWTDecodeException ex) {
            token = new cn.ac.iscas.security.JsonWebToken();
        }
        return token;
    }

    /**
     * 向请求放输出错误结果
     *
     * @param errCode
     * @param errMsg
     * @param response
     */
    void flushError(int errCode, String errMsg, @NotNull ServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            ResponseResult.error(errCode, errMsg).write(writer);
        } catch (IOException e) {
            log.error(errMsg, e);
        }
    }
}
