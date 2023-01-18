package com.laonworks.shop.api.controller.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ApiModel(description = "delete account")
public class WithdrawalRequest {

    @NotBlank(message = "비밀번호를 입력해 주세요")
    @ApiParam(value ="user password", required = true)
    public String password="";



    public boolean valid() {return StringUtils.hasText(password);}
    public boolean invalid(){return !valid(); }
}
