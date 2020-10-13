package com.leo.taobao.service.Auth;


import com.leo.taobao.entity.CustomerInf;
import com.leo.taobao.entity.CustomerLogin;
import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.security.PasswordUtils;
import com.leo.taobao.util.ResponseResult;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("basicSignUpSvc")
@Transactional
public class BasicSignUpService extends SignUpServiceBase implements SignUpService {

    @Override
    public ResponseResult signUp(@NotNull CustomerInf customerInf, @NotNull CustomerLogin customerLogin) {
        assert null != customerInf.getMobilePhone();
        assert null != customerLogin.get;

        try {
            user.setPassword(PasswordUtils.encrypt(user.getPassword()));
            user.setJoinDate(new Date(System.currentTimeMillis()));
            userMapper.createUserByPass(user);
            return ResponseResult.ok();
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SIGN_UP_ERROR, ex.getMessage());
        }
    }

}
