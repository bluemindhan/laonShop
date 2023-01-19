package com.laonworks.shop.api.jihyeon.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductImageVo {

  public int prdtNo; // 상품 번호

  public int prdtImgNo; // 상품 이미지 번호

  public String prdtImgNm; // 상품 이미지 이름

  public String prdtImgUrl; // 상품 이미지 URL
}
