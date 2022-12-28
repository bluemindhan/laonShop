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
@ApiModel(description = "Item info")
@Data
@ToString
public class ItemInfo {
    @ApiParam(value = "item id")
    public long itemId =  0;
    
    @ApiParam(value = "item name")
    public String name =  "";
    
    @ApiParam(value = "item price")
    public long value =  0;
}

