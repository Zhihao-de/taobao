package com.leo.taobao.security;


import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.error.RuntimeServiceException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

public class MultiCredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, @NotNull AuthenticationInfo info) {
        AppUsernamePasswordToken token = (AppUsernamePasswordToken) authcToken;
        token.setThrowable(null);
        AppCredential ac = (AppCredential) info.getCredentials();
        Object tokenCredentials = this.getCredentials(authcToken);
        String credential = ac.getCredentials().get(token.getClass().getName());
        if (!this.equals(tokenCredentials, credential)) {
            token.setThrowable(new RuntimeServiceException(ErrCodes.TOKEN_VERIFICATION_ERROR, "invalid_token"));
            return false;
        }
        if (token.getAppId().equals(ac.getAppId()) && token.getTimestamp() != ac.getTimestamp()) {
            token.setThrowable(new RuntimeServiceException(ErrCodes.REPETITIVE_SIGN_IN_ERROR, "already_signed_in"));
            return false;
        }
        token.setAppCredential(new WeakReference<>(ac));
        return true;
    }
}
