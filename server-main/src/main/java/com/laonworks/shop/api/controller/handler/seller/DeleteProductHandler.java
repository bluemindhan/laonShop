package com.laonworks.shop.api.controller.handler.seller;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.classes.AwsService;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.seller.DeleteProductRequest;
import com.laonworks.shop.api.controller.response.seller.DeleteProductResponse;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.jihyeon.mapper.ProductMapper;
import com.laonworks.shop.api.jihyeon.service.ProductService;
import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.mapper.AuthMapper;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component // 재사용이 가능한 독립된 모듈
public class DeleteProductHandler extends BaseHandler {
    @Autowired
    AuthMapper authMapper;
    @Autowired
    ProductMapper productMapper;
    
    public DeleteProductResponse execute (CustomUserDetails user, DeleteProductRequest req) {
        DeleteProductResponse res = new DeleteProductResponse();
        ProductVo productVo = new ProductVo();
        ProductImageVo productImageVo = new ProductImageVo();
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
        int productNum = req.getProductNum();

        try {
            UserVo sellerVo;
            if(userType == UserType.Seller.getValue()) {
                sellerVo = authMapper.selectSellerInfo(email);
            }
            else{
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
            productVo.setSllrId(user.getUsername());
            productVo.setPrdtNo(req.productNum);
            productImageVo.setPrdtNo(req.productNum);
            productMapper.deleteProductImg(productNum);
            productMapper.deleteProduct(productVo);
            if(sellerVo == null) {
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

