package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.controller.vo.ProductInfo;
import com.laonworks.shop.api.mapper.vo.BuyVO;
import com.laonworks.shop.api.mapper.vo.ProductVo;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.mapper.vo.WishVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {


    List<BuyVO> selectBuyList(@Param("userId") String userId); //login user 구매 이력 목록 select

    int updateUserInfo(@Param("map") HashMap<String, Object> map); //  login user(buyer) 정보 수정

    int updateSellerInfo(@Param("map") HashMap<String, Object> map);// login user(seller) 정보 수정




}
