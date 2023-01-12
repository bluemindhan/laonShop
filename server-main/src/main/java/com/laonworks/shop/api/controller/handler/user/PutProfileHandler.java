package com.laonworks.shop.api.controller.handler.user;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.user.PutProfileRequest;
import com.laonworks.shop.api.controller.response.user.PutProfileResponse;
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
public class PutProfileHandler extends BaseHandler {
    @Autowired
    UserMapper userMapper;

    public PutProfileResponse execute(CustomUserDetails user, PutProfileRequest req){
        PutProfileResponse res =new PutProfileResponse();
        if(req.invalid()){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        if(user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }

        try {
            // parameter setting
            int userType = user.getUserType();
            String userId=user.getUsername();
            String email = req.email;
            String password = req.password;
            String name= req.name;
            String birth =req.birth;
            String gender = req.gender;
            String phone = req.phone;
            String salt = CryptoUtils.generateSalt();
            String encryptedPassword = CryptoUtils.encryptPassword(password,salt);

            UserVo userVo = new UserVo();
//            userVo.userId = updateEmail;
//            userVo.email = updateEmail;
            userVo.password = encryptedPassword;
            userVo.name = name;
            userVo.email= email;
            userVo.birth = birth;
            userVo.gender = gender;
            userVo.phone = phone;
            userVo.salt = salt;
            userVo.userType = userType;

            HashMap<String,Object> map = new HashMap<>();
            map.put("userId",userId);
            map.put("user",userVo);

            if(userType== UserType.User.getValue()){ // login User(buyer) 프로필 수정
                int result = userMapper.updateUserInfo(map);

                if(result<=0){
                    res.setCode(ResultCode.InternalServerError);
                    return res;
                }
            }
            else if(userType == UserType.Seller.getValue()){ // login User(seller) 프로필 수정
                int result = userMapper.updateSellerInfo(map);
                if(result<=0){
                    res.setCode(ResultCode.InternalServerError);
                    return res;
                }
            }
            else{
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
            res.userInfo=new UserInfo();
            res.userInfo.set(userVo);
            res.userInfo.userType = userType;
            res.setCode(ResultCode.Success);
            return res;
        }
        catch (Exception e){
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }

    public static PutProfileResponse validatePutProfile(Errors errors) {
        Map<String, String> errorList = new HashMap<>();
        PutProfileResponse res = new PutProfileResponse();

        for(FieldError error : errors.getFieldErrors()){
            String validKeyName= String.format("valid_%s",error.getField());
            errorList.put(validKeyName, error.getDefaultMessage());
        }

        res.setCode(ResultCode.Failed);
        res.setErrorList(errorList);

        return res;
    }


}
