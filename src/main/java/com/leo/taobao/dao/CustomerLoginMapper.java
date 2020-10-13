package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerLogin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "CustomerLoginMapper")
public interface CustomerLoginMapper {
    int deleteByPrimaryKey(Integer customerId);

    int addLoginInfo(CustomerLogin record);

    CustomerLogin queryByCustomerId(Integer customerId);

    int updateByPrimaryKeySelective(CustomerLogin record);

    int updateByPrimaryKey(CustomerLogin record);
}