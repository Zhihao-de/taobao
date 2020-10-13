package com.leo.taobao.service.Customer.CustomerInf;

import com.leo.taobao.dao.CustomerInfMapper;
import com.leo.taobao.entity.CustomerInf;
import com.leo.taobao.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerRetrievalService {
    //@Autowired
    private CustomerInfMapper customerInfMapper;

    @Autowired
    public CustomerRetrievalService(CustomerInfMapper customerInfMapper) {
        this.customerInfMapper = customerInfMapper;
    }

    /**
     * 根据customerInfoID获取用户信息
     *
     * @param customerInfoId
     */
    public ResponseResult getCustomerInfo(int customerInfoId) {
        assert 0 < customerInfoId;
        CustomerInf customerInf;
        try {
            customerInf = customerInfMapper.queryCustomerInfByCustomerInfId(customerInfoId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseResult.ok();
    }

    /**
     * 根据customerID获取用户信息
     *
     * @param customerId
     */
    public CustomerInf getCustomerInfoByCustomerId(int customerId) {
        assert 0 < customerId;
        System.out.println("dasdasdasdsadasdasd");
        CustomerInf customerInf = customerInfMapper.queryCustomerInfByCustomerId(customerId);
        return customerInf;
    }


}
