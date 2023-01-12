package com.laonworks.shop.api.controller.handler.auth;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.auth.WithdrawalRequest;
import com.laonworks.shop.api.controller.response.auth.WithdrawalResponse;
import com.laonworks.shop.api.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WithdrawalHandler extends BaseHandler {

    @Autowired
    AuthMapper authMapper;

    public WithdrawalResponse execute(WithdrawalRequest req){
        WithdrawalResponse res = new WithdrawalResponse();
        if(req.invalid()){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }

    }
}
