package com.laonworks.shop.api.jihyeon.service;

import com.laonworks.shop.api.jihyeon.mapper.ProductMapper;
import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
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
                imageVo.prdtImgNo = productMapper.selectPrdtImgNextVal();
                imageVo.prdtNo = productVo.prdtNo;
                imageVo.prdtImgUrl = urlList.get(i);
                n += productMapper.insertProductImage(imageVo);
            }
        }
        return n;
    }
    /* 상품 수정*/
    public int modifyProduct(ProductVo productVo, List<String> urlList) {
        logger.info("Service productVo:" + productVo);
        int n = productMapper.modifyProduct(productVo);
        logger.info("Service n:" + n);
        if(n > 0) {
            for(int i = 0; i < urlList.size(); i++) {
                ProductImageVo imageVo = new ProductImageVo();
                imageVo.prdtImgNo = productMapper.selectPrdtImgNextVal();
                imageVo.prdtNo = productVo.prdtNo;
                imageVo.prdtImgUrl = urlList.get(i);
                n += productMapper.insertProductImage(imageVo);
            }
        }
        return n;
    }


}

