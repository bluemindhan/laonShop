package com.laonworks.shop.api.controller.handler.seller;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.seller.GetProductDetailRequest;
import com.laonworks.shop.api.controller.response.seller.GetProductDetailResponse;
import com.laonworks.shop.api.controller.utils.AuthUtils;
import com.laonworks.shop.api.jihyeon.mapper.ProductMapper;
import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetProductDetailHandler extends BaseHandler {

    @Autowired
    ProductMapper productMapper;
    
    public GetProductDetailResponse execute (GetProductDetailRequest req) {

        GetProductDetailResponse res = new GetProductDetailResponse();
        int prdtNo = 0; int num = 0;
        String userPk;

        if (req != null || !req.equals("")){
            prdtNo = (int) req.getPrdtNo();
        }

        //로그인 여부
        if(req.accessToken != null){
            userPk = AuthUtils.parseToken(req.accessToken);
            String tokens[] = userPk.split(":");
            String userid = tokens[0];
        }

        try {
            ProductVo productVo = productMapper.selectProductDetail(prdtNo);
            List<ProductImageVo> productImageVo = productMapper.selectProductDetailImage(prdtNo);

            if(productVo == null) {
                res.setCode(ResultCode.Failed);
                return res;
            }
            res.productVo = productVo;
            res.productImageVoList = productImageVo;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            res.setCode(ResultCode.InternalServerError);
        }
        res.setCode(ResultCode.Success);

        return res;
    }
}

