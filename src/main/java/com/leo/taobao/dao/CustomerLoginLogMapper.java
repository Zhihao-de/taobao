package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "CustomerLoginLogMapper")
public interface CustomerLoginLogMapper {
    int deleteByPrimaryKey(Integer loginId);

    int insert(CustomerLoginLog record);

    int insertSelective(CustomerLoginLog record);

    CustomerLoginLog selectByPrimaryKey(Integer loginId);

    int updateByPrimaryKeySelective(CustomerLoginLog record);

    int updateByPrimaryKey(CustomerLoginLog record);
}