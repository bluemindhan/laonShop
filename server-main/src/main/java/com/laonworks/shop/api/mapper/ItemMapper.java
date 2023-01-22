package com.laonworks.shop.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.laonworks.shop.api.mapper.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    ItemVo selectItemDetailInfo(@Param("pdNum") int productNo);

    int selectItemCount(@Param("cateCode") int cateCode); // 전체 상품 개수 total get

    List<ProductVo1> selectItemList(@Param("start") int start, @Param("end") int end, @Param("cateCode") int cateCode); // 전체 카테고리 상품 목록 Select

    List<ItemVo> selectMainPage();

    int selectWishFlag(String userid, int productNum);



}
