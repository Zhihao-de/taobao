package com.leo.taobao.service.Customer.CustomerBalance;


import com.leo.taobao.dao.CustomerBalanceLogMapper;
import com.leo.taobao.entity.CustomerBalanceLog;
import com.leo.taobao.error.ErrCodes;
import com.leo.taobao.util.ResponseResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;

@Service
@Transactional(readOnly = true)
public class CustomerBalanceUpdateService {
    //@Autowired
    private CustomerBalanceLogMapper customerBalanceLogMapper;

    public CustomerBalanceUpdateService(CustomerBalanceLogMapper customerBalanceLogMapper) {
        this.customerBalanceLogMapper = customerBalanceLogMapper;


    }

    public ResponseResult updateBalance(int id, CustomerBalanceLog cbl) {
        try {
            CustomerBalanceLog ccc = customerBalanceLogMapper.selectByPrimaryKey(id);
            int res = customerBalanceLogMapper.updateByPrimaryKey(cbl);
            System.out.println("dasdasdasdsadasdasd");
            return ResponseResult.ok().put("message", "Data has been updated!");
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

    public ResponseResult clearBalance(int id) {
        try {
            CustomerBalanceLog ccc = customerBalanceLogMapper.selectByPrimaryKey(id);
            ccc.setAmount(new BigDecimal(Double.toString(0.0)));
            int res = customerBalanceLogMapper.updateByPrimaryKey(ccc);
            return ResponseResult.ok().put("message", "Balance has been cleared");
        } catch (Exception ex) {
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }
}
