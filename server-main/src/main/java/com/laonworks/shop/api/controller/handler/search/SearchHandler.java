package com.laonworks.shop.api.controller.handler.search;

import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.search.SearchRequest;
import com.laonworks.shop.api.controller.response.item.GetItemsResponse;
import com.laonworks.shop.api.mapper.SearchMapper;
import com.laonworks.shop.api.mapper.vo.BigCategoryVo;
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

        int currentpage = req.page;
        int pagesize = 10;
        int start = (currentpage - 1) * pagesize + 1;
        int end = start + pagesize - 1;

        List<BigCategoryVo> catelist = searchMapper.selectCateList();
        List<ProductVo> searchList = searchMapper.findByKeyword(req,start,end);

        res.products = searchList;
        res.categoryList = catelist;


        return res;

    }
}
