package com.leo.taobao.dao;

import com.leo.taobao.entity.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "ProductCategoryMapper")
public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Short categoryId);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Short categoryId);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);
}