package com.laonworks.shop.api.controller.request.seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "Get product list")
@Data
@ToString
public class GetProductDetailRequest {

    @ApiParam(value = "제품번호")
    public int prdtNo = 0;
    
    
    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}

