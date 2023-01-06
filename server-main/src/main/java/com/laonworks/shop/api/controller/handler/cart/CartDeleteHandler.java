package com.laonworks.shop.api.controller.handler.cart;

import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.cart.CartDeleteRequest;
import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartDeleteHandler extends BaseHandler {

    @Autowired
    CartMapper cartMapper;

    public BaseResponse excute(CartDeleteRequest req) {

        BaseResponse res = new BaseResponse();

        if(req == null){

        }

        return res;
    }

}
