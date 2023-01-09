package com.laonworks.shop.api.controller.handler.wish;

import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.response.wish.PostWishResponse;
import com.laonworks.shop.api.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Login User 찜상품 등록 Handler
@Slf4j
@Component
public class PostWishHandler extends BaseHandler {

    @Autowired
    UserMapper userMapper;

    public PostWishResponse execute(){
        log.info("PostWishResponse start");
        PostWishResponse res = new PostWishResponse();
        return  res;
    }
}
