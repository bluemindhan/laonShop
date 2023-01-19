package com.laonworks.shop.api.controller.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@ApiModel(description = "patch password")
@Data
@ToString
public class PatchPasswordRequest {

    // 기존 비밀번호
    @NotEmpty(message = "기존 비밀번호를 입력해 주세요.")
    @ApiParam(value = "user password", required = true)
    public String password="";
    
    // 새로운 비밀번호
    @NotEmpty(message = "새로운 비밀번호를 입력해 주세요.")
    @ApiParam(value = "new password", required = true)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-z])(?=.*\\W)(?=\\S+$).{8,16}" ,
             message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~20자의 비밀번호여야 합니다")
    public String newPassword="";

    // 비밀번호 확인
    @NotEmpty(message = "새로운 비밀번호를 확인해 주세요.")
    @ApiParam(value = "confirm password" ,required = true)
    public String confirmPassword="";

    @AssertTrue(message = "기존 비밀번호와 동일한 비밀번호입니다.")
    public boolean isEqual(){
        return password.equals(newPassword);
    }

    public boolean valid(){
        return StringUtils.hasText(password)||StringUtils.hasText(newPassword)||StringUtils.hasText(confirmPassword)
                ||newPassword.equals(confirmPassword);
    }
    public boolean invalid(){
        return !valid();
    }


}
