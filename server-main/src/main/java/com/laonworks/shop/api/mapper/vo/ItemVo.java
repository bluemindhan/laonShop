package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ItemVo {

    public long itemId;

    public String name;

    public long price;

    public String itemDetail;
    public List<String> comment;

    public String image;

    public int likeCnt;

    public int bookCnt;
}
