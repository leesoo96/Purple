package com.purple.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.purple.demo.common.Utils;
import com.purple.demo.mapper.LayoutMapper;
import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.model.DTO.MessageDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LayoutService {

    @Autowired
    private LayoutMapper mapper;

    @Autowired
    private Utils utils;

    public List<FriendDTO> getRecommandFriendList(FriendDTO dto) {
        List<FriendDTO> friendList = new ArrayList<FriendDTO>();
        friendList = mapper.getRecommandFriendList(dto);
        if(friendList.isEmpty()) {
        // 친구가 없는 사용자에게 랜덤으로 목록을 출력합니다 
            friendList = mapper.getRandomRecommandFriendList(dto);
            return friendList;
        }
        return friendList;
    }

    public int addNewFriend(FriendDTO dto) {
        return mapper.addNewFriend(dto);
    }

    public int delFriend(FriendDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setUser_pk(principal.getUser_pk());
        return mapper.delFriend(dto);
    }

    // 이미 친구인지 확인
    public int friendCheck(FriendDTO dto) {

        List<FriendDTO> friendPkList = mapper.friendCheck(dto);

        for (FriendDTO friendDTO : friendPkList) {
            if (friendDTO.getFriend_pk() == dto.getFriend_pk()) {
                return 0;
            }
        }
        return 1;
    }

    // 채팅방 생성
    public String getRoom(ChatRoomDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setChatroom_userpk(principal.getUser_pk());
        System.out.println(dto.getChatroom_userpk());
        String room_id = mapper.getRoom(dto);

        if(room_id == null || room_id.equals("")){
            room_id = UUID.randomUUID().toString();
            dto.setChatroom_id(room_id);
            ChatRoomDTO tempdto = new ChatRoomDTO();
            tempdto.setChatroom_friendpk(dto.getChatroom_userpk());
            tempdto.setChatroom_userpk(dto.getChatroom_friendpk());
            tempdto.setChatroom_id(room_id);
            
            mapper.createRoom(tempdto);
            mapper.createRoom(dto);
        }
        return room_id;
    }

    // 대화목록
    public List<ChatRoomDTO> getChatList(ChatRoomDTO dto) {
        return mapper.getChatList(dto);
    }

    public List<MessageDTO> enterChatroom(String room_id) {
        return mapper.enterChatroom(room_id);
    }

    public int getNoReadAllMessage(String user_id) {
        int user_pk = utils.getUserPkFromId(user_id);
        return mapper.getNoReadAllMessage(user_pk);
    }

    public int readMessage(String room_id) {
        MessageDTO dto = new MessageDTO();
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setMessage_sendto(principal.getUser_pk());
        dto.setMessage_chatroomid(room_id);
        dto.setMessage_readstate(0);
        return mapper.readMessage(dto);
    }

    public int getAlarmCount(String user_id) {
        int user_pk = utils.getUserPkFromId(user_id);
        return mapper.getAlarmCount(user_pk);
    }
    
    public int readAlarm(int user_pk) {
        return mapper.readAlarm(user_pk);
    }
 }
