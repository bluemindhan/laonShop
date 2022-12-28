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
import com.laonworks.shop.api.controller.vo.ItemInfo;
@ApiModel(description = "send items")
@Data
@ToString
public class SendItemsRequest {
    @ApiParam(value = "items")
    public List<ItemInfo> items =  null;
    
    @ApiParam(value = "names")
    public List<String> names =  null;
    
    
    public boolean valid() {
        return true;
    }
    public boolean invalid() {
        return !valid();
    }
}

