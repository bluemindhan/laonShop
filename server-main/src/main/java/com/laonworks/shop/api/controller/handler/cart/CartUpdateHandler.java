package com.laonworks.shop.api.controller.handler.cart;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.cart.CartUpdateRequest;
import com.laonworks.shop.api.controller.response.cart.CartInResponse;
import com.laonworks.shop.api.mapper.CartMapper;
import com.laonworks.shop.api.mapper.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartUpdateHandler extends BaseHandler {

    @Autowired
    CartMapper cartMapper;

    public CartInResponse excute(CartUpdateRequest req) {

        CartInResponse res = new CartInResponse();
        CartVo vo = new CartVo();

        if(req.invalid()){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }

        CartVo cartVo = (CartVo) cartMapper.selectCartInfo(req.getUserid());

        if(cartVo == null){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }

        try{
            vo.setProductNum(req.getProductNum());
            vo.setUserid(req.getUserid());
            vo.setCnt(req.getCnt());
            cartMapper.updateCart(vo);

        }catch (Exception e){
            log.error(e.getMessage());
            res.setCode(ResultCode.InternalServerError);
        }

        res.setCode(ResultCode.Success);
        res.setCartVo(vo);

        return res;
    }
}
