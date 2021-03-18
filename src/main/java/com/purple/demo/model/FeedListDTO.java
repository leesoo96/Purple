package com.purple.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedListDTO extends FeedEntity{
    private String user_id;                           // 유저 아이디
    private String user_profileimg;                   // 프로필 이미지
    private int comment_count;                        // 댓글 갯수
    private List<String> media_url;                   // 이미지
    private List<String> hashtag_ctnt;                // 해시태그 내용
    private int favorite_count;                       // 좋아요 갯수
    // private int is_favorite;	                      // 내가 좋아요 했는지 안 했는지 용도.
}
