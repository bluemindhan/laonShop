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
import java.util.List;
import java.util.ArrayList;
@Slf4j
@Component
public class SendItemsHandler extends BaseHandler {
    
    public SendItemsResponse execute (SendItemsRequest req) {
        SendItemsResponse res = new SendItemsResponse();
        for(int i = 0; i < req.items.size(); i++) {
            log.info(req.items.get(i).name);
            log.info(String.valueOf(req.items.get(i).itemId));
        }
        for(int i = 0; i < req.names.size(); i++) {
            log.info(req.names.get(i));
        }
        ItemInfo item = new ItemInfo();
        item.name = "test";
        item.value = 100;
        res.items.add(item);
        res.items.add(new ItemInfo());
        res.setCode(ResultCode.Success);
        return res;
    }
}

