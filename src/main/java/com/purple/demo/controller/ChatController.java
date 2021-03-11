package com.purple.demo.controller;

import java.util.List;

import com.purple.demo.mapper.ChatMapper;
import com.purple.demo.model.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private ChatMapper mapper;

    @ResponseBody
    @GetMapping("/getFriendList")
    public List<UserEntity> getFriendList(UserEntity entity) {
        System.out.println("pk = "+entity.getUser_pk());
        return mapper.getFriendList(entity);
    }
}
