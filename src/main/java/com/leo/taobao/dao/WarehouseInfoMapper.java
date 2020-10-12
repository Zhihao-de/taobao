package com.leo.taobao.dao;

import com.leo.taobao.entity.WarehouseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "WarehouseInfoMapper")
public interface WarehouseInfoMapper {
    int deleteByPrimaryKey(Short wId);

    int insert(WarehouseInfo record);

    int insertSelective(WarehouseInfo record);

    WarehouseInfo selectByPrimaryKey(Short wId);

    int updateByPrimaryKeySelective(WarehouseInfo record);

    int updateByPrimaryKey(WarehouseInfo record);
}