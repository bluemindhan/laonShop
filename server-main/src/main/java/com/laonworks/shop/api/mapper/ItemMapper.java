package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.controller.vo.ItemInfo;
import org.apache.ibatis.annotations.Mapper;
import com.laonworks.shop.api.mapper.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    ItemVo selectItemDetailInfo(@Param("pdNum") int productNo);

    List<ItemVo> selectMainPage();
}
