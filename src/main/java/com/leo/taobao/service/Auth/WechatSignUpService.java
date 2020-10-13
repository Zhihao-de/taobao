package com.leo.taobao.service.Auth;


import com.leo.taobao.entity.CustomerInf;
import com.leo.taobao.entity.CustomerLogin;
import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.util.ResponseResult;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("wxSignUpSvc")
@Transactional
public class WechatSignUpService extends SignUpServiceBase implements SignUpService {

    //@Override
    /*
    public ResponseResult signUp(@NotNull CustomerInf customerInf) {
        assert null != customerInf.getMobilePhone();
        assert null != user.getWxid();

        try {
            user.setJoinDate(new Date(System.currentTimeMillis()));
            userMapper.createUserByWxid(user);
            return ResponseResult.ok();
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SIGN_UP_ERROR, ex.getMessage());
        }
    }*/
}
