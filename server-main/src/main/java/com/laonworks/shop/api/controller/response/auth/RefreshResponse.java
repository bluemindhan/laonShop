package com.laonworks.shop.api.controller.response.auth;

import com.laonworks.shop.api.controller.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "refreshToken")
@Data
public class RefreshResponse extends BaseResponse {

    public String accessToken = "";

    public String refreshToken = "";
}
