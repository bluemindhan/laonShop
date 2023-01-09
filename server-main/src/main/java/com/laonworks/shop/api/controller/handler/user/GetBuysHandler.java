package com.laonworks.shop.api.controller.handler.user;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.user.GetBuysRequest;
import com.laonworks.shop.api.controller.response.user.GetBuysResponse;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.mapper.UserMapper;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;

@Slf4j
@Component
public class GetBuysHandler extends BaseHandler {
    @Autowired
    UserMapper userMapper;

    public GetBuysResponse execute(CustomUserDetails user, GetBuysRequest req){
        GetBuysResponse res = new GetBuysResponse();
        if(user== null){
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        String email=user.getUsername();
        int userType=user.getUserType();
        try{
            if(userType== UserType.User.getValue()){
                res.buysList=userMapper.selectBuyList(email);
            }else {
                res.setCode(ResultCode.Unauthorized);
                return  res;
            }
        }
        catch (Exception e){
            res.setCode(ResultCode.InternalServerError);
        }
        res.setCode(ResultCode.Success);
        return res;
    }
}
