package com.laonworks.shop.api.controller.request.item;

import com.laonworks.shop.api.controller.vo.ItemInfo;
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

    @ApiParam(value = "")
    public int pageNo=0;

    @ApiParam(value = "")
    public int pageSize=0;

    @ApiParam(value = "")
    public int cateCode=0;


    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }

}
