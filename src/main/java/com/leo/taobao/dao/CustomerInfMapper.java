package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerInf;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "customerInfMapper")
public interface CustomerInfMapper {
    int deleteByPrimaryKey(Integer customerInfId);

    int addCustomerInf(CustomerInf record);

    int insertSelective(CustomerInf record);

    CustomerInf queryCustomerInfByCustomerInfId(Integer customerInfId);


    CustomerInf queryCustomerInfByCustomerId(Integer customerId);

    int updateByPrimaryKeySelective(CustomerInf record);

    int updateByPrimaryKey(CustomerInf record);
}