package com.laonworks.shop.api.controller.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;
import oracle.security.pki.resources.OraclePKICmd;

@ApiModel(value = "item Detail")
@Data
@ToString
public class ItemDetailRequest {

    @ApiParam(value = "제품번호")
    public int productNum = 0;
}
