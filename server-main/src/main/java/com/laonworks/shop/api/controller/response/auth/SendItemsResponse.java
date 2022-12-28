package com.laonworks.shop.api.controller.response.auth;

import com.laonworks.shop.api.controller.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import com.laonworks.shop.api.controller.vo.ItemInfo;
import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "send items")
@Data
public class SendItemsResponse extends BaseResponse {

  public List<ItemInfo> items = null;
}
