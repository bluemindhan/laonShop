package com.laonworks.shop.api.controller.handler.seller;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.classes.AwsService;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.seller.ModifyProductRequest;
import com.laonworks.shop.api.controller.response.seller.ModifyProductResponse;
import com.laonworks.shop.api.controller.vo.UserType;
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
    ProductMapper productMapper;
    
    public ModifyProductResponse execute (CustomUserDetails user, ModifyProductRequest req) {
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
            int result=0;
            if(userType == UserType.Seller.getValue()) {
                sellerVo = authMapper.selectSellerInfo(email);
            }
            else{
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
            ProductVo productVo = new ProductVo();
            // 파라미터 setting
            productVo.setSllrId(email);
            productVo.setPrdtNo(productNum);
            productVo.setPrdtNm(productName);
            productVo.setPrdtDesc(productDesc);
            productVo.setPrdtPrce(productPrice);

            if(req != null || !req.equals("")){
               result=productMapper.modifyProduct(productVo);
               log.info("result:" +result);
               res.setResult(result);
               res.setCode(ResultCode.Success);
            }
            else if(result == 0) {
                res.setCode(ResultCode.InternalServerError);
                return res;
            }
        }
        catch(Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}

