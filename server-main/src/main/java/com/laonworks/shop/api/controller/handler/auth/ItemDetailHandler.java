package com.laonworks.shop.api.controller.handler.auth;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.auth.ItemDetailRequest;
import com.laonworks.shop.api.controller.response.auth.ItemDetailResponse;
import com.laonworks.shop.api.controller.vo.ItemInfo;
import com.laonworks.shop.api.controller.vo.UserInfo;
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
        int ProductNo = req.productNum;

        ItemVo itemVo = itemMapper.selectItemDetailInfo(ProductNo);
        List<String> list = itemMapper.selectCommentList(ProductNo);
        itemVo.setComment(list);

        res.itemVo = itemVo;

        res.setCode(ResultCode.Success);

        return res;
    }
}
