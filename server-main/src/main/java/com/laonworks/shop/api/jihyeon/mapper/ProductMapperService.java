package com.laonworks.shop.api.jihyeon.mapper;

import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMapperService {

  @Autowired
  ProductMapper mapper;

  public int selectPrdtNextVal() {
    return mapper.selectPrdtNextVal();
  }

  public int selectPrdtImgNextVal() {
    return mapper.selectPrdtImgNextVal();
  }

  public int insertProduct(ProductVo vo) {
    return mapper.insertProduct(vo);
  }

  public int insertProductImage(ProductImageVo vo) {
    return mapper.insertProductImage(vo);
  }

  public int selectProductCount(String sllrId) {
    return mapper.selectProductCount(sllrId);
  }

  public List<ProductVo> selectProductList(String sllrId, int start, int end) {
    return mapper.selectProductList(sllrId, start, end);
  }

//  public List<ProductImageVo> selectProductImageList(int prdtNo) {
//    return mapper.selectProductImageList(prdtNo);
//  }
}
