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
import java.util.List;
import java.util.ArrayList;
import com.laonworks.shop.api.controller.utils.CryptoUtils;
import com.laonworks.shop.api.controller.utils.AuthUtils;
@Slf4j
@Component
public class SignUpHandler extends BaseHandler {
    @Autowired
    AuthMapper authMapper;
    
    public SignUpResponse execute (SignUpRequest req) {
        SignUpResponse res = new SignUpResponse();
        if(req.invalid()) {
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        String email = req.email;
        String password = req.password;
        String name = req.name;
        int userType = req.userType;
        try {
            if(userType == UserType.User.getValue()) {
                UserVo userVo = authMapper.selectUserInfo(email);
                if(userVo != null) {
                    res.setCode(ResultCode.DuplicateUser);
                    return res;
                }
            }
            else if(userType == UserType.Seller.getValue()) {
                UserVo userVo = authMapper.selectSellerInfo(email);
                if(userVo != null) {
                    res.setCode(ResultCode.DuplicateSeller);
                    return res;
                }
            }
            else {
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
            String salt = CryptoUtils.generateSalt();
            String encryptedPassword = CryptoUtils.encryptPassword(password,salt);
            UserVo userVo = new UserVo();
            userVo.userId = email;
            userVo.email = email;
            userVo.password = encryptedPassword;
            userVo.name = name;
            userVo.salt = salt;
            if(userType == UserType.User.getValue()) {
                authMapper.insertUserInfo(userVo);
            }
            else if(userType == UserType.Seller.getValue()) {
                authMapper.insertSellerInfo(userVo);
            }
            else {
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
            res.userInfo = new UserInfo();
            res.userInfo.set(userVo);
            res.accessToken = AuthUtils.generateToken(email,userType);
            res.setCode(ResultCode.Success);
            return res;
        }
        catch(Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}

