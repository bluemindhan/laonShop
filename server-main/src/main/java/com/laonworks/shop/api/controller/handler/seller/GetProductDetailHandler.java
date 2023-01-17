package com.laonworks.shop.api.controller.handler.seller;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.seller.GetProductDetailRequest;
import com.laonworks.shop.api.controller.response.seller.GetProductDetailResponse;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.jihyeon.mapper.ProductMapper;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.mapper.AuthMapper;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetProductDetailHandler extends BaseHandler {
    @Autowired
    AuthMapper authMapper;
    @Autowired
    ProductMapper productMapper;
    
    public GetProductDetailResponse execute (CustomUserDetails user, GetProductDetailRequest req) {
        GetProductDetailResponse res = new GetProductDetailResponse();
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
            if (userType == UserType.Seller.getValue()) {
                sellerVo = authMapper.selectSellerInfo(email);
            }
            int prdtNo = 0;
            ProductVo productVo = productMapper.selectProductDetail(prdtNo);
            if(productVo == null) {
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

