package com.laonworks.shop.api.controller.request.seller;

import com.laonworks.shop.api.controller.vo.ProductInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ApiModel(value = "Product Modification")
@Data
@ToString
public class ModifyProductRequest {

    @ApiParam(value = "제품 번호")
    public int productNum = 0;

    @ApiParam(value = "제품 이름")
    public String productName = "";

    @ApiParam(value = "제품 설명")
    public String productDesc = "";

    @ApiParam(value = "제품 가격")
    public int productPrice = 0;

    @ApiParam(value = "제품 이미지")
    public List<String> imageList =  null;

    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}

