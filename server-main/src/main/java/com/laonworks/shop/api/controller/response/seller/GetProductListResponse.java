package com.laonworks.shop.api.controller.response.seller;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.controller.vo.ProductInfo;
import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Get product list")
@Data
public class GetProductListResponse extends BaseResponse {

  public int totalCount = 0;

  public int pageNo = 0;

  public int pageSize = 0;

  public List<ProductInfo> productList = null;

}
