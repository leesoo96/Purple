package com.purple.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;

@Mapper
public interface LayoutMapper {
   
   // RightLayout only //////////////////////////////
   // 채팅 - 친구목록
   List<UserEntity> getFriendList(UserEntity entity);

   // 채팅 - 대화목록
   List<ChatRoomDTO> getFriendChatList(ChatRoomDTO dto);

   // 추천친구 목록
   List<FriendDTO> getRecommandFriendList(FriendDTO dto);
   // 추천친구 랜덤목록
   List<FriendDTO> getRandomRecommandFriendList(FriendDTO dto);
   
   // 친구 추가
   int addNewFriend(FriendDTO dto);
}
