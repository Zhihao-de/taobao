package com.leo.taobao.dao;

import com.leo.taobao.entity.CustomerPointLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "CustomerPointLogMapper")
public interface CustomerPointLogMapper {
    int deleteByPrimaryKey(Integer pointId);

    int insert(CustomerPointLog record);

    int insertSelective(CustomerPointLog record);

    CustomerPointLog selectByPrimaryKey(Integer pointId);

    int updateByPrimaryKeySelective(CustomerPointLog record);

    int updateByPrimaryKey(CustomerPointLog record);
}