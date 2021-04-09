package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.DTO.MessageDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
    // 채팅방
    String getRoom(ChatRoomDTO dto);
    int createRoom(ChatRoomDTO dto);

    // 대화목록
    List<ChatRoomDTO> getChatList(ChatRoomDTO dto);

    // 채팅방
    List<MessageDTO> enterChatroom(String room_id);

    //안 읽은 메시지 수
    int getNoReadAllMessage(int send_to);
    int readMessage(MessageDTO dto);
    int insMessage(MessageDTO dto);
    
}
