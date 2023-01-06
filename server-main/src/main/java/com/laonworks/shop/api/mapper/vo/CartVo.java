package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartVo {

    public String userid;

    public int productNum;

    public String price;

    public String productName;

    public int cnt;

}
