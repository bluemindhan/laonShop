package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.controller.vo.ItemInfo;
import org.apache.ibatis.annotations.Mapper;
import com.laonworks.shop.api.mapper.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    ItemVo selectItemDetailInfo(@Param("pdNum") int productNo);

<<<<<<< HEAD
    List<String> selectCommentList(@Param("pdNum") int productNo);

    List<ProductVo> selectProductList(); // 전체 상품 목록 select
=======
    List<ItemVo> selectMainPage();
>>>>>>> d30866524399b4551e43ea73e42a8e70113c2fc2
}
