package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerInf;

public interface CustomerInfMapper {
    int deleteByPrimaryKey(Integer customerInfId);

    int insert(CustomerInf record);

    int insertSelective(CustomerInf record);

    CustomerInf selectByPrimaryKey(Integer customerInfId);

    int updateByPrimaryKeySelective(CustomerInf record);

    int updateByPrimaryKey(CustomerInf record);
}