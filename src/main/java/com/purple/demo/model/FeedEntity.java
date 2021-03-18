package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedEntity {
    private int feed_pk; //피드번호
    private String feed_ctnt; //피드내용
    private String feed_writedate; //작성날짜
    private int feed_userpk; //작성자
    private String feed_update; //수정날짜
    private String feed_state; //삭제여부
    private int feed_report; //신고횟수
}
