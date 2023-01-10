package com.laonworks.shop.api.controller.request.seller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ApiModel(description = "Add a new product")
@Data
@ToString
public class AddProductRequest {
    @ApiParam(value = "")
    public String productName =  "";
    
    @ApiParam(value = "")
    public String productDesc =  "";
    
    @ApiParam(value = "")
    public int productPrice =  0;
    
//    @ApiParam(value = "")
//    public List<String> imageList =  null;
    
    
    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}

