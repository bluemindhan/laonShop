package com.laonworks.shop.api.controller.request.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

@ApiModel(value = "item Detail")
@Data
@ToString
public class ItemDetailRequest {

    @ApiParam(value = "제품번호")
    public int productNum = 0;
    @ApiParam
    public String accessToken = null;
}
