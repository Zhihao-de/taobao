package com.leo.taobao.dao;

import com.leo.taobao.entity.OrderCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "OrderCartMapper")
public interface OrderCartMapper {
    int deleteByPrimaryKey(Integer cartId);

    int insert(OrderCart record);

    int insertSelective(OrderCart record);

    OrderCart selectByPrimaryKey(Integer cartId);

    int updateByPrimaryKeySelective(OrderCart record);

    int updateByPrimaryKey(OrderCart record);
}