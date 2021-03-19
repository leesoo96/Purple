package com.purple.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.purple.demo.model.UserEntity;

@Mapper
public interface UserMapper {

//	로그인
	UserEntity loginUser(UserEntity entity);
	
//	회원가입
	int joinUser(UserEntity entity);
	
//	중복체크
	int overlap_Confirm(UserEntity entity);

	UserEntity selUserInfo(String user_id);
}
