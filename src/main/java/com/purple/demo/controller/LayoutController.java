package com.purple.demo.controller;

import java.util.List;

import com.purple.demo.mapper.LayoutMapper;
import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// LeftLayout & RightLayout only 
@Controller
@RequestMapping("/layout")
public class LayoutController {
    
    @Autowired
    private LayoutMapper mapper;

    // RightLayout ONLY //////////////////////////////////////////////
    // 알 수도 있는 사람 목록 
    @ResponseBody
    @PostMapping("/recommandFriend")
    public List<FriendDTO> getRecommandFriendList(@RequestBody FriendDTO dto) {
        return mapper.getRecommandFriendList(dto);
    }

    // 친구목록
    @ResponseBody
    @PostMapping("/getFriendList")
    public List<UserEntity> getFriendList(@RequestBody UserEntity entity) {
        return mapper.getFriendList(entity);
    }

    // 대화목록
    @ResponseBody
    @PostMapping("/getFriendChatList")
    public List<ChatRoomDTO> getFriendChatList(@RequestBody ChatRoomDTO dto) {
        return mapper.getFriendChatList(dto);
    }
}