package com.leo.taobao.security;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.lang.ref.WeakReference;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AppUsernamePasswordToken extends UsernamePasswordToken {
    private String appId;
    private long timestamp;
    private transient WeakReference<AppCredential> appCredential;
    private transient Throwable throwable;

    AppUsernamePasswordToken(String username, String password, String appId) {
        super(username, (password != null ? password.toCharArray() : null), false, null);
        this.appId = appId;
    }
}
