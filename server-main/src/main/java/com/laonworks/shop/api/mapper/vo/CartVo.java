package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CartVo {

    public String userid; // 유저 아이디
    
    public List<ImageVo> imageList; // 상품이미지

    public int productNum; // 상품 번호

    public String price; // 상품 가격

    public String productName; // 상품 이름

    public int cnt; // 상품 개수

    public String sum; // 상품의 가격 합

}
