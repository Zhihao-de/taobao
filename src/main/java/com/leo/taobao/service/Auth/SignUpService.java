package com.leo.taobao.service.Auth;

import com.leo.taobao.entity.CustomerInf;
import com.leo.taobao.util.ResponseResult;

public interface SignUpService {
    /**
     * @param customerInf
     */
    ResponseResult signUp(CustomerInf customerInf);
}
