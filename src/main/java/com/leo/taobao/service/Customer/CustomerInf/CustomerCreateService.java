package com.leo.taobao.service.Customer.CustomerInf;


import com.leo.taobao.dao.CustomerBalanceLogMapper;
import com.leo.taobao.dao.CustomerInfMapper;
import com.leo.taobao.dao.CustomerLoginLogMapper;
import com.leo.taobao.dao.CustomerLoginMapper;
import com.leo.taobao.entity.CustomerInf;
import com.leo.taobao.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerCreateService {

    private CustomerLoginMapper customerLoginMapper;

    private CustomerInfMapper customerInfMapper;

    private CustomerLoginLogMapper customerLoginLogMapper;

    private CustomerBalanceLogMapper customerBalanceLogMapper;

/*
    @Autowired
    public CustomerCreateService(CustomerInfMapper customerInfMapper) {

        this.customerInfMapper = customerInfMapper;
    }
*/

    /**
     * 创建用户信息
     *
     * @param customerInfoId
     */
    public ResponseResult createCustomerInfo(int customerInfoId) {
        //1. set the customer Login info
        CustomerLoginMapper customerLoginMapper = null;
        //2. set the customer Info
        CustomerInf customerInf = new CustomerInf();
        customerInf.setCustomerId(customerInfoId);
        //3. initialize the balance data

        return null;
    }
}
