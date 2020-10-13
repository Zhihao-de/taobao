package com.leo.taobao.security;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;
import org.jetbrains.annotations.Contract;

import java.lang.ref.WeakReference;

/**
 * 表示JWT认证令牌
 */
@Data
public class JsonWebToken implements AuthenticationToken {

    private static final long serialVersionUID = -4481576224230675022L;

    private String token = "";
    private String userId = "";
    private String appId = "";
    private transient Throwable throwable;
    private transient WeakReference<AppCredential> appCredential;

    @Contract(pure = true)
    public JsonWebToken() {
    }

    /**
     * 构造JWT认证令牌
     *
     * @param token  令牌内容
     * @param userId 用户标识符
     * @param appId  设备标识符
     */
    @Contract(pure = true)
    public JsonWebToken(String token, String userId, String appId) {
        this.token = token;
        this.userId = userId;
        this.appId = appId;
    }

    /**
     * 获得令牌身份
     *
     * @return 在系统内描述用户身份的字符串，例如 13800138000@device1
     */
    @Override
    public Object getPrincipal() {
        return this.userId;
    }

    /**
     * 获得令牌秘钥
     *
     * @return 返回一个令牌秘钥，用于和<code>AuthenticationInfo</code>中的秘钥进行比对。
     * 但对于JWT认证令牌，该秘钥总是<code>null</code>。
     * 在比较秘钥时，由<code>JwtCredentialMatcher</code>处理。
     */
    @Override
    public Object getCredentials() {
        return null;
    }
}
