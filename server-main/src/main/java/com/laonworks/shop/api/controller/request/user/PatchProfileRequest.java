package com.laonworks.shop.api.controller.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@ApiModel(description = "put profile")
public class PatchProfileRequest {

    @NotEmpty(message ="이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이 맞지 않습니다.")
    @ApiParam(value = "user email",required = true)
    public String email="";

    @NotBlank(message = "비밀번호를 입력해 주세요")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-z])(?=.*\\W)(?=\\S+$).{8,16}",
//            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~20자의 비밀번호여야 합니다")
    @ApiParam(value ="user password", required = true)
    public String password = "";

    @NotBlank(message = "이름을 입력해 주세요")
    @ApiParam(value = "user name", required=true)
    public String name="";

    @NotEmpty(message = "생년월일을 선택해주세요")
    @ApiParam(value = "user birth",required = true)
    @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
             message = "생년월일을 다시 선택해주세요")
    public String birth="";
    
    @NotEmpty(message = "성별을 선택해주세요")
    @ApiParam(value = "user gender", required =true)
    public String gender ="";
    
    @NotEmpty(message = "연락처를 입력해주세요")
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",
             message = "연락처를 다시 입력해 주세요")
    @ApiParam(value = "phone number",required = true)
    public String phone="";

    public boolean valid() {
        return StringUtils.hasText(email) || StringUtils.hasText(password) || StringUtils.hasText(name) || StringUtils.hasText(birth) || StringUtils.hasText(gender)||StringUtils.hasText(phone);
    }
    public boolean invalid() {
        return !valid();
    }




}
