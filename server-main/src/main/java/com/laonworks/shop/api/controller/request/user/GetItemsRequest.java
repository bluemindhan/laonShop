package com.laonworks.shop.api.controller.request.user;

import com.laonworks.shop.api.controller.vo.ProductInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ApiModel(description = "get items")
@Data
@ToString
public class GetItemsRequest {

    @ApiParam(value = "items")
    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }

}
