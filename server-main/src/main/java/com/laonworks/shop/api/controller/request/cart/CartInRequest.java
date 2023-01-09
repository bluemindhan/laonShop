package com.laonworks.shop.api.controller.request.cart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("")
public class CartInRequest {

    @ApiParam(value = "product Number", required = true)
    public int productNum = 0;

    @ApiParam(value = "userid", required = true)
    public String userid = "";

    @ApiParam(value = "product cnt", required = true)
    public int cnt = 0;

    public boolean valid(){return productNum >= 0 && !userid.equals("") && cnt >= 0;}
    public boolean invalid(){return !valid();}

}
