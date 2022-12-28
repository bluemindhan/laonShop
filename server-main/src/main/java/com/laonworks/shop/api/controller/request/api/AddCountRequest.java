package com.laonworks.shop.api.controller.request.api;

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
@ApiModel(description = "add")
@Data
@ToString
public class AddCountRequest {
    @ApiParam(value = "add count")
    public int count =  0;
    
    
    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}

