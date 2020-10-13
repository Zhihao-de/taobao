package com.leo.taobao.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.ref.WeakReference;

/**
 * JWT认证令牌的秘钥匹配器
 */
public class JwtCredentialsMatcher implements CredentialsMatcher {

    @Autowired
    private JwtManipulator jwtManipulator;

    /**
     * 匹配秘钥
     *
     * @param authcToken JWT认证令牌
     * @param info       认证信息
     * @return 匹配则为<code>true</code>，否则为<code>false</code>
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        AppCredential ac = (AppCredential) info.getCredentials();
        for (String credential : ac.getCredentials().values()) {
            if (null == credential || 0 == credential.length())
                continue;
            if (doSingleCredentialsMatch(authcToken, info, credential))
                return true;
        }
        return false;
    }

    private boolean doSingleCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info, String credential) {
        AppCredential ac = (AppCredential) info.getCredentials();
        JsonWebToken token = (JsonWebToken) authcToken;
        token.setThrowable(null);
        try {
            String key = PasswordUtils.encrypt(credential + ac.getPassphrase());
            jwtManipulator.verify(token.getToken(), ac.getUserId(), ac.getAppId(), key);
            return true;
        } catch (Throwable ex) {
            if (ex instanceof TokenExpiredException) {
                token.setAppCredential(new WeakReference<>(ac));
            }
            token.setThrowable(ex);
        }
        return false;
    }
}
