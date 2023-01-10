package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.mapper.vo.ProductVo;
import com.laonworks.shop.api.mapper.vo.WishVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WishMapper {

    List<WishVo> selectWishList(@Param("userId") String email); // login user 상품 찜 목록

    int insertWishProduct(@Param("wish") WishVo vo); // login user 상품 찜 추가

    int deleteWishProduct(@Param("wish") WishVo vo); // login user 상품 찜 해제

}
