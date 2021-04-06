package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentEntity {
    private int comment_pk;             // 댓글번호
    private int comment_feedpk;         // 피드 번호
    private String comment_ctnt;        // 댓글내용
    private int comment_userpk;         // 작성자
    private String comment_writedate;   // 작성날짜
    private int comment_parentpk;       // 댓글소속
    private int comment_state;          // 삭제 여부
}
