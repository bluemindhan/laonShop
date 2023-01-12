package com.laonworks.shop.api.controller.handler.user;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.user.PutProfileRequest;
import com.laonworks.shop.api.controller.response.user.PutProfileResponse;
import com.laonworks.shop.api.controller.vo.UserType;
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
        if(user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        String email = user.getUsername();
        int userType = user.getUserType();
        try {
            UserVo userVo;
            if(userType== UserType.User.getValue()){

            }
            else {
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
        }catch (Exception e){
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
