package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.controller.vo.ProductInfo;
import com.laonworks.shop.api.mapper.vo.BuyVO;
import com.laonworks.shop.api.mapper.vo.ProductVo;
import com.laonworks.shop.api.mapper.vo.WishVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<ProductVo> selectProductList(); // 전체 상품 목록 select
    List<BuyVO> selectBuyList(@Param("userId") String userId); // user 구매 이력 목록 select





}
