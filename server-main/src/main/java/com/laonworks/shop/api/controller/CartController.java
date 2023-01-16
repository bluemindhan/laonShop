package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.cart.CartDeleteHandler;
import com.laonworks.shop.api.controller.handler.cart.CartInHandler;
import com.laonworks.shop.api.controller.handler.cart.CartUpdateHandler;
import com.laonworks.shop.api.controller.handler.cart.GetCartHandler;
import com.laonworks.shop.api.controller.request.cart.CartDeleteRequest;
import com.laonworks.shop.api.controller.request.cart.CartInRequest;
import com.laonworks.shop.api.controller.request.cart.CartUpdateRequest;
import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.controller.response.cart.CartInResponse;
import com.laonworks.shop.api.controller.response.cart.CartUpdateResponse;
import com.laonworks.shop.api.controller.response.cart.GetCartResponse;
import com.laonworks.shop.api.mapper.vo.CartVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;

@RestController
@RequestMapping(value = "api/v1/cart")
public class CartController {

    @Autowired
    CartInHandler cartInHandler;

    @RequestMapping(method = RequestMethod.POST, value = "cartin")
    CartInResponse cartin(@RequestBody CartInRequest req, @AuthenticationPrincipal Authentication auth){

        if(auth == null) {
            throw new RestClientResponseException("토큰 정보를 불러올 수 없음", HttpStatus.UNAUTHORIZED.value(),"",null,null,null);
        }
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        if(user == null){
            throw new RestClientResponseException(("토큰 정보를 불러올 수 없음"),HttpStatus.UNAUTHORIZED.value(),"",null, null,null);

        }

        return cartInHandler.excute(req,user);
    }

    @Autowired
    CartDeleteHandler cartDeleteHandler;

    @RequestMapping(method = RequestMethod.DELETE, value = "cart-delete")
    BaseResponse cartDelete(@AuthenticationPrincipal Authentication auth, @RequestBody CartDeleteRequest req){
        BaseResponse res = new BaseResponse();

        if(auth == null) {
            throw new RestClientResponseException("토큰 정보를 불러올 수 없음", HttpStatus.UNAUTHORIZED.value(),"",null,null,null);
        }
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        if(user == null){
            throw new RestClientResponseException(("토큰 정보를 불러올 수 없음"),HttpStatus.UNAUTHORIZED.value(),"",null, null,null);

        }

        return cartDeleteHandler.excute(req,user);

    }

    @Autowired
    CartUpdateHandler cartUpdateHandler;

    @RequestMapping(method = RequestMethod.PATCH, value = "cart-update")
    GetCartResponse cartUpdate(@AuthenticationPrincipal Authentication auth, @RequestBody CartUpdateRequest req){


        if(auth == null){
            throw new RestClientResponseException(("토큰 정보를 불러올 수 없음"),HttpStatus.UNAUTHORIZED.value(),"",null, null,null);
        }
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        if(user == null){
            throw new RestClientResponseException(("토큰 정보를 불러올 수 없음"),HttpStatus.UNAUTHORIZED.value(),"",null, null,null);

        }

        return cartUpdateHandler.excute(req,user);
    }

    @Autowired
    GetCartHandler getCartHandler;

    @RequestMapping(method = RequestMethod.GET, value = "cart-info")
    GetCartResponse cartInfo(@AuthenticationPrincipal Authentication auth){

        if(auth == null){
            throw new RestClientResponseException("토큰 정보를 불러올 수 없음",HttpStatus.UNAUTHORIZED.value(),"",null,null,null);
        }
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        if(user == null){
            throw new RestClientResponseException(("토큰 정보를 불러올 수 없음"),HttpStatus.UNAUTHORIZED.value(),"",null, null,null);

        }

        String userid = user.getUsername();


        return getCartHandler.excute(userid);


    }




}


