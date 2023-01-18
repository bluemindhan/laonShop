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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetProductDetailHandler extends BaseHandler {

    @Autowired
    ProductMapper productMapper;
    
    public GetProductDetailResponse execute (GetProductDetailRequest req) {

        GetProductDetailResponse res = new GetProductDetailResponse();
        int prdtNo = 0;

        if (req != null || !req.equals("")){
            prdtNo = (int) req.getPrdtNo();
        }

        try {
            ProductVo productVo = productMapper.selectProductDetail(prdtNo);
            if(productVo == null) {
                res.setCode(ResultCode.Failed);
                return res;
            }
            res.productVo = productVo;

        }
        catch(Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        res.setCode(ResultCode.Success);

        return res;
    }
}

