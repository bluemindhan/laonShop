package com.laonworks.shop.api.controller.handler.search;

import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.search.SearchRequest;
import com.laonworks.shop.api.controller.response.user.GetItemsResponse;
import com.laonworks.shop.api.mapper.SearchMapper;
import com.laonworks.shop.api.mapper.vo.ItemVo;
import com.laonworks.shop.api.mapper.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchHandler extends BaseHandler {


    @Autowired
    SearchMapper searchMapper;

    public GetItemsResponse excute(SearchRequest req) {

        GetItemsResponse res = new GetItemsResponse();

        List<ProductVo> searchList = searchMapper.findByKeyword(req);

        res.products = searchList;


        return res;

    }
}
