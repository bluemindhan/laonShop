package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.auth.SignInHandler;
import com.laonworks.shop.api.controller.request.auth.SignInRequest;
import com.laonworks.shop.api.controller.response.auth.SignInResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class AuthControllerTest {

    @Autowired
    SignInHandler signInHandler;

    SignInRequest request = null;
    SignInResponse response = null;

    @BeforeEach
    void beforeEach(){
        // 테스트를 시작할 때마다 SignInRequest req 새로 생성
        request = new SignInRequest();
        SignInResponse response = new SignInResponse();
        request.setEmail("123");
        request.setUserType(1);
        request.setPassword("123");
    }

    @AfterEach
    void afterEach(){

    }
    @Test
    @DisplayName("1. 로그인 테스트")
    void loginTest(){

        response = signInHandler.execute(request);
        assertThat(response.getUserInfo().getEmail()).isEqualTo(request.getEmail());
        assertThat(response.getUserInfo().getUserType()).isEqualTo(request.getUserType());
    }

}