package com.purple.demo.service;

import java.util.List;
import java.util.UUID;

import com.purple.demo.common.Utils;
import com.purple.demo.mapper.ChatMapper;
import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.model.DTO.MessageDTO;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    
    final Utils utils;

    final ChatMapper chatMapper;

    // 채팅방 생성
    public String getRoom(ChatRoomDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setChatroom_userpk(principal.getUser_pk());
        
        String room_id = chatMapper.getRoom(dto);

        if(room_id == null || room_id.equals("")){
            room_id = UUID.randomUUID().toString();
            dto.setChatroom_id(room_id);
            
            ChatRoomDTO tempdto = new ChatRoomDTO();
            tempdto.setChatroom_friendpk(dto.getChatroom_userpk());
            tempdto.setChatroom_userpk(dto.getChatroom_friendpk());
            tempdto.setChatroom_id(room_id);
            
            chatMapper.createRoom(tempdto);
            chatMapper.createRoom(dto);
        }
        return room_id;
    }

    // 대화목록
    public List<ChatRoomDTO> getChatList(ChatRoomDTO dto) {
        return chatMapper.getChatList(dto);
    }

    public List<MessageDTO> enterChatroom(String room_id) {
        return chatMapper.enterChatroom(room_id);
    }

    public int getNoReadAllMessage(String user_id) {
        int user_pk = utils.getUserPkFromId(user_id);
        
        return chatMapper.getNoReadAllMessage(user_pk);
    }

    public int readMessage(String room_id) {
        MessageDTO dto = new MessageDTO();
        
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setMessage_sendto(principal.getUser_pk());
        dto.setMessage_chatroomid(room_id);
        dto.setMessage_readstate(0);
        
        return chatMapper.readMessage(dto);
    }
}
