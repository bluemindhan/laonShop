package com.laonworks.shop.api.controller.handler.user;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.response.user.GetItemsResponse;
import com.laonworks.shop.api.controller.vo.ProductInfo;
import com.laonworks.shop.api.mapper.UserMapper;
import com.laonworks.shop.api.mapper.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GetItemsHandler extends BaseHandler{

    @Autowired
    UserMapper userMapper;

    public GetItemsResponse execute() {
        log.info("GetItemsResponse Start");
        GetItemsResponse res = new GetItemsResponse();
        try {
            res.products= userMapper.selectProductList();
            log.info("result.size():"+ res.products.size());
            if(res.products!=null) {
                res.setCode(ResultCode.Success);
                return res;
            }else {
                res.setCode(ResultCode.Failed);
            }
        } catch (Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}
