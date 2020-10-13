package com.leo.taobao.security;


import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.error.RuntimeServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * JDBC数据库用户身份认证域
 */
public class BasicRealm extends UnifiedAuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken authcToken) {
        return authcToken instanceof BasicAuthenticationToken;
    }

    /**
     * 获取认证信息
     *
     * @param authcToken <code>BasicAuthenticationToken</code>认证令牌
     * @return 若可以使用提供的用户名和密码完成数据库认证，则返回认证信息对象，否则为<code>null</code>。
     * 认证信息对象将被缓存到 EhCache，缓存名为 globalAuthenticationCache，缓存键为 userId@appId
     * @throws AuthenticationException 认证异常，例如 SQLException 等
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        AppUsernamePasswordToken token = (AppUsernamePasswordToken) authcToken;
        String number = token.getUsername();
        String password = new String(token.getPassword());
        User query = new User(number);
        query.setPassword(password);
        User user = userMapper.queryUserByPass(query);
        if (null == user) {
            token.setThrowable(new RuntimeServiceException(ErrCodes.ABSENCE_OR_MISMATCH_ERROR, "illegal_username_or_password"));
            return null;
        }
        return getCacheableAuthenticationInfo(token, user);
    }
}
