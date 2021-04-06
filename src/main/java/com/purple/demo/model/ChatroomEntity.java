package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatroomEntity {
    private String chatroom_id;
    private int chatroom_userpk;
    private int chatroom_friendpk;
    private String chatroom_createdate;
}
