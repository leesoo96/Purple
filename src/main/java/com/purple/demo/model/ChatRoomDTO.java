package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDTO extends ChatroomEntity {
    private String user_id;
    private String user_profileimg;
    private String last_message;
    private String last_messagedate;

    private int chatroom_noreadcount; // 안 읽은 갯수
}