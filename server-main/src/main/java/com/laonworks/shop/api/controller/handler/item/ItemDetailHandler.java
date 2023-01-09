package com.laonworks.shop.api.controller.handler.item;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.item.ItemDetailRequest;
import com.laonworks.shop.api.controller.response.item.ItemDetailResponse;
import com.laonworks.shop.api.mapper.ItemMapper;
import com.laonworks.shop.api.mapper.vo.ItemVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ItemDetailHandler extends BaseHandler {

    @Autowired
    ItemMapper itemMapper;

    public ItemDetailResponse excute(ItemDetailRequest req) {

        ItemDetailResponse res = new ItemDetailResponse();
        int ProductNo = 0;

        if(req != null || !req.equals("")){
            ProductNo = (int) req.getProductNum();
        }

        try {
            ItemVo itemVo = itemMapper.selectItemDetailInfo(ProductNo);
            if(itemVo == null){
                res.setCode(ResultCode.Failed);
                return res;
            }
            List<String> list = itemMapper.selectCommentList(ProductNo);
            itemVo.setComment(list);
            res.itemVo = itemVo;

        } catch(Exception e){
            res.setCode(ResultCode.InternalServerError);
        }

        res.setCode(ResultCode.Success);

        return res;
    }
}
