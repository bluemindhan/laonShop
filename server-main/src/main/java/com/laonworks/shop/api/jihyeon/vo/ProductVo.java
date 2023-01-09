package com.laonworks.shop.api.jihyeon.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductVo {

  public String sllrId;   // 판매자 아이디

  public int prdtNo;      // 상품번호

  public double prdtPrce; // 상품가격

  public String prdtNm;   // 상품이름

  public String prdtDesc; // 상품설명

  public String crtDt;    // 생성날짜

  public String updtDt;
}
