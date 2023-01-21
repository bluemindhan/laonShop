package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.item.ItemDetailHandler;
import com.laonworks.shop.api.controller.handler.item.GetItemsHandler;
import com.laonworks.shop.api.controller.handler.item.MainPageHandler;
import com.laonworks.shop.api.controller.request.item.ItemDetailRequest;
import com.laonworks.shop.api.controller.request.item.GetItemsRequest;
import com.laonworks.shop.api.controller.response.item.ItemDetailResponse;
import com.laonworks.shop.api.controller.response.item.GetItemsResponse;
import com.laonworks.shop.api.controller.response.item.MainPageResponse;
import com.laonworks.shop.api.service.CustomUserDetails;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientResponseException;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/api/v1/item")
public class ItemController extends BaseController {
    static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemDetailHandler itemDetailHandler;

    @RequestMapping(method = RequestMethod.GET , value = "item-details")
    ItemDetailResponse getItemDetail(ItemDetailRequest req,HttpServletRequest request){

        itemDetailHandler.setHttpServletRequest(request);

        return itemDetailHandler.excute(req);
    }

    // User 권한 전체 판매상품목록 조회
    @Autowired
    private GetItemsHandler getItemsHandler;

    @RequestMapping(method = RequestMethod.GET, value = "user-items")
    @ApiOperation(value = "get items")
    GetItemsResponse getItems(@AuthenticationPrincipal Authentication auth, @RequestParam(value = "pageNo", required = true) int pageNo, @RequestParam(value = "pageSize", required = true) int pageSize,  HttpServletRequest request) {
        getItemsHandler.setHttpServletRequest(request);
        System.out.println("Authoriztion -->  " + request.getHeader("Authorization"));
        CustomUserDetails user=null;
        if (auth == null) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        user = (CustomUserDetails) auth.getPrincipal();
        if (user == null) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }

        if (!checkRoute(RequestMethod.GET, "/api/v1/item/user-items", user)) {
            throw new RestClientResponseException("", HttpStatus.UNAUTHORIZED.value(), "", null, null, null);
        }
        GetItemsRequest req =new GetItemsRequest();
        req.setPageNo(pageNo);
        req.setPageSize(pageSize);
        return getItemsHandler.execute(user,req);
    }

    @Autowired
    private MainPageHandler mainPageHandler;

    @RequestMapping(method = RequestMethod.GET, value = "mainpage")
    @ApiOperation(value = "main page")
    MainPageResponse getMainPage(){

        return mainPageHandler.excute();

    }

}
