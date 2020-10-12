package com.leo.taobao.dao;

import com.leo.taobao.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "OrderDetailMapper")
public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer orderDetailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer orderDetailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);
}