package com.laonworks.shop.api.controller.response.seller;

import com.laonworks.shop.api.controller.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Product delete")
@Data
public class DeleteProductResponse extends BaseResponse {
}
