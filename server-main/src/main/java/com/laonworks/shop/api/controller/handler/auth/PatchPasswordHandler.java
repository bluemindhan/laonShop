package com.laonworks.shop.api.controller.handler.auth;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.auth.PatchPasswordRequest;
import com.laonworks.shop.api.controller.response.auth.PatchPasswordResponse;
import com.laonworks.shop.api.controller.utils.CryptoUtils;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.mapper.AuthMapper;
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
public class PatchPasswordHandler extends BaseHandler {

    @Autowired
    AuthMapper authMapper;

    public PatchPasswordResponse execute(CustomUserDetails user, PatchPasswordRequest req) {
        HashMap<String, Object> map = new HashMap<>();
        PatchPasswordResponse res = new PatchPasswordResponse();

        if (!req.newPassword.equals(req.confirmPassword)) {
            res.setCode(ResultCode.InvalidPassword);
            return res;
        }
        if (!req.invalid()) {
            if (user == null) {
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
        } else {
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }

        // parameter Setting
        int userType = user.getUserType();
        String userId = user.getUsername();
        String prevPassword = req.password; // 기존 비밀번호
        String newPassword = req.newPassword; // 새로운 비밀번호
        int result = 0; // mapper 결과값

        try {
            UserVo userVo;
            if (userType == UserType.User.getValue()) {
                userVo = authMapper.selectUserInfo(userId);
            } else if (userType == UserType.Seller.getValue()) {
                userVo = authMapper.selectSellerInfo(userId);
            } else {
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
            String encryptedPassword = CryptoUtils.encryptPassword(prevPassword, userVo.salt);
            if (!userVo.password.equals(encryptedPassword)) { // 기존 비밀번호 일치 여부 확인
                res.setCode(ResultCode.InvalidPassword);
                return res;
            }
            String salt = CryptoUtils.generateSalt();
            String encryptedNewPassword = CryptoUtils.encryptPassword(newPassword, salt); // 새로운 비밀번호 암호화

            map.put("userType", userType);
            map.put("userId", userId);
            map.put("salt",salt);
            map.put("password", encryptedNewPassword);

            if (userVo.userId.equals(userId)) {
                result = authMapper.updatePassword(map);
                if(result>0){
                    res.setResult(result); // 성공하면 response의 result에 결과값 Setting
                    res.setCode(ResultCode.Success);
                }
                else{
                    res.setCode(ResultCode.DbFailed);
                }
            }
            else {
                res.setCode(ResultCode.Unauthorized);
                return res;
            }

        } catch (Exception e) {
            res.setCode(ResultCode.InternalServerError);
            return res;
        }
        return res;
    }


   public static PatchPasswordResponse validatePatchPassword(Errors errors) {
        Map<String, String> errorList = new HashMap<>();
        PatchPasswordResponse res = new PatchPasswordResponse();

        for(FieldError error:errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s",error.getField());
            errorList.put(validKeyName, error.getDefaultMessage());
        }

        res.setCode(ResultCode.Failed);
        res.setErrorList(errorList);
        return res;
    }

}
