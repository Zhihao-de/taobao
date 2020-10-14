package com.leo.taobao.service.Auth;


import com.leo.taobao.dao.CustomerLoginMapper;
import com.leo.taobao.entity.CustomerLogin;
import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service("basicSignUpSvc")
@Transactional
public class BasicSignUpService extends SignUpServiceBase implements SignUpService {

    @Autowired
    private CustomerLoginMapper customerLoginMapper;

    @Override
    public ResponseResult signUp(CustomerLogin customerLogin) {
        assert null != customerLogin.getLoginName();
        assert null != customerLogin.getPassword();

        try {
            //customerLogin.setPassword(md5.encrypt(customerLogin.getPassword()));
            //此处先用md5加密  等下用shiro中的组件换
            customerLogin.setPassword(DigestUtils.md5DigestAsHex(customerLogin.getPassword().getBytes()));
            customerLogin.setModifiedTime(new Date(System.currentTimeMillis()));
            customerLoginMapper.addLoginInfo(customerLogin);
            return ResponseResult.ok();
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SIGN_UP_ERROR, ex.getMessage());
        }
    }

}
