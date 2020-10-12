package com.leo.taobao.dao;

import com.leo.taobao.entity.ProductComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component(value = "ProductCommentMapper")
public interface ProductCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(ProductComment record);

    int insertSelective(ProductComment record);

    ProductComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(ProductComment record);

    int updateByPrimaryKey(ProductComment record);
}