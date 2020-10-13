package com.leo.taobao.security;

public class BasicAuthenticationToken extends AppUsernamePasswordToken {

    public BasicAuthenticationToken(String username, String password, String appId) {
        super(username, password, appId);
    }
}
