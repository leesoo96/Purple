package com.purple.demo.controller;

import java.util.List;

import com.purple.demo.mapper.ChatMapper;
import com.purple.demo.model.ChatRelDTO;
import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private ChatMapper mapper;

    // 친구목록
    @ResponseBody
    @PostMapping("/getFriendList")
    public List<UserEntity> getFriendList(@RequestBody UserEntity entity) {
        return mapper.getFriendList(entity);
    }

    // 대화목록
    @ResponseBody
    @PostMapping("/getFriendChatList")
    public List<ChatRoomDTO> getFriendChatList(@RequestBody ChatRoomDTO dto, Model model) {
        return mapper.getFriendChatList(dto);
    }
}
