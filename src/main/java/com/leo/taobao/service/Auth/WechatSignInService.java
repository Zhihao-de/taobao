package com.leo.taobao.service.Auth;


import org.springframework.stereotype.Service;

@Service(value = "wxSignInSvc")
public class WechatSignInService extends SignInServiceBase {
/*
    @Override
    AppUsernamePasswordToken createToken(String username, String password, String appId) {
        return new WechatAuthenticationToken(username, password, appId);
    }
 */
}