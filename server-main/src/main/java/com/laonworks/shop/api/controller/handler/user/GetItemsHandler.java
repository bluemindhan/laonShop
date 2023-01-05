package com.laonworks.shop.api.controller.handler.user;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.user.GetItemsRequest;
import com.laonworks.shop.api.controller.response.user.GetItemsResponse;
import com.laonworks.shop.api.controller.vo.ProductInfo;
import com.laonworks.shop.api.controller.vo.UserInfo;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.mapper.ItemMapper;
import com.laonworks.shop.api.mapper.UserMapper;
import com.laonworks.shop.api.mapper.vo.ProductVo;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GetItemsHandler extends BaseHandler {
    @Autowired
    UserMapper userMapper;

    public GetItemsResponse execute() {
        GetItemsResponse res = new GetItemsResponse();

        try {
            List<ProductVo> productList;
            productList = userMapper.selectProductList();
            for (ProductVo productVo : productList) {
                res.setProducts((List<ProductInfo>) productVo);
            }
            res.setCode(ResultCode.Success);
            return res;

        } catch (Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}
