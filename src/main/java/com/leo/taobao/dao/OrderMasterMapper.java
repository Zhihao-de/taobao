package com.leo.taobao.dao;

import com.leo.taobao.entity.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "OrderMasterMapper")
public interface OrderMasterMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    OrderMaster selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);
}