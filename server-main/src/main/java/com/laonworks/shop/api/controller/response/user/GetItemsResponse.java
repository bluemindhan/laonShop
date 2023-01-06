package com.laonworks.shop.api.controller.response.user;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.controller.vo.ProductInfo;
import com.laonworks.shop.api.mapper.vo.ProductVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@EqualsAndHashCode(callSuper = true) // 두 객체의 동등성,동일성 비교 어노테이션(callSuper-> 부모 클래스의 필드까지 감안)
@ApiModel(description = "get items")
@Data
public class GetItemsResponse extends BaseResponse {

    public List<ProductVo> products=null;


}
