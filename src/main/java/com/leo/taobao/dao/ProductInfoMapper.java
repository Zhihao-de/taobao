package com.leo.taobao.dao;

import com.leo.taobao.entity.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "ProductInfoMapper")
public interface ProductInfoMapper {
    int deleteByPrimaryKey(Integer productId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer productId);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKeyWithBLOBs(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}