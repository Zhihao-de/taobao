package com.leo.taobao.dao;

import com.leo.taobao.entity.ProductPicInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "ProductPicInfoMapper")
public interface ProductPicInfoMapper {
    int deleteByPrimaryKey(Integer productPicId);

    int insert(ProductPicInfo record);

    int insertSelective(ProductPicInfo record);

    ProductPicInfo selectByPrimaryKey(Integer productPicId);

    int updateByPrimaryKeySelective(ProductPicInfo record);

    int updateByPrimaryKey(ProductPicInfo record);
}