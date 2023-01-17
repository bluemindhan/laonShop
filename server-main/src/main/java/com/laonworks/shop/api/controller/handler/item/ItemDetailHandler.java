package com.laonworks.shop.api.controller.handler.item;

import com.laonworks.shop.api.controller.ResultCode;
import com.laonworks.shop.api.controller.handler.BaseHandler;
import com.laonworks.shop.api.controller.request.item.ItemDetailRequest;
import com.laonworks.shop.api.controller.response.item.ItemDetailResponse;
import com.laonworks.shop.api.controller.utils.AuthUtils;
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
        int ProductNo = 0; int num = 0;
        String userPk;

        if(req != null || !req.equals("")){
            ProductNo = (int) req.getProductNum();
        }

        //로그인 여부
        if(req.accessToken != null){
            userPk = AuthUtils.parseToken(req.accessToken);
            String tokens[] = userPk.split(":");
            String userid = tokens[0];
        }

        try {
            ItemVo itemVo = itemMapper.selectItemDetailInfo(ProductNo);
            if(itemVo == null){
                res.setCode(ResultCode.Failed);
                return res;
            }
            res.itemVo = itemVo;
        } catch(Exception e){
            System.out.println(e.getMessage());
            res.setCode(ResultCode.InternalServerError);
            return res;
        }

        // 찜여부 표시
        if(req.accessToken != null){
            userPk = AuthUtils.parseToken(req.accessToken);
            String tokens[] = userPk.split(":");
            String userid = tokens[0];
            num = itemMapper.selectWishFlag(userid,req.productNum);
            if(num != 0){
                res.wishFlag = true;
            }
        }

        res.setCode(ResultCode.Success);

        return res;
    }
}
