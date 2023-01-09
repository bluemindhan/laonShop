package com.laonworks.shop.api.controller.response.cart;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.vo.CartVo;
import lombok.Data;

@Data
public class CartUpdateResponse extends BaseResponse {

    public CartVo cartVo = null;

}
