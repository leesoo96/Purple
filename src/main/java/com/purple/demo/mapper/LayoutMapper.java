package com.purple.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;

@Mapper
public interface LayoutMapper {
   
   // RightLayout only //////////////////////////////
   List<UserEntity> getFriendList(UserEntity entity);

   List<ChatRoomDTO> getFriendChatList(ChatRoomDTO dto);

   List<FriendDTO> getRecommandFriendList(FriendDTO dto);
   List<FriendDTO> getRandomRecommandFriendList(FriendDTO dto);
}
