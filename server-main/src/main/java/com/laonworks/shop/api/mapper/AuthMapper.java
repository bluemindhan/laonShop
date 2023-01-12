package com.laonworks.shop.api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.laonworks.shop.api.mapper.vo.*;

import java.util.HashMap;

@Mapper
public interface AuthMapper {

  UserVo selectUserInfo(@Param("userId") String userId);

  UserVo selectSellerInfo(@Param("sellerId") String sellerId);

  int insertUserInfo(@Param("user") UserVo user);

  int insertSellerInfo(@Param("seller") UserVo seller);

  int updateRefreshToken(@Param("map") HashMap<String,Object> map);

  int selectUserType(@Param("userid") String userid);

  String selectRefreshToken(HashMap<String, String> map);


}
