package com.laonworks.shop.api.controller.response.wish;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.controller.vo.WishInfo;
import com.laonworks.shop.api.mapper.vo.WishVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "post wishs")
@Data
public class PostWishResponse extends BaseResponse {

    public int result=0; // 상품 찜 insert sql 실행 결과
}
