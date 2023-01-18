package com.laonworks.shop.api.controller.handler.user;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.user.PatchProfileRequest;
import com.laonworks.shop.api.controller.response.user.PatchProfileResponse;
import com.laonworks.shop.api.controller.utils.CryptoUtils;
import com.laonworks.shop.api.controller.vo.UserInfo;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.mapper.AuthMapper;
import com.laonworks.shop.api.mapper.UserMapper;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class PatchProfileHandler extends BaseHandler {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthMapper authMapper;

    public PatchProfileResponse execute(CustomUserDetails user, PatchProfileRequest req){
        PatchProfileResponse res =new PatchProfileResponse();

        if(req.invalid()){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        if(user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }

        // parameter setting
        int userType = user.getUserType();
        String userId=user.getUsername();
        String email = req.email;
        String plainPassword = req.password;
        String name= req.name;
        String birth =req.birth;
        String gender = req.gender;
        String phone = req.phone;
        int result=0;


        try {
            UserVo userVo;
            if(userType== UserType.User.getValue()){ // login User(buyer) 프로필 get
                userVo = authMapper.selectUserInfo(userId);
                if(userVo == null) {
                    res.setCode(ResultCode.NotFoundUser);
                    return res;
                }
            }
            else if(userType == UserType.Seller.getValue()){ // login User(seller) 프로필 get
                userVo = authMapper.selectSellerInfo(userId);
                if(userVo == null) {
                    res.setCode(ResultCode.NotFoundUser);
                    return res;
                }
            }
            else{
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
            String encryptedPassword = CryptoUtils.encryptPassword(plainPassword,userVo.salt);
            if(!userVo.password.equals(encryptedPassword)){ // 로그인 user 비밀번호 확인
                res.setCode(ResultCode.InvalidPassword);
                return res;
            }
            userVo.setEmail(email);
            userVo.setName(name);
            userVo.setBirth(birth);
            userVo.setGender(gender);
            userVo.setPhone(phone);

            HashMap<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("user",userVo);
            
            if(userType== UserType.User.getValue()){
                result = userMapper.updateUserInfo(map);
            }
            else {
                result = userMapper.updateSellerInfo(map);
            }
            res.userInfo=new UserInfo();
            res.userInfo.set(userVo);
            res.setCode(ResultCode.Success);
            return res;
        }
        catch (Exception e){
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }

    public static PatchProfileResponse validatePutProfile(Errors errors) {
        Map<String, String> errorList = new HashMap<>();
        PatchProfileResponse res = new PatchProfileResponse();

        for(FieldError error : errors.getFieldErrors()){
            String validKeyName= String.format("valid_%s",error.getField());
            errorList.put(validKeyName, error.getDefaultMessage());
        }

        res.setCode(ResultCode.Failed);
        res.setErrorList(errorList);

        return res;
    }


}
