package com.leo.taobao.security;

public class WechatAuthenticationToken extends AppUsernamePasswordToken {

    public WechatAuthenticationToken(String username, String password, String appId) {
        super(username, password, appId);
    }
}