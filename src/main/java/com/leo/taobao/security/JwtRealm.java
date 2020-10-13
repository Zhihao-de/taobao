package com.leo.taobao.security;


import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.error.RuntimeServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * JWT认证域
 */
public class JwtRealm extends UnifiedAuthorizingRealm {

    public JwtRealm() {
        setAuthenticationCacheName("globalAuthenticationCache");
    }

    @Override
    public boolean supports(AuthenticationToken authcToken) {
        return authcToken instanceof JsonWebToken;
    }

    /**
     * 获取认证信息
     *
     * <p>认证信息从<code>JdbcRealm</code>存储的缓存中获取
     * 即首先通过SignIn服务签入，认证通过后由<code>JdbcRealm</code>写入签入缓存。
     * 此后所有的Web API访问，均通过 JwtAccessFilter 进行处理。
     * 在认证时，通过此方法提取JWT令牌中的用户身份标识（即 userId@appId），查询签入缓存。
     * 若命中缓存，则获取响应的<code>AuthenticationInfo</code>对象，并进行秘钥认证。
     * 若无缓存，则认为用户未登录，当前请求被拒绝。</p>
     *
     * <p>此方法正确运行的前提是为<code>JdbcRealm</code>配置了开启AuthenticationCache。
     * 为<code>JwtRealm</code>配置了关闭缓存。
     * </p>
     *
     * @param authcToken <code>JsonWebToken</code>认证令牌
     * @return 若可以使用提供的JWT令牌完成数据库认证，则返回认证信息对象，否则为<code>null</code>。
     * 认证信息对象将被不缓存到 EhCache
     * @throws AuthenticationException 认证异常，例如 SQLException 等
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        JsonWebToken token = (JsonWebToken) authcToken;
        CacheManager cacheManager = this.getCacheManager();
        if (null == cacheManager)
            return null;

        String cacheName = this.getAuthenticationCacheName();
        Cache<Object, AuthenticationInfo> authenticationCache = cacheManager.getCache(cacheName);
        if (null == authenticationCache)
            return null;

        AuthenticationInfo info = authenticationCache.get(authcToken.getPrincipal());
        if (null == info)
            token.setThrowable(new RuntimeServiceException(ErrCodes.UNAUTHORIZED_ERROR, "unauthorized"));

        return info;
    }
}
