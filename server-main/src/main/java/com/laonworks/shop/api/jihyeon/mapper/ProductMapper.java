package com.laonworks.shop.api.jihyeon.mapper;

import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.mapper.vo.ItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ProductMapper {

  int selectPrdtNextVal();

  int selectPrdtImgNextVal();

  int insertProduct(@Param("vo") ProductVo vo);

  int insertProductImage(@Param("vo") ProductImageVo vo);

  int selectProductCount(@Param("sllrId") String sllrId);

  List<ProductVo> selectProductList(@Param("sllrId") String sllrId, @Param("start") int start, @Param("end") int end); // 상품 리스트 불러오기

  List<ProductImageVo> selectProductImageList(@Param("prdtNo") int prdtNo);

  int modifyProduct(@Param("vo") ProductVo vo);  // 등록 상품 수정

  int modifyProductImage(ProductImageVo vo); // 등록 상품 이미지 수정

  int deleteProduct(int prdtNo);           // 등록 상품 삭제

}
