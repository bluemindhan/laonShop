package com.laonworks.shop.api.controller.vo;

import com.laonworks.shop.api.mapper.vo.WishVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

@ApiModel(description = "Wish Info")
@Data
@ToString
public class WishInfo {
    @ApiParam(value = "user Id")
    public String userId="";

    @ApiParam(value = "wish productNum")
    public long prdNum=0;

    @ApiParam(value = "wish productName")
    public String prdNm="";

    @ApiParam(value = "wish createDate")
    public String crtDt="";

    public void set(WishVo vo){
        this.userId=vo.userId;
        this.prdNum=vo.productNum;
        this.prdNm=vo.productName;
        this.crtDt=vo.createDate;
    }


}
