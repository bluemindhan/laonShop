package com.laonworks.shop.api.controller.vo;

import com.laonworks.shop.api.jihyeon.vo.ProductImageVo;
import com.laonworks.shop.api.jihyeon.vo.ProductVo;
import com.laonworks.shop.api.mapper.vo.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ApiModel(description = "product info")
@Data
@ToString
public class ProductInfo {
    @ApiParam(value = "seller id")
    public String sllrId =  "";
    
    @ApiParam(value = "prodcut number")
    public int prdtNo =  0;
    
    @ApiParam(value = "product name")
    public String prdtNm =  "";
    
    @ApiParam(value = "product description")
    public String prdtDesc =  "";
    
    @ApiParam(value = "product price")
    public double prdtPrce =  0.0;

    @ApiParam(value = "create date")
    public String crtDt = "";

    @ApiParam(value = "update date")
    public String updDt = "";
    
    @ApiParam(value = "image")
    public String image =  "";

    public void set(ProductVo vo) {
        this.sllrId = vo.sllrId;
        this.prdtNo = vo.prdtNo;
        this.prdtNm = vo.prdtNm;
        this.prdtDesc = vo.prdtDesc;
        this.prdtPrce = vo.prdtPrce;
        this.crtDt = vo.crtDt;
        this.updDt = vo.updDt;
        this.image = vo.image;
    }

}
