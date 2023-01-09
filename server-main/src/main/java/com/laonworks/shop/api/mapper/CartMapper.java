package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.mapper.vo.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CartMapper {
    List<CartVo> selectCartInfo(@Param("userid") String userid);

    CartVo findByUseridAndProductNo(@Param("map") HashMap<String, Object> map);

    int insertCartInfo(@Param("user") CartVo vo);

    int deleteCart(@Param("user") CartVo vo);

    int updateCart(@Param("user") CartVo vo);

}
