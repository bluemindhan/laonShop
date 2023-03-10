package com.laonworks.shop.api.controller.response.user;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.controller.vo.UserInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "update profile")
@Data
public class PatchProfileResponse extends BaseResponse {

    public int result =0;

    public Map<String,String> errorList = null;
}
