package com.laonworks.shop.api.controller.handler.wish;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.wish.PostWishRequest;
import com.laonworks.shop.api.controller.response.wish.PostWishResponse;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.controller.vo.WishInfo;
import com.laonworks.shop.api.mapper.UserMapper;
import com.laonworks.shop.api.mapper.WishMapper;
import com.laonworks.shop.api.mapper.vo.WishVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import com.laonworks.shop.api.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;


// Login User 상품 찜 등록 Handler
@Slf4j
@Component
public class PostWishHandler extends BaseHandler {
    @Autowired
    WishMapper wishMapper;

    public PostWishResponse execute(CustomUserDetails user,PostWishRequest req) {
        PostWishResponse res = new PostWishResponse();
        if (user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        int userType = user.getUserType();
        String email = user.getUsername();
        int productNum = req.getPrdNum();

        try {
            WishVo wishVo = new WishVo();
            // 로그인한 사용자의 찜 목록
            List<WishVo> wishVoList;
            wishVo.setUserId(email);
            wishVo.setProductNum(productNum);
            if (userType == UserType.User.getValue()) {
                wishVoList=wishMapper.selectWishList(email);
                if(wishVoList.size()!=0){ // 이미 해당 상품 찜이 되어있는지 check
                  for(WishVo wishVo1 : wishVoList) {
                      if(wishVo1.getProductNum()==productNum&&wishVo1.getUserId().equals(email)) {
                          res.setCode(ResultCode.DuplicateWish);
                          return res;
                      }
                  }
                }

                int result = wishMapper.insertWishProduct(wishVo);
                if (result == 1) {
                    res.setResult(result);
                    res.setCode(ResultCode.Success);
                } else {
                    res.setCode(ResultCode.InternalServerError);
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
