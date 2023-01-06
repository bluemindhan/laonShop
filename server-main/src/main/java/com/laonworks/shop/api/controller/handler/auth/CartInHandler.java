package com.laonworks.shop.api.controller.handler.auth;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.cart.CartInRequest;
import com.laonworks.shop.api.controller.response.cart.CartInResponse;
import com.laonworks.shop.api.mapper.CartMapper;
import com.laonworks.shop.api.mapper.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Slf4j
public class CartInHandler extends BaseHandler {

    @Autowired
    CartMapper cartMapper;

    public CartInResponse excute(CartInRequest req) {

        CartInResponse res = new CartInResponse();
        HashMap<String, Object> map = new HashMap<>();


        if(req.invalid()){
            res.setCode(ResultCode.BadParams);
            return res;
        }

        try{
            map.put("request",req);
            CartVo vo = cartMapper.findByUseridAndProductNo(map);

            if(vo != null){
                res.setMessage("이미 장바구니에 담겨져있습니다");
                return res;
            }

            CartVo cartVo = new CartVo();
            cartVo.productNum = req.productNum;
            cartVo.cnt = req.cnt;
            cartVo.userid = req.userid;

            cartMapper.insertCartInfo(cartVo);

            res.setCartVo(cartVo);
            res.setCode(ResultCode.Success);

        } catch (Exception e){log.error(e.getMessage());}

        return res;

    }
}
