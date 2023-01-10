package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BuyVO { // 사용자 구매이력 VO

    public long buyId; //  구매 이력 번호

    public String userId; // 사용자 아이디

    public long productId; // 상품 번호

    public String createDate; // 생성 일자

    public String updateDate; // 수정일자

    public long cnt; // 구매 개수

    public String name; // 구매 상품 이름

    public long tntPrice; // 구매 금액

    public void setUser(String email){
        this.userId=email;
    }


}
