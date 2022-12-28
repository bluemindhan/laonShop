package com.laonworks.shop.api.controller.handler.api;

import com.laonworks.shop.api.controller.Constants;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.api.*;
import com.laonworks.shop.api.controller.response.api.*;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.laonworks.shop.api.controller.vo.*;
import com.laonworks.shop.api.controller.ResultCode;
import java.util.List;
import java.util.ArrayList;
@Slf4j
@Component
public class AddCountHandler extends BaseHandler {
    
    public AddCountResponse execute (AddCountRequest req) {
        AddCountResponse res = new AddCountResponse();
        log.info(String.valueOf(req.count));
        res.count = req.count + 1;
        res.setCode(ResultCode.Success);
        return res;
    }
}

