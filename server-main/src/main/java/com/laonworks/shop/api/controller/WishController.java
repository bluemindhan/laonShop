package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.wish.PostWishHandler;
import com.laonworks.shop.api.controller.response.wish.PostWishResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class WishController extends BaseController {

    @Autowired
    private PostWishHandler postWishHandler;

    // Login User 상품 찜 등록
    @RequestMapping(method = RequestMethod.POST,value = "wishs")
    @ApiOperation(value = "post wishs")
    PostWishResponse postWishs(){

    }
}
