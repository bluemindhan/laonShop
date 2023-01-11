package com.laonworks.shop.api.controller.request.search;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "search")
public class SearchRequest {

    public String keyWord = "";

    public int cateCode = 0;

    public String filter = "";


}
