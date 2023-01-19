package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.cart.CartInHandler;
import com.laonworks.shop.api.controller.request.cart.CartInRequest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartControllerTest {

    @Autowired
    CartInHandler cartInHandler;

    CartInRequest req = null;

    @BeforeEach
    void beforeEach(){
        // 테스트를 시작할 때마다 CartInRequest req 새로 생성

    }



}