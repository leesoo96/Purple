package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentEntity {
    private int comment_pk;
    private int comment_feedpk;
    private String comment_ctnt;
    private int comment_userpk;
    private String comment_writedate;
    private int comment_parentpk;
    private int comment_state;
}
