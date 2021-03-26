package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageEntity {
    private int message_pk;             // 메시지 번호
    private String message_chatroomid;  // 채팅방 아이디
    private String message_ctnt;        // 메시지 내용
    private String message_date;        // 메시지 날짜
    private int message_userpk;         // 작성자
    private int message_view;           // 읽음 여부
    private int message_state;          // 삭제 여부
    private String message_type;        // 메시지 종류
}
