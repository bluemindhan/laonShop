package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.wish.DeleteWishHandler;
import com.laonworks.shop.api.controller.handler.wish.GetWishHandler;
import com.laonworks.shop.api.controller.handler.wish.PostWishHandler;
import com.laonworks.shop.api.controller.request.user.GetProfileRequest;
import com.laonworks.shop.api.controller.request.wish.DeleteWishRequest;
import com.laonworks.shop.api.controller.request.wish.GetWishRequest;
import com.laonworks.shop.api.controller.request.wish.PostWishRequest;
import com.laonworks.shop.api.controller.response.wish.DeleteWishResponse;
import com.laonworks.shop.api.controller.response.wish.GetWishResponse;
import com.laonworks.shop.api.controller.response.wish.PostWishResponse;
import com.laonworks.shop.api.service.CustomUserDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/user")
public class WishController extends BaseController {

    // login User 상품 찜 목록
    @Autowired
    private GetWishHandler getWishHandler;

    @RequestMapping(method = RequestMethod.GET,value = "wishs")
    @ApiOperation(value = "get wishs")
    GetWishResponse getWishs(@AuthenticationPrincipal Authentication auth, HttpServletRequest request){
        getWishHandler.setHttpServletRequest(request);
        System.out.println("Authoriztion -->  " + request.getHeader("Authorization"));
        CustomUserDetails user = null;
        if (auth == null) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        user = (CustomUserDetails) auth.getPrincipal();
        if (user == null) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        if (checkRoute(RequestMethod.POST, "/api/v1/user/wishs", user) == false) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        GetWishRequest req= new GetWishRequest();
        return getWishHandler.execute(user,req);

    }

    // login User 상품 찜 등록
    @Autowired
    private PostWishHandler postWishHandler;

    @RequestMapping(method = RequestMethod.POST,value = "wishs")
    @ApiOperation(value = "post wishs")
    PostWishResponse postWishs(@AuthenticationPrincipal Authentication auth, HttpServletRequest request,@RequestBody PostWishRequest req){
        postWishHandler.setHttpServletRequest(request);
        System.out.println("Authoriztion -->  " + request.getHeader("Authorization"));
        CustomUserDetails user = null;
        if (auth == null) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        user = (CustomUserDetails) auth.getPrincipal();
        if (user == null) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        if (checkRoute(RequestMethod.POST, "/api/v1/user/wishs", user) == false) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        return postWishHandler.execute(user,req);

    }

    // login User 상품 찜 해제
    @Autowired
    private DeleteWishHandler deleteWishHandler;

    @RequestMapping(method = RequestMethod.DELETE,value = "wishs")
    @ApiOperation(value = "delete wishs")
    DeleteWishResponse deleteWishs(@AuthenticationPrincipal Authentication auth, HttpServletRequest request, @RequestBody DeleteWishRequest req){
        deleteWishHandler.setHttpServletRequest(request);
        System.out.println("Authoriztion -->  " + request.getHeader("Authorization"));
        CustomUserDetails user =null;
        if (auth == null) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        user = (CustomUserDetails) auth.getPrincipal();
        if (user == null) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        if (checkRoute(RequestMethod.DELETE,"/api/v1/user/wishs", user) == false) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        return deleteWishHandler.execute(user,req);
    }


}
