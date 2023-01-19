package com.laonworks.shop.api.controller.response.seller;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.controller.vo.ProductInfo;
import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Get product Detail")
@Data
public class GetProductDetailResponse extends BaseResponse {

  public ProductVo productVo = null;
  public List<ProductImageVo> productImageVoList = null;
}
