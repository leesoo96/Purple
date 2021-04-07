package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatroomEntity {
    private String chatroom_id;         // 채팅방 아이디
    private int chatroom_userpk;        // 사용자 번호
    private int chatroom_friendpk;      // 친구 번호
    private String chatroom_createdate; // 생성날짜
}
