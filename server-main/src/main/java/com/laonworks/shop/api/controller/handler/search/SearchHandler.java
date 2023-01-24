package com.laonworks.shop.api.controller.handler.search;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.search.SearchRequest;
import com.laonworks.shop.api.controller.response.item.GetItemsResponse;
import com.laonworks.shop.api.mapper.SearchMapper;
import com.laonworks.shop.api.mapper.vo.BigCategoryVo;
import com.laonworks.shop.api.mapper.vo.ProductVo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Component
public class SearchHandler extends BaseHandler {


    @Autowired
    SearchMapper searchMapper;

    public GetItemsResponse excute(SearchRequest req) {

        GetItemsResponse res = new GetItemsResponse();
        List<BigCategoryVo> catelist = null;
        List<ProductVo1> searchList = null;

        int currentpage = req.page;
        int pagesize = 8;
        int totalCount = 0;
        int start = (currentpage - 1) * pagesize + 1;
        int end = start + pagesize - 1;

        try {
            catelist = searchMapper.selectCateList();
            totalCount = searchMapper.selectSearchCount(req);

            if(totalCount != 0){
                searchList = searchMapper.findByKeyword(req,start,end);
            }
            else{
                throw new RestClientResponseException("", HttpStatus.NO_CONTENT.value(), "", null, null, null);
            }

        } catch (Exception e){
            res.setCode(ResultCode.InternalServerError);
            return res;
        }

        res.products = searchList;
        res.categoryList = catelist;
        res.totalCount = totalCount;
        res.pageNo = currentpage;
        res.pageSize = pagesize;
        res.setCode(ResultCode.Success);

        return res;

    }
}
