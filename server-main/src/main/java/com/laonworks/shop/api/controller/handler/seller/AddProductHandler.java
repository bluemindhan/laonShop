package com.laonworks.shop.api.controller.handler.seller;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.classes.AwsService;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.seller.AddProductRequest;
import com.laonworks.shop.api.controller.response.seller.AddProductResponse;
import com.laonworks.shop.api.jihyeon.service.ProductService;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.mapper.AuthMapper;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component // 재사용이 가능한 독립된 모듈
public class AddProductHandler extends BaseHandler {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    AuthMapper authMapper;
    @Autowired
    AwsService awsService;
    @Autowired
    ProductService productService;
    
    public AddProductResponse execute (CustomUserDetails user, AddProductRequest req) {
        AddProductResponse res = new AddProductResponse();
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
            ProductVo productVo = new ProductVo();
            productVo.prdtNm = req.productName;
            productVo.prdtDesc = req.productDesc;
            productVo.prdtPrce = req.productPrice;
            productVo.sllrId = sellerVo.userId;
            List<String> urlList = awsService.uploadProductImageList(req.imageList);

            int n = productService.addProduct(productVo,urlList);
            if(n == 0) {
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

