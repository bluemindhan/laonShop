package com.laonworks.shop.api.controller.handler.seller;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.classes.AwsService;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.seller.ModifyProductRequest;
import com.laonworks.shop.api.controller.response.seller.ModifyProductResponse;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.jihyeon.mapper.ProductMapper;
import com.laonworks.shop.api.jihyeon.service.ProductService;
import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.mapper.AuthMapper;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component // 재사용이 가능한 독립된 모듈
public class ModifyProductHandler extends BaseHandler {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    AuthMapper authMapper;

    @Autowired
    AwsService awsService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductMapper productMapper;
    
    public ModifyProductResponse execute (CustomUserDetails user, ModifyProductRequest req) {
        int delete = 0;
        ModifyProductResponse res = new ModifyProductResponse();
        if(user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        if(req.invalid()) { // 파라미터 유효하지 않음
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        String email = user.getUsername();
        int userType = user.getUserType();
        int productNum = req.getProductNum();
        String productName = req.getProductName();
        String productDesc = req.getProductDesc();
        int productPrice = req.getProductPrice();

        try {
            UserVo sellerVo;
            if(userType == UserType.Seller.getValue()) {
                sellerVo = authMapper.selectSellerInfo(email);
            }
            else{
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
            ProductVo productVo = new ProductVo();
            // 파라미터 setting
            productVo.setSllrId(sellerVo.userId);
            productVo.setPrdtNo(productNum);
            productVo.setPrdtNm(productName);
            productVo.setPrdtDesc(productDesc);
            productVo.setPrdtPrce(productPrice);
            logger.info("1. productVo 확인" + productVo);
            delete = productMapper.deleteProductImg(productNum);
            List<String> urlList = awsService.uploadProductImageList(req.imageList);

            int n = productService.modifyProduct(productVo, urlList);
            logger.info("2. n" + n);
            if(n == 0) {
                res.setCode(ResultCode.InternalServerError);
                res.result = 0;
                return res;
            }
            res.result = 1;
            res.setCode(ResultCode.Success);
            return res;
        }
        catch(Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}

