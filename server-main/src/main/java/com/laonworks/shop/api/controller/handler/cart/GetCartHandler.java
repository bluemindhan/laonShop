package com.laonworks.shop.api.controller.handler.cart;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.response.cart.GetCartResponse;
import com.laonworks.shop.api.mapper.CartMapper;
import com.laonworks.shop.api.mapper.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetCartHandler extends BaseHandler {

    @Autowired
    CartMapper cartMapper;

    public GetCartResponse excute(String userid) {

        GetCartResponse res = new GetCartResponse();
        List<CartVo> list = null;

        try {
            list = cartMapper.selectCartInfo(userid);
        } catch (Exception e) {
            res.setCode(ResultCode.InternalServerError);
            return res;
        }

        res.setVolist(list);
        res.setCode(ResultCode.Success);

        return res;
    }
}
