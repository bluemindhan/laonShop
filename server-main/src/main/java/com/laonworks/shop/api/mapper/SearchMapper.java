package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.controller.request.search.SearchRequest;
import com.laonworks.shop.api.mapper.vo.BigCategoryVo;
import com.laonworks.shop.api.mapper.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<ProductVo> findByKeyword(@Param(value = "search") SearchRequest req, int start, int end);

    List<BigCategoryVo> selectCateList();
}
