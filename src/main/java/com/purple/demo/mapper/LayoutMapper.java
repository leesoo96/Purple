package com.purple.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;
import com.purple.demo.model.DTO.MessageDTO;

@Mapper
public interface LayoutMapper {
   
   // RightLayout only //////////////////////////////

   // 추천친구 목록
   List<FriendDTO> getRecommandFriendList(FriendDTO dto);
   // 추천친구 랜덤목록
   List<FriendDTO> getRandomRecommandFriendList(FriendDTO dto);

   //이미 친구인지 확인
   List<FriendDTO> friendCheck(FriendDTO dto);
   
   // 친구 추가
   int addNewFriend(FriendDTO dto);

   // 친구 삭제
   int delFriend(FriendDTO dto);

   // 채팅 - 친구목록
   List<UserEntity> getFriendList(UserEntity entity);
   
   // 채팅방
   String getRoom(ChatRoomDTO dto);
   int createRoom(ChatRoomDTO dto);

   // 대화목록
   List<ChatRoomDTO> getChatList(ChatRoomDTO dto);

   // 채팅방
   List<MessageDTO> enterChatroom(String room_id);
}
