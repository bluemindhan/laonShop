package com.laonworks.shop.api.controller.response.user;

import com.laonworks.shop.api.controller.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import com.laonworks.shop.api.controller.vo.UserInfo;
import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "get profile")
@Data
public class GetProfileResponse extends BaseResponse {

  public UserInfo userInfo = null;
}
