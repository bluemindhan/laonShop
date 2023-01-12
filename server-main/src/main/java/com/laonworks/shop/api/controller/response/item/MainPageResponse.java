package com.laonworks.shop.api.controller.response.item;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.vo.ItemVo;
import lombok.Data;

import java.util.List;

@Data
public class MainPageResponse extends BaseResponse {

    public List<ItemVo> volist = null;

}
