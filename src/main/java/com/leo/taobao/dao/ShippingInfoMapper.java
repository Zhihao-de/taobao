package com.leo.taobao.dao;

import com.leo.taobao.entity.ShippingInfo;

public interface ShippingInfoMapper {
    int deleteByPrimaryKey(Byte shipId);

    int insert(ShippingInfo record);

    int insertSelective(ShippingInfo record);

    ShippingInfo selectByPrimaryKey(Byte shipId);

    int updateByPrimaryKeySelective(ShippingInfo record);

    int updateByPrimaryKey(ShippingInfo record);
}