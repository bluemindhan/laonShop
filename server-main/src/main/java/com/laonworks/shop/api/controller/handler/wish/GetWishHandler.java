package com.laonworks.shop.api.controller.handler.wish;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.wish.GetWishRequest;
import com.laonworks.shop.api.controller.response.wish.GetWishResponse;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.mapper.WishMapper;
import com.laonworks.shop.api.mapper.vo.WishVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetWishHandler extends BaseHandler {
    @Autowired
    WishMapper wishMapper;

    public GetWishResponse execute(CustomUserDetails user, GetWishRequest req) {
        GetWishResponse res= new GetWishResponse();
        if (user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        int userType = user.getUserType();
        String email = user.getUsername();
        try{
            List<WishVo> wishVoList;
            if (userType == UserType.User.getValue()) {
                wishVoList=wishMapper.selectWishList(email);
                if(wishVoList!=null&&wishVoList.size()!=0){
                    res.setWishList(wishVoList); // response 객체에 set
                    res.setCode(ResultCode.Success);
                }
                else{
                    res.setCode(ResultCode.NonContent);
                    return res;
                }
            }
            else if(userType==UserType.Seller.getValue()) {
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
            else{
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
        } catch (Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return  res;
    }
}
