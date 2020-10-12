package com.leo.taobao.dao;

import com.leo.taobao.entity.BrandInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "BrandInfoMapper")
public interface BrandInfoMapper {
    int deleteByPrimaryKey(Short brandId);

    int insert(BrandInfo record);

    int insertSelective(BrandInfo record);

    BrandInfo selectByPrimaryKey(Short brandId);

    int updateByPrimaryKeySelective(BrandInfo record);

    int updateByPrimaryKey(BrandInfo record);
}