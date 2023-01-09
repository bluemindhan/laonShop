package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.item.ItemDetailHandler;
import com.laonworks.shop.api.controller.request.item.ItemDetailRequest;
import com.laonworks.shop.api.controller.response.item.ItemDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/api/v1/item")
public class ItemController extends BaseController {
    static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemDetailHandler itemDetailHandler;

    @RequestMapping(method = RequestMethod.POST , value = "item-details")
    ItemDetailResponse getItemDetail(@RequestBody ItemDetailRequest req,HttpServletRequest request){

        itemDetailHandler.setHttpServletRequest(request);

        return itemDetailHandler.excute(req);
    }
}
