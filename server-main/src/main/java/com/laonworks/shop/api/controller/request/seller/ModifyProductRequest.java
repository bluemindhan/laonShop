package com.laonworks.shop.api.controller.request.seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ApiModel(value = "Product Modification")
@Data
@ToString
public class ModifyProductRequest {

    @ApiParam(value = "제품번호")
    public int productNum = 0;

    @ApiParam(value = "제품이름")
    public String productName = "";

    @ApiParam(value = "제품설명")
    public String productDesc = "";

    @ApiParam(value = "제품가격")
    public int productPrice = 0;

    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}

