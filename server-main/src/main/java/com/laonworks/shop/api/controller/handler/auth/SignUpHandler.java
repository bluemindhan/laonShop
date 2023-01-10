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
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

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
        String birth = req.birth;
        String gender = req.gender;
        String phone = req.phone;

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
            userVo.birth = birth;
            userVo.gender = gender;
            userVo.phone = phone;
            userVo.salt = salt;
            userVo.userType = userType;
            if(userType == UserType.User.getValue()) {
                int n = authMapper.insertUserInfo(userVo);
                if(n <= 0) {
                    res.setCode(ResultCode.InternalServerError);
                    return res;
                }
            }
            else if(userType == UserType.Seller.getValue()) {
                int n = authMapper.insertSellerInfo(userVo);
                if(n <= 0) {
                    res.setCode(ResultCode.InternalServerError);
                    return res;
                }
            }
            else {
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
            res.userInfo = new UserInfo();
            res.userInfo.set(userVo);
            Map<String,String> map = AuthUtils.generateToken(email,userType);
            res.accessToken = map.get("accessToken");

            res.userInfo.userType = userType;
            res.setCode(ResultCode.Success);
            return res;
        }
        catch(Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }

    public SignUpResponse validateSignup(Errors errors) {

        Map<String, String> errorList = new HashMap<>();
        SignUpResponse res = new SignUpResponse();

        for(FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s", error.getField());
            errorList.put(validKeyName, error.getDefaultMessage());
        }

        res.setCode(ResultCode.Failed);
        res.setErrorList(errorList);

        return res;

    }
}

