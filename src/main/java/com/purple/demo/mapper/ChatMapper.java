package com.purple.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import com.purple.demo.model.UserEntity;

@Mapper
public interface ChatMapper {
   List<UserEntity> getFriendList(UserEntity entity);
}
