package com.laonworks.shop.api.controller.response.seller;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Product Modification")
@Data
public class ModifyProductResponse extends BaseResponse {
    public ProductVo productVo = null;
    public int result = 0;
}
