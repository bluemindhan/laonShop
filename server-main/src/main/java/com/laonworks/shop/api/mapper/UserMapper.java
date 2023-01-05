package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.mapper.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<ProductVo> selectProductList(); // 전체 상품 목록 select

}
