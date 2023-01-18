package com.laonworks.shop.api.controller.response.auth;

import com.laonworks.shop.api.controller.response.BaseResponse;
import com.laonworks.shop.api.controller.vo.UserInfo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "withdrawal")
@Data
public class WithdrawalResponse extends BaseResponse {

    public int result = 0; // 1이면 회원탈퇴 성공 그 이외면 실패

    public Map<String, String> errorList= null;

}
