package com.laonworks.shop.api.controller.handler.user;

import com.laonworks.shop.api.controller.Constants;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.user.*;
import com.laonworks.shop.api.controller.response.user.*;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.laonworks.shop.api.controller.vo.*;
import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.mapper.vo.*;
import com.laonworks.shop.api.mapper.AuthMapper;
import java.util.List;
import java.util.ArrayList;
@Slf4j
@Component
public class GetProfileHandler extends BaseHandler {
    @Autowired
    AuthMapper authMapper;
    
    public GetProfileResponse execute (CustomUserDetails user, GetProfileRequest req) {
        GetProfileResponse res = new GetProfileResponse();
        if(user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        String email = user.getUsername();
        int userType = user.getUserType();
        try {
            UserVo userVo ;
            if(userType == UserType.User.getValue()) {
                userVo = authMapper.selectUserInfo(email);
            }
            else if(userType == UserType.Seller.getValue()) {
                userVo = authMapper.selectSellerInfo(email);
            }
            else {
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
            res.userInfo = new UserInfo();
            res.userInfo.set(userVo);
            res.userInfo.userType = userType;
            res.setCode(ResultCode.Success);
            return res;
        }
        catch(Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}

