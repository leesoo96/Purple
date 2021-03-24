package com.purple.demo.model.DTO;

import com.purple.demo.model.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchUserDTO extends UserEntity{
    private String search_user_id;

    private int friend_pk;
}
