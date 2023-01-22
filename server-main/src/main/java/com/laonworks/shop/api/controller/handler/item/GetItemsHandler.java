package com.laonworks.shop.api.controller.handler.item;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.item.GetItemsRequest;
import com.laonworks.shop.api.controller.response.item.GetItemsResponse;
import com.laonworks.shop.api.controller.utils.PagingUtils;
import com.laonworks.shop.api.controller.vo.UserType;
import com.laonworks.shop.api.mapper.ItemMapper;
import com.laonworks.shop.api.mapper.UserMapper;
import com.laonworks.shop.api.service.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetItemsHandler extends BaseHandler{

    @Autowired
    ItemMapper itemMapper;

    public GetItemsResponse execute(CustomUserDetails user,GetItemsRequest req) {
        GetItemsResponse res = new GetItemsResponse();
        if(user == null) {
            res.setCode(ResultCode.Unauthorized);
            return res;
        }
        if(req.invalid()){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        int pageNo = req.pageNo; // 현재 페이지
        int pageSize = req.pageSize;
        int cateCode = req.cateCode;
        int userType = user.getUserType();
        if(cateCode > 300){
            res.setCode(ResultCode.InvalidParameter);
            return res;
        }
        try {
            if(userType == UserType.User.getValue()){
                int totalCount = itemMapper.selectItemCount(cateCode);
                int begin = (pageNo - 1) * pageSize;
                int end = begin + pageSize;

                res.products= itemMapper.selectItemList(begin,end,cateCode);

                if(res.products!=null && res.products.size()!=0) {
                    res.totalCount = totalCount;
                    res.pageNo = pageNo;
                    res.pageSize = pageSize;
                    res.setCode(ResultCode.Success);
                }else {
                    res.setCode(ResultCode.NonContent);
                    return res;
                }
            } else if (userType==UserType.Seller.getValue()) {
                res.setCode(ResultCode.Unauthorized);
                return res;
            }
            else{
                res.setCode(ResultCode.InvalidParameter);
                return res;
            }
        } catch (Exception e) {
            res.setCode(ResultCode.InternalServerError);
        }
        return res;
    }
}
