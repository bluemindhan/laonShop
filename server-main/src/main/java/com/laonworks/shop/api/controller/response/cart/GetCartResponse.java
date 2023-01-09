package com.laonworks.shop.api.controller.response.cart;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.vo.CartVo;
import lombok.Data;

import java.util.List;

@Data
public class GetCartResponse extends BaseResponse {

    public List<CartVo> volist;


}
