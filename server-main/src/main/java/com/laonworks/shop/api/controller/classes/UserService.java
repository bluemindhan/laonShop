package com.laonworks.shop.api.controller.classes;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.laonworks.shop.api.mapper.AuthMapper;
import lombok.extern.slf4j.Slf4j;
import com.laonworks.shop.api.controller.vo.*;
import com.laonworks.shop.api.mapper.vo.*;

@Slf4j
@Service
public class UserService {
    @Autowired
    AuthMapper authMapper;
    
    @Transactional(isolation=Isolation.DEFAULT)
    public UserInfo getUserInfo (String email) {
        UserInfo userInfo = new UserInfo();
        UserVo userVo = authMapper.selectUserInfo(email);
        userInfo.set(userVo);
        return userInfo;
    }
}

