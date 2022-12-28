package com.laonworks.shop.api.controller.response.auth;

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
@ApiModel(description = "sign up")
@Data
public class SignUpResponse extends BaseResponse {

  public UserInfo userInfo = null;

  public String accessToken = "";
}
