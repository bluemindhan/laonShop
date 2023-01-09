package com.laonworks.shop.api.controller.handler.seller;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.seller.GetProductListRequest;
import com.laonworks.shop.api.controller.response.seller.GetProductListResponse;
import com.laonworks.shop.api.controller.vo.ProductInfo;
import com.laonworks.shop.api.jihyeon.mapper.ProductMapper;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.mapper.AuthMapper;
import com.laonworks.shop.api.mapper.vo.UserVo;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GetProductListHandler extends BaseHandler {
    @Autowired
    AuthMapper authMapper;
    @Autowired
    ProductMapper productMapper;
    
    public GetProductListResponse execute (CustomUserDetails user, GetProductListRequest req) {
        GetProductListResponse res = new GetProductListResponse();
        if(user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        if(req.invalid()) {
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        int pageNo = req.pageNo;
        int pageSize = req.pageSize;
        String email = user.getUsername();
        int userType = user.getUserType();
        try {
            UserVo sellerVo = authMapper.selectSellerInfo(email);
            if(sellerVo == null) {
                res.setCode(ResultCode.NotFoundSeller);
                return res;
            }
            int totalCount = productMapper.selectProductCount(sellerVo.userId);
            int begin = (pageNo - 1) * pageSize;
            int end = begin + pageSize;
            List<ProductVo> productVos = productMapper.selectProductList(sellerVo.userId, begin, end);
            if(productVos == null) {
                res.setCode(ResultCode.InternalServerError);
                return res;
            }
            res.productList = new ArrayList<ProductInfo>();
            productVos.forEach(elem -> {
                ProductInfo item = new ProductInfo();
                item.set(elem);
                res.productList.add(item);
            });
            res.totalCount = totalCount;
            res.pageNo = pageNo;
            res.pageSize = pageSize;
            res.setCode(ResultCode.Success);
            return res;
        }
        catch(Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}

