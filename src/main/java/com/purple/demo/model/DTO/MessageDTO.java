package com.purple.demo.model.DTO;

import com.purple.demo.model.MessageEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO extends MessageEntity{
    // private int message_pk; // 메시지 번호
    // private String message_chatroomid;  // 방 아이디
    // private String message_ctnt; // 메시지 내용
    // private String message_date; // 작성 날짜
    // private int message_sendto; // 받는사람
    // private int message_from; // 보내는 사람
    // private int message_readstate; //읽음 상태
    // private int message_state; //삭제 여부

    private String user_id;
    private String user_profileimg;
}
