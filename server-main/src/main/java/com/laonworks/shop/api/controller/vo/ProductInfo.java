package com.laonworks.shop.api.controller.vo;

import com.laonworks.shop.api.jihyeon.vo.ProductVo;
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
    @ApiParam(value = "")
    public String sllrId =  "";
    
    @ApiParam(value = "")
    public int prdtNo =  0;
    
    @ApiParam(value = "")
    public String prdtNm =  "";
    
    @ApiParam(value = "")
    public String prdtDesc =  "";
    
    @ApiParam(value = "")
    public double prdtPrce =  0.0;

    @ApiParam(value = "")
    public Date crtDt = null;

    @ApiParam(value = "")
    public Date updtDt = null;
    
    @ApiParam(value = "")
    public List<String> imageList =  null;
    
    
    public void set(ProductVo vo) {
        this.sllrId = vo.sllrId;
        this.prdtNo = vo.prdtNo;
        this.prdtNm = vo.prdtNm;
        this.prdtDesc = vo.prdtDesc;
        this.prdtPrce = vo.prdtPrce;
        this.crtDt = vo.crtDt;
        this.updtDt = vo.updtDt;
    }
}
