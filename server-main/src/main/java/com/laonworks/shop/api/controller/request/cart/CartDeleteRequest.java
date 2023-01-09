package com.laonworks.shop.api.controller.request.cart;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
@ApiModel(value = "cart-delete")
public class CartDeleteRequest {

    @ApiParam(value = "productNum", required = true)
    public int productNum = 0;


}
