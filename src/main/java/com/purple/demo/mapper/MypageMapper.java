package com.purple.demo.mapper;

import com.purple.demo.model.UserEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageMapper {

    int modUserInfo(UserEntity entity);
}
