package com.leo.taobao.service.Auth;

import com.leo.taobao.entity.CustomerLogin;
import com.leo.taobao.util.ResponseResult;

public interface SignUpService {
    /**
     * @param customerlogin
     */
    ResponseResult signUp(CustomerLogin customerlogin);
}
