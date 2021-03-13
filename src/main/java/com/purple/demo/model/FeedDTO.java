package com.purple.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDTO extends FeedEntity{
    private int comment_cnt;                 // 댓글 갯수
    private int favorite_cnt;                // 좋아요 갯수
    private List<MediaEntity> image_cnt;     // 이미지 갯수
    private int hashtag_cnt;                 // 해시 태그 갯수
    private int bookmark_state;              // 북마크 여부
    private int favorite_state;              // 좋아요 여부
}
