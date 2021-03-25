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
    private List<MediaEntity> media_url;                   // 이미지
    private List<HashtagEntity> hashtag_ctnt;                // 해시태그 내용
    private int favorite_count;                       // 좋아요 갯수
    private int favorite_state;	                      // 내가 좋아요 했는지 안 했는지 용도
    private int bookmark_state;                       // bookmark 했는지 안 했는지
    
    //feedList 만들기 위해 필요한 값
    private int category;
    private int page_count;
    private int first_page;
    private int end_page;
    private int user_pk;

    //search hashtag 위해 필요한 값
    private String search_hashtag_ctnt; 
}
