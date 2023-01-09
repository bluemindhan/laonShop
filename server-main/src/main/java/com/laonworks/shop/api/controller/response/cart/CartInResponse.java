package com.laonworks.shop.api.controller.response.cart;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.vo.CartVo;
import lombok.Data;
import lombok.ToString;

@Data
public class CartInResponse extends BaseResponse {

    public CartVo cartVo = null;

}
