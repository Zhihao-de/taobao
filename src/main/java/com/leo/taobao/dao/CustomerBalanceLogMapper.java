package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerBalanceLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component(value = "customerBalanceLogMapper")
public interface CustomerBalanceLogMapper {
    int deleteByPrimaryKey(Integer balanceId);

    int insert(CustomerBalanceLog record);

    int insertSelective(CustomerBalanceLog record);

    CustomerBalanceLog selectByPrimaryKey(Integer balanceId);

    int updateByPrimaryKeySelective(CustomerBalanceLog record);

    int updateByPrimaryKey(CustomerBalanceLog record);
}