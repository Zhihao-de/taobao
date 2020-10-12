package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerBalanceLog;

public interface CustomerBalanceLogMapper {
    int deleteByPrimaryKey(Integer balanceId);

    int insert(CustomerBalanceLog record);

    int insertSelective(CustomerBalanceLog record);

    CustomerBalanceLog selectByPrimaryKey(Integer balanceId);

    int updateByPrimaryKeySelective(CustomerBalanceLog record);

    int updateByPrimaryKey(CustomerBalanceLog record);
}