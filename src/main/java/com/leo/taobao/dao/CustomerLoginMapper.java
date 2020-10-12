package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerLogin;

public interface CustomerLoginMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(CustomerLogin record);

    int insertSelective(CustomerLogin record);

    CustomerLogin selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(CustomerLogin record);

    int updateByPrimaryKey(CustomerLogin record);
}