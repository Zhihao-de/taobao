package com.leo.taobao.security;


import com.leo.taobao.dao.CustomerInfMapper;
import com.leo.taobao.dao.CustomerLoginMapper;
import com.leo.taobao.entity.CustomerInf;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public abstract class UnifiedAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    //protected UserMapper userMapper;
    protected CustomerInfMapper customerInfMapper;
    protected CustomerLoginMapper customerLoginMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(@NotNull PrincipalCollection principals) {
        String number = (String) principals.getPrimaryPrincipal();
        CustomerInf user = userMapper.queryUserRoleAndPermissionByNumber(number);
        //CustomerInf user = customerInfMapper.;
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(new HashSet<>(Arrays.asList(StringUtils.split(user.getUserRoles()))));
        if (0 == user.getUserPerms()) {
            info.addStringPermission(PermissionNames.Normal);
        }
        return info;
    }

    AuthenticationInfo getCacheableAuthenticationInfo(@NotNull AppUsernamePasswordToken token, @NotNull User user) {
        String number = token.getUsername();
        String password = (null != token.getPassword() ? new String(token.getPassword()) : "");
        String wxid = (null != user.getWxid() ? user.getWxid() : "");
        AppCredential ac = new AppCredential(number);
        ac.setTimestamp(token.getTimestamp());
        ac.getCredentials().put(BasicAuthenticationToken.class.getName(), password);
        ac.getCredentials().put(WechatAuthenticationToken.class.getName(), wxid);
        return new SimpleAuthenticationInfo(number, ac, getName());
    }
}
