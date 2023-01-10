package com.laonworks.shop.api.controller.response.wish;

import com.laonworks.shop.api.controller.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "post wishs")
@Data
public class DeleteWishResponse extends BaseResponse {

    public int result=0; // 상품 찜 delete query 실행 결과
}
