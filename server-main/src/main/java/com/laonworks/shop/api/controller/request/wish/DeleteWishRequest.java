package com.laonworks.shop.api.controller.request.wish;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "delete wishs")
@Data
@ToString
public class DeleteWishRequest {

    @ApiParam(value = "product num", required = true)
    public int prdNum =0; // 상품번호

    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}
