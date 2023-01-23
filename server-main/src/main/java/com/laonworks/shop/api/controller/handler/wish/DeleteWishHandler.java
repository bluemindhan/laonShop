package com.laonworks.shop.api.controller.handler.wish;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.wish.DeleteWishRequest;
import com.laonworks.shop.api.controller.response.wish.DeleteWishResponse;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.mapper.WishMapper;
import com.laonworks.shop.api.mapper.vo.WishVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// login user 상품 찜 해제 Handler
@Slf4j
@Component
public class DeleteWishHandler extends BaseHandler {

    @Autowired
    WishMapper wishMapper;

    public DeleteWishResponse execute(CustomUserDetails user, DeleteWishRequest req) {
        DeleteWishResponse res = new DeleteWishResponse();
        if (user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        int userType = user.getUserType();
        String email = user.getUsername();
        int productNum=req.getPrdNum();

        try {
            List<WishVo> wishVoList;
            WishVo wishVo = new WishVo();
            wishVo.setUserId(email);
            wishVo.setProductNum(productNum);
            if(userType== UserType.User.getValue()){
                wishVoList=wishMapper.selectWishList(email);
                int result = wishMapper.deleteWishProduct(wishVo);
            }
            else if(userType==UserType.Seller.getValue()) {
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
            else{
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
        }catch (Exception e){
            res.setCode(ResultCode.InternalServerError);
        }
        res.setResult(1);
        res.setCode(ResultCode.Success);
        return res;
    }
}