package com.laonworks.shop.api.controller.request.seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ApiModel(description = "Product delete")
@Data
@ToString
public class DeleteProductRequest {

    @ApiParam(value = "제품번호")
    public int productNum = 0;

    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}

