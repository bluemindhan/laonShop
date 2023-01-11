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

    public List<CommentVo> commentVoList;

    public List<ImageVo> imageVoList;

    public int likeCnt;

    public int bookCnt;
}
