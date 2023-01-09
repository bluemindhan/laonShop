package com.laonworks.shop.api.controller.response.user;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.mapper.vo.BuyVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "get buys")
@Data
public class GetBuysResponse extends BaseResponse {

    public List<BuyVO> buysList=null;
}
