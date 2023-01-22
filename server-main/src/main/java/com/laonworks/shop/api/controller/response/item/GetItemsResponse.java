package com.laonworks.shop.api.controller.response.item;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.vo.BigCategoryVo;
import com.laonworks.shop.api.mapper.vo.ProductVo1;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true) // 두 객체의 동등성,동일성 비교 어노테이션(callSuper-> 부모 클래스의 필드까지 감안)
@ApiModel(description = "get items")
@Data
public class GetItemsResponse extends BaseResponse {

    public int totalCount = 0;

    public int pageNo = 0;

    public int pageSize = 0;

    public List<ProductVo1> products=null;

    public List<BigCategoryVo> categoryList = null;


}
