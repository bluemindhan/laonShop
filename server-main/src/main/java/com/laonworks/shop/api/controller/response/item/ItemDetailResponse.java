package com.laonworks.shop.api.controller.response.item;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.vo.ItemVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "아이템 상세화면")
public class ItemDetailResponse extends BaseResponse {
    public ItemVo itemVo = null;
}
