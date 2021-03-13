package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRelDTO extends UserEntity {
 
    private int chatroom_pk; // 채팅방 번호
    private int chatroom_userpk; // 로그인한 사용자(나)의 번호
    private int chatroom_friendpk; // 친구 번호 
}
