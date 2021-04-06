package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FriendMapper {
    
    // 친구목록
    List<UserEntity> getFriendList(UserEntity entity);

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
}
