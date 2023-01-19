package com.laonworks.shop.api.controller.response.auth;

import com.laonworks.shop.api.controller.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "change password")
@Data
public class PatchPasswordResponse extends BaseResponse {

    public int result=0; // 결과값 저장

    public Map<String,String> errorList = null;
}
