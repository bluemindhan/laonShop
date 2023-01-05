package com.laonworks.shop.api.controller.vo;

import com.laonworks.shop.api.mapper.vo.ProductVo;
import com.laonworks.shop.api.mapper.vo.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import java.util.*;

@ApiModel(description = "Product info")
@Data
@ToString
public class ProductInfo{
    @ApiParam(value = "product id")
    public long productId; // 상품번호

    @ApiParam(value = "product createDate")
    public String createDate; // 생성일자

    @ApiParam(value = "product updateDate")
    public String updateDate; // 수정일자

    @ApiParam(value = "product sellerId")
    public String sellerId; // 판매자 아이디

    @ApiParam(value = "product price")
    public String price; // 상품 가격

    @ApiParam(value = "product image")
    public String image; // 이미지 url

    @ApiParam(value = "product name")
    public String name; // 상품 이름

    @ApiParam(value = "product detail")
    public String detail; // 상품 설명


    List<ProductInfo> productInfoList; // 상품 목록

}