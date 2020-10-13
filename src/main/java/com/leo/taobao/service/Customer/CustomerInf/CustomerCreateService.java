package com.leo.taobao.service.Customer.CustomerInf;


import com.leo.taobao.dao.CustomerBalanceLogMapper;
import com.leo.taobao.dao.CustomerInfMapper;
import com.leo.taobao.dao.CustomerLoginMapper;
import com.leo.taobao.entity.CustomerInf;
import com.leo.taobao.entity.CustomerLogin;
import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
//根据
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CustomerCreateService {

    private CustomerLoginMapper customerLoginMapper;
    private CustomerInfMapper customerInfMapper;
    private CustomerBalanceLogMapper customerBalanceLogMapper;


    @Autowired
    public CustomerCreateService(CustomerLoginMapper customerLoginMapper, CustomerInfMapper customerInfMapper, CustomerBalanceLogMapper customerBalanceLogMapper) {

        this.customerLoginMapper = customerLoginMapper;
        this.customerInfMapper = customerInfMapper;
        this.customerBalanceLogMapper = customerBalanceLogMapper;
    }

    /**
     * 创建用户资料信息
     *
     * @param
     */
    public ResponseResult createCustomerInfo(CustomerInf customerInf) {
        try {
            Date date = new Date();//获得系统时间.
            SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
            String nowTime = sdf.format(date);
            Date time = sdf.parse(nowTime);
            customerInf.setModifiedTime(time);
            customerInfMapper.addCustomerInf(customerInf);
            return ResponseResult.ok();
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//关键
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }

    /**
     * 创建用户登录信息
     *
     * @param
     */
    public ResponseResult createCustomerLoginInfo(CustomerLogin customerLogin) throws ParseException {
        try {
            Date date = new Date();//获得系统时间.
            SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
            String nowTime = sdf.format(date);
            Date time = sdf.parse(nowTime);
            customerLogin.setModifiedTime(time);
            int res = customerLoginMapper.addLoginInfo(customerLogin);

            return ResponseResult.ok();
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//关键
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }


}
