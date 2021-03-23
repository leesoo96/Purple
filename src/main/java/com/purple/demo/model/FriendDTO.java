package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendDTO extends UserEntity {
    private int user_pk;
    private int friend_pk; // 친구 pk 번호 

}
