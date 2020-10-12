package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerPointLog;

public interface CustomerPointLogMapper {
    int deleteByPrimaryKey(Integer pointId);

    int insert(CustomerPointLog record);

    int insertSelective(CustomerPointLog record);

    CustomerPointLog selectByPrimaryKey(Integer pointId);

    int updateByPrimaryKeySelective(CustomerPointLog record);

    int updateByPrimaryKey(CustomerPointLog record);
}