package com.leo.taobao.dao;

import com.leo.taobao.entity.SupplierInfo;

public interface SupplierInfoMapper {
    int deleteByPrimaryKey(Integer supplierId);

    int insert(SupplierInfo record);

    int insertSelective(SupplierInfo record);

    SupplierInfo selectByPrimaryKey(Integer supplierId);

    int updateByPrimaryKeySelective(SupplierInfo record);

    int updateByPrimaryKey(SupplierInfo record);
}