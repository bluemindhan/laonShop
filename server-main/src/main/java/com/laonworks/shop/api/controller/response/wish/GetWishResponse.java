package com.laonworks.shop.api.controller.response.wish;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.vo.WishVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "get wishs")
@Data
public class GetWishResponse extends BaseResponse {

    public List<WishVo> wishList=null;
}
