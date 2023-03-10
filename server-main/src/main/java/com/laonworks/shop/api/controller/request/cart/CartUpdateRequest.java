package com.laonworks.shop.api.controller.request.cart;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class CartUpdateRequest {

    @ApiParam(value = "cart count", required = true)
    private int cnt;

    @ApiParam(value = "product Num", required = true)
    private int productNum;

    public boolean valid(){return cnt > 0 && productNum > 0;}
    public boolean invalid(){return !valid();}

}
