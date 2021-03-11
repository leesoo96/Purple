package com.purple.demo.controller;

import java.util.List;

import com.purple.demo.model.UserEntity;
import com.purple.demo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private ChatService service;

    @ResponseBody
    @GetMapping("/getFriendList")
    public List<UserEntity> getFriendList(UserEntity entity) {
        entity.getUser_pk();
        
        return service.getFriendList(entity);
    }
}
