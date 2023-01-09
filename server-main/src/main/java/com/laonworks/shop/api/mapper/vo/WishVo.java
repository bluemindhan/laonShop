package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WishVo {
    
    public long userId; // 사용자 아이디

    public long productNum; // 상품 번호
    
    public String productName; // 상품 이름 
    
    public String createDate; // 생성 일시
}
