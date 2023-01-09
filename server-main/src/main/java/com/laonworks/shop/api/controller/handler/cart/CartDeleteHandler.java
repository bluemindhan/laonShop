package com.laonworks.shop.api.controller.handler.cart;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.cart.CartDeleteRequest;
import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.CartMapper;
import com.laonworks.shop.api.mapper.vo.CartVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CartDeleteHandler extends BaseHandler {

    @Autowired
    CartMapper cartMapper;

    public BaseResponse excute(CartDeleteRequest req, CustomUserDetails user) {

        BaseResponse res = new BaseResponse();
        CartVo vo = new CartVo();

        if(req == null){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }

        try{
            vo.setUserid(user.getUsername());
            vo.setProductNum(req.productNum);
            cartMapper.deleteCart(vo);
        } catch (Exception e){
            log.error(e.getMessage());
            res.setCode(ResultCode.InternalServerError);
        }

        res.setCode(ResultCode.Success);

        return res;
    }

}
