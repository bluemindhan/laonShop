package com.laonworks.shop.api.controller.vo;

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
import com.laonworks.shop.api.mapper.vo.UserVo;
@ApiModel(description = "User info")
@Data
@ToString
public class UserInfo {
    @ApiParam(value = "user email")
    public String email =  "";
    
    @ApiParam(value = "user name")
    public String name =  "";
    
    @ApiParam(value = "user type")
    public int userType =  0;

    @ApiParam(value = "user gender")
    public String gender = "";

    @ApiParam(value = "user birth")
    public String birth = "";

    @ApiParam(value = "user phone")
    public String phone = "";
    
    
    public void set(UserVo vo) {
        this.email = vo.email;
        this.name = vo.name;
        this.gender = vo.gender;
        this.birth = vo.birth;
        this.phone = vo.phone;
        this.userType = vo.userType;
    }
}

