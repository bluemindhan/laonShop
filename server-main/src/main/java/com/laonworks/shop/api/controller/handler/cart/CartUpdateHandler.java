package com.laonworks.shop.api.controller.handler.cart;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.cart.CartUpdateRequest;
import com.laonworks.shop.api.controller.response.cart.GetCartResponse;
import com.laonworks.shop.api.mapper.CartMapper;
import com.laonworks.shop.api.mapper.vo.CartVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class CartUpdateHandler extends BaseHandler {

    @Autowired
    CartMapper cartMapper;

    public GetCartResponse excute(CartUpdateRequest req, CustomUserDetails user) {

        GetCartResponse res = new GetCartResponse();
        HashMap<String, Object> map = new HashMap<>();
        CartVo vo = new CartVo();
        List<CartVo> listvo = null;

        if(req.invalid()){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }

        map.put("request",req);
        map.put("userid",user.getUsername());

        CartVo cartVo = cartMapper.findByUseridAndProductNo(map);

        if(cartVo == null){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }

        try{
            vo.setProductNum(req.getProductNum());
            vo.setUserid(user.getUsername());
            vo.setCnt(req.getCnt());
            cartMapper.updateCart(vo);

        }catch (Exception e){
            log.error(e.getMessage());
            res.setCode(ResultCode.InternalServerError);
        }

        try{
            listvo = cartMapper.selectCartInfo(user.getUsername());

        } catch(Exception e){
            log.error(e.getMessage());
            res.setCode(ResultCode.InternalServerError);
        }


        res.setCode(ResultCode.Success);
        res.setVolist(listvo);

        return res;
    }
}
