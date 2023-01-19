package com.laonworks.shop.api.controller.handler.auth;

import com.laonworks.shop.api.controller.Constants;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.auth.*;
import com.laonworks.shop.api.controller.response.auth.*;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.laonworks.shop.api.controller.vo.*;
import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.mapper.vo.*;
import com.laonworks.shop.api.mapper.AuthMapper;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.laonworks.shop.api.controller.utils.CryptoUtils;
import com.laonworks.shop.api.controller.utils.AuthUtils;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class SignInHandler extends BaseHandler {
    @Autowired
    AuthMapper authMapper;
    
    public SignInResponse execute (SignInRequest req) {
        SignInResponse res = new SignInResponse();
        HashMap<String,Object> map = new HashMap<>();
        Map<String,String> tokens = new HashMap<>();

        if(req.invalid()) {
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        String email = req.email;
        String plainPassword = req.password;
        int userType = req.userType;
        try {
            UserVo userVo ;
            if(userType == UserType.User.getValue()) {
                userVo = authMapper.selectUserInfo(email);
                if(userVo == null) {
                    res.setCode(ResultCode.NotFoundUser);
                    return res;
                }
            }
            else if(userType == UserType.Seller.getValue()) {
                userVo = authMapper.selectSellerInfo(email);
                if(userVo == null) {
                    res.setCode(ResultCode.NotFoundSeller);
                    return res;
                }
            }
            else {
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
            String encryptedPassword = CryptoUtils.encryptPassword(plainPassword,userVo.salt);
            if(!userVo.password.equals(encryptedPassword)) {
                res.setCode(ResultCode.InvalidPassword);
                return res;
            }
            userVo.setUserType(userType);
            res.userInfo = new UserInfo();
            res.userInfo.set(userVo);
//            res.accessToken = AuthUtils.generateToken(email,userType);
//            res.refreshToken = AuthUtils.createRefresh(reponse);
            tokens = AuthUtils.generateToken(email,userType);
            res.accessToken = tokens.get("accessToken");
            res.refreshToken = tokens.get("refreshToken");

            map.put("refresh",res.refreshToken);
            map.put("userVo",userVo);
            int rowCnt = authMapper.updateRefreshToken(map);
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

