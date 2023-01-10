package com.laonworks.shop.api.controller.request.wish;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "post wishs")
@Data
@ToString
public class PostWishRequest {

    @ApiParam(value = "product num", required = true)
    public int prdNum =0; // 상품번호

    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}
