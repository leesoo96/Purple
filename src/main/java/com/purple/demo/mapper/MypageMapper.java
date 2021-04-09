package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.UserEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageMapper {
    int modUserInfo(UserEntity entity);

    String checkUserpw(int user_pk);

    int modUserPw(UserEntity entity);

    List<FeedListDTO> selMypageFeedList(FeedListDTO dto);

    UserEntity oauth2_typ(UserEntity entity);
}
