package com.laonworks.shop.api.controller.request.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Builder;
import java.util.HashMap;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
import com.laonworks.shop.api.framework.libs.FormFile;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;
@ApiModel(description = "sign up")
@Data
@ToString
public class SignUpRequest {

    @NotNull(message = "user type을 입력해야합니다")
    @ApiParam(value = "user type", required = true)
    public int userType =  0;

    @NotEmpty(message = "email을 입력해야합니다")
    @Email(message = "이메일 형식에 맞지 않습니다")
    @ApiParam(value = "user email", required = true)
    public String email =  "";

    @NotEmpty(message = "비밀번호를 입력해야합니다")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8~20자의 비밀번호여야 합니다")
    @ApiParam(value = "user password", required = true)
    public String password =  "";

    @ApiParam(value = "user nick name")
    public String name =  "";

    @NotEmpty(message = "생년월일을 입력해야합니다")
    @ApiParam(value = "user birth", required = true)
    public String birth = "";

    @ApiParam(value = "user gender", required = true)
    public String gender = "";

    @ApiParam(value = "phone number", required = true)
    public String phone = "";

    
    
    public boolean valid() {
        return userType >= 0 || StringUtils.hasText(email) || StringUtils.hasText(password);
    }
    public boolean invalid() {
        return !valid();
    }
}

