package com.leo.taobao.dao;

import com.leo.taobao.entity.OrderCart;

public interface OrderCartMapper {
    int deleteByPrimaryKey(Integer cartId);

    int insert(OrderCart record);

    int insertSelective(OrderCart record);

    OrderCart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(OrderCart record);

    int updateByPrimaryKey(OrderCart record);
}