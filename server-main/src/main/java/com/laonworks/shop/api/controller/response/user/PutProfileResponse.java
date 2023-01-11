package com.laonworks.shop.api.controller.response.user;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.controller.vo.UserInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "put profile")
@Data
public class PutProfileResponse extends BaseResponse {

    public UserInfo userInfo = null;

    public Map<String,String> errorList = null;
}
