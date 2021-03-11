package com.purple.demo.service;

import java.util.List;

import com.purple.demo.mapper.ChatMapper;
import com.purple.demo.model.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    
    @Autowired
    private ChatMapper mapper;

    // 친구 목록 
    public List<UserEntity> getFriendList(UserEntity entity) {
        return mapper.getFriendList();
    }
}
