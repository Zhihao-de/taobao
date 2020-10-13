package com.leo.taobao.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class JwtManipulator {

    private String issuer = "speaknow.com.cn";
    private long shortTtl = 60000L;
    private long longTtl = 120000L;

    /**
     * @param subject
     * @param audience
     * @param key
     * @param issuedAt
     * @return
     */
    public String signShortTermToken(String subject, String audience, String key, long issuedAt) {
        return sign(subject, audience, key, issuedAt, shortTtl);
    }

    /**
     * @param subject
     * @param audience
     * @param key
     * @param issuedAt
     * @return
     */
    public String signLongTermToken(String subject, String audience, String key, long issuedAt) {
        return sign(subject, audience, key, issuedAt, longTtl);
    }

    private String sign(String subject, String audience, String key, long issuedAt, long ttl) {
        return JWT.create()
                .withIssuer(issuer)
                .withJWTId(String.valueOf(System.currentTimeMillis()))
                .withSubject(subject)
                .withAudience(audience)
                .withIssuedAt(new Date(issuedAt))
                .withNotBefore(new Date(issuedAt))
                .withExpiresAt(new Date(issuedAt + ttl))
                .sign(Algorithm.HMAC256(key));
    }

    /**
     * 解析JWT令牌.
     *
     * @param token JWT令牌
     * @return 解码后的信息对象
     * @throws JWTDecodeException
     */
    public DecodedJWT decode(String token) throws JWTDecodeException {
        return JWT.decode(token);
    }

    /**
     * 验证JWT令牌.
     *
     * @param token    JWT令牌
     * @param subject  用户身份
     * @param audience 接受对象，即设备标识（ip、imei等）
     * @param key      签发秘钥
     */
    public void verify(String token, String subject, String audience, String key) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(key))
                .withIssuer(issuer)
                .withSubject(subject)
                .withAudience(audience)
                .acceptLeeway(60)   // this is for concurrent access (unit: second)
                .build();
        verifier.verify(token);
    }
}
