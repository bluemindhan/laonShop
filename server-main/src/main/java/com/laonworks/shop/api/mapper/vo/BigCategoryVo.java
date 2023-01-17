package com.laonworks.shop.api.mapper.vo;

import lombok.Data;

import java.util.List;

@Data
public class BigCategoryVo {

    public int catecodeRef;

    public String refname;

    public List<CategoryVo> catgorylist;

}
