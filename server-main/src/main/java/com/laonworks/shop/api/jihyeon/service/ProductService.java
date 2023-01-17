package com.laonworks.shop.api.jihyeon.service;

import com.laonworks.shop.api.jihyeon.mapper.ProductMapper;
import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    @Autowired
    ProductMapper productMapper;

    /* 상품 등록 */
    @Transactional(isolation=Isolation.DEFAULT)
    public int addProduct (ProductVo productVo, List<String> urlList) {
        productVo.prdtNo = productMapper.selectPrdtNextVal();
        int n = productMapper.insertProduct(productVo);
        if(n > 0) {
            for(int i = 0; i < urlList.size(); i++) {
                ProductImageVo imageVo = new ProductImageVo();
                imageVo.prdtNo = productVo.prdtNo;
                imageVo.prdtImgUrl = urlList.get(i);
                n += productMapper.insertProductImage(imageVo);
            }
        }
        return n;
    }

    /*  이미지를 뺀 상품 등록 */
//    @Transactional(isolation=Isolation.DEFAULT) // Transaction의 isolation Level. 별도로 정의하지 않으면 DB의 Isolation Level을 따름. = 데이터베이스에 의존
//    public int addProduct (ProductVo productVo) {
//        productVo.prdtNo = productMapper.selectPrdtNextVal();
//        int n = productMapper.insertProduct(productVo);
//        return n;
//    }

    /* 상품 수정*/
    public int modifyProduct(ProductVo productVo, List<String> urlList) {
        int n = productMapper.modifyProduct(productVo);
        if(n > 0) {
            for(int i = 0; i < urlList.size(); i++) {
                ProductImageVo imageVo = new ProductImageVo();
                imageVo.prdtImgNo = productMapper.selectPrdtImgNextVal();
                imageVo.prdtNo = productVo.prdtNo;
                imageVo.prdtImgUrl = urlList.get(i);
                n += productMapper.modifyProductImage(imageVo);
            }
        }
        return n;
    }


}

