package com.laonworks.shop.api.controller.handler.item;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.response.item.MainPageResponse;
import com.laonworks.shop.api.mapper.ItemMapper;
import com.laonworks.shop.api.mapper.vo.ItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainPageHandler extends BaseHandler {

    @Autowired
    ItemMapper itemMapper;

    public MainPageResponse excute() {
        MainPageResponse res = new MainPageResponse();
        List<ItemVo> voList = null;

        try{
            voList = itemMapper.selectMainPage();
            res.setCode(ResultCode.Success);
        } catch (Exception e) {
            res.setCode(ResultCode.InternalServerError);
            return res;
        }

        res.volist = voList;

        return res;
    }
}
