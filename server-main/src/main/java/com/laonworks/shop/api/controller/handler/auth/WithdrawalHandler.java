package com.laonworks.shop.api.controller.handler.auth;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.auth.WithdrawalRequest;
import com.laonworks.shop.api.controller.response.auth.WithdrawalResponse;
import com.laonworks.shop.api.controller.utils.CryptoUtils;
import com.laonworks.shop.api.controller.vo.UserInfo;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.mapper.AuthMapper;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import jdk.internal.vm.compiler.collections.EconomicMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class WithdrawalHandler extends BaseHandler {

    @Autowired
    AuthMapper authMapper;

    public WithdrawalResponse execute(CustomUserDetails user, WithdrawalRequest req){
       WithdrawalResponse res = new WithdrawalResponse();
       if(req.invalid()){
           res.setCode(ResultCode.InvalidParameter);
            return res;
       }
       if(user ==null){
           res.setCode(ResultCode.Unauthorized);
           return res;
       }

        // login User/Seller info setting & parameter(password) setting
        int userType =user.getUserType();
        String email= user.getUsername();
        String plainPassword = req.password;

        int result=0;
        try {
            UserVo uservo;
            System.out.println(email);
            if(userType ==UserType.User.getValue()){
                uservo = authMapper.selectUserInfo(email);
            }
            else if (userType ==UserType.Seller.getValue()) {
                uservo = authMapper.selectSellerInfo(email);
            }
            else{
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
            String encryptedPassword = CryptoUtils.encryptPassword(plainPassword,uservo.salt);
            if(!uservo.password.equals(encryptedPassword)){
                res.setCode(ResultCode.InvalidPassword);
                return res;
            }
            if (userType ==UserType.User.getValue()) {
                result =authMapper.deleteUserInfo(email);

            } else {
               result =authMapper.deleteSellerInfo(email);
            }
            return res;
        }catch (Exception e){
            res.setCode(ResultCode.InternalServerError);
        }
        res.setCode(ResultCode.Success);
        res.result=1;
        return  res;
   }

   // response 비밀번호 유효성 check
   public WithdrawalResponse validateWithdrawal(Errors errors){
       Map<String,String> errorList = new HashMap<>();
       WithdrawalResponse res = new WithdrawalResponse();

       for(FieldError error : errors.getFieldErrors()){
           String validKeyName = String.format("valid_%s",error.getField());
           errorList.put(validKeyName, error.getDefaultMessage());
       }

       res.setCode(ResultCode.Failed);
       res.setErrorList(errorList);

       return res;
   }
}
