package com.purple.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purple.demo.common.Const;
import com.purple.demo.mapper.ChatMapper;
import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.DTO.MessageDTO;
import com.purple.demo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private ChatService chatService;
    
    // 채팅방 생성
    @ResponseBody
    @PostMapping("/getRoom")
    public Map<String, Object> getRoom(@RequestBody ChatRoomDTO dto){
		Map<String, Object> getRoom = new HashMap<String, Object>();
        getRoom.put(Const.KEY_RESULT, chatService.getRoom(dto));

        return getRoom;
    }
    
    // 대화목록
    @ResponseBody
    @PostMapping("/getChatList")
    public List<ChatRoomDTO> getFriendChatList(@RequestBody ChatRoomDTO dto) {
        return chatMapper.getChatList(dto);
    }

    @ResponseBody
    @GetMapping("/enterChatroom/{room_id}")
    public List<MessageDTO> enterChatroom(@PathVariable String room_id) {
        return chatService.enterChatroom(room_id);
    }

    @ResponseBody
    @GetMapping("/getnoreadallmessage/{user_id}")
    public int getNoReadAllMessage(@PathVariable String user_id) {
        return chatService.getNoReadAllMessage(user_id);
    }

    @ResponseBody
    @GetMapping("/readmessage/{room_id}")
    public int readMessage(@PathVariable String room_id) {
        return chatService.readMessage(room_id);
    }

}
