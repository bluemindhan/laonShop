package com.laonworks.shop.api.mapper;

import com.laonworks.shop.api.mapper.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthMapperService {

  @Autowired
  AuthMapper mapper;

  public UserVo selectUserInfo(String userId) {
    return mapper.selectUserInfo(userId);
  }

  public UserVo selectSellerInfo(String sellerId) {
    return mapper.selectSellerInfo(sellerId);
  }

  public int insertUserInfo(UserVo user) {
    return mapper.insertUserInfo(user);
  }

  public int insertSellerInfo(UserVo seller) {
    return mapper.insertSellerInfo(seller);
  }
}
