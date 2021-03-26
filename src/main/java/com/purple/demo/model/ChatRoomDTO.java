package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO extends UserEntity {
    
    private String chatroom_id; // UUID로 채팅방 구분
    private int chatroom_userpk; // 사용자 번호
    private int chatroom_friendpk; // 사용자와 대화하는 친구 번호
    private String chatroom_createdate; // 대화방 생성 시간
}