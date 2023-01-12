package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductVo { //상품 VO

    public long productId; // 상품번호

    public String createDate; // 생성일자

    public String updateDate; // 수정일자

    public String sellerId; // 판매자 아이디

    public String price; // 상품 가격

    public String image; // 이미지 url

    public String name; // 상품 이름

    public String detail; // 상품 설명

    public int likeCnt; // 좋아요 개수

    public int bookCnt; // 북마크 갯수
}
