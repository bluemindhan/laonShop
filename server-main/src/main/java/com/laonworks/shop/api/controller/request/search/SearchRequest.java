package com.laonworks.shop.api.controller.request.search;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "search")
public class SearchRequest {

    public String keyWord = ""; // 검색

    public int cateCode = 0; //태블릿 , 핸드폰 등 분류

    public String filter = ""; // 좋아요 높은 순인지 아닌지 확인

    public int page = 0; // 현재 페이지


}
