package com.laonworks.shop.api.controller;

import com.laonworks.shop.api.controller.handler.search.SearchHandler;
import com.laonworks.shop.api.controller.request.search.SearchRequest;
import com.laonworks.shop.api.controller.response.item.GetItemsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/search")
public class SearchController extends BaseController{

    @Autowired
    SearchHandler searchHandler;

    @RequestMapping(method = RequestMethod.GET,value = "search")
    public GetItemsResponse search(SearchRequest req){

        GetItemsResponse res = new GetItemsResponse();

        return searchHandler.excute(req);


    }


}
