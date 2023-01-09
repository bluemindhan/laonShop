package com.laonworks.shop.api.controller.handler.seller;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.classes.AwsService;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.seller.ModifyProductRequest;
import com.laonworks.shop.api.controller.response.seller.ModifyProductResponse;
import com.laonworks.shop.api.jihyeon.mapper.ProductMapper;
import com.laonworks.shop.api.jihyeon.service.ProductService;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.mapper.AuthMapper;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component // 재사용이 가능한 독립된 모듈
public class ModifyProductHandler extends BaseHandler {
    @Autowired
    AuthMapper authMapper;
    @Autowired
    AwsService awsService;
    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;
    
    public ModifyProductResponse execute (CustomUserDetails user, ModifyProductRequest req) {
        ModifyProductResponse res = new ModifyProductResponse();

        if(user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        if(req.invalid()) {
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        String email = user.getUsername();
        int userType = user.getUserType();

        try {
            UserVo sellerVo = authMapper.selectSellerInfo(email);
            if(sellerVo == null) {
                res.setCode(ResultCode.NotFoundSeller);
                return res;
            }
            int ProductNo = 0;
            if(req != null || !req.equals("")){
                ProductNo = (int) req.getProductNum();
            }
            res.productVo.set(sellerVo);
            res.productVo.setPrdtNo(ProductNo);
            res.result=productMapper.modifyProduct(res.productVo);
            if(res.result == 0) {
                res.setCode(ResultCode.InternalServerError);
                return res;
            }
            res.setCode(ResultCode.Success);
            return res;
        }
        catch(Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}

