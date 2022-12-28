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
import java.util.Objects;
@ApiModel(description = "sign in")
@Data
@ToString
public class SignInRequest {
    @ApiParam(value = "user type", required = true)
    public int userType =  0;
    
    @ApiParam(value = "user email", required = true)
    public String email =  "";
    
    @ApiParam(value = "user password", required = true)
    public String password =  "";
    
    
    public boolean valid() {
        return userType >= 0 || StringUtils.hasText(email) || StringUtils.hasText(password);
    }
    public boolean invalid() {
        return !valid();
    }
}

