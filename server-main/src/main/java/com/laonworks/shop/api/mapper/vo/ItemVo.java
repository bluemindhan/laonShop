package com.laonworks.shop.api.mapper.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ItemVo {

    public long itemId; // 상품 번호

    public String name; // 상품 이름

    public long price; // 상품 가격

    public String cateName; // 카테고리 이름

    public String itemDetail; // 상품 상세정보

    public List<CommentVo> commentVoList; // 댓글

    public List<ImageVo> imageVoList; // 상품 이미지 리스트

    public int likeCnt; // 좋아요 개수

    public int bookCnt; // 북마크 갯수

    public String createDate; // 생성일자

    public String updateDate; // 수정일자

    public String sellerId; // 판매자 아이디

}
