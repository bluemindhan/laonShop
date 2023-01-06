package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.auth.CartInHandler;
import com.laonworks.shop.api.controller.request.cart.CartInRequest;
import com.laonworks.shop.api.controller.response.cart.CartInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/cart")
public class CartController {

    @Autowired
    CartInHandler cartInHandler;

    @RequestMapping(method = RequestMethod.POST,value = "cartin")
    CartInResponse cartin(@RequestBody CartInRequest req, @AuthenticationPrincipal Authentication auth){

        CartInResponse res = new CartInResponse();

        if(auth == null){
            res.setCode(ResultCode.NotFoundUser);
            return res;
        }

        return cartInHandler.excute(req);
    }



}
