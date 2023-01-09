package com.laonworks.shop.api.controller.request.seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "Get product list")
@Data
@ToString
public class GetProductListRequest {
    @ApiParam(value = "")
    public int pageNo =  0;
    
    @ApiParam(value = "")
    public int pageSize =  0;
    
    
    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}

