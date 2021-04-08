package com.purple.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.purple.demo.model.UserEntity;
import com.purple.demo.model.UserPrincipal;

@Mapper
public interface UserMapper {

//	로그인
	UserPrincipal loginUser(UserEntity entity);
	
	
	UserPrincipal oauthloginUser(UserEntity entity);
	
	// 회원가입
	int joinUser(UserEntity entity);
	
	// 중복체크
	int overlap_Confirm(UserEntity entity);

	// 프로필 수정	
	UserEntity selUserInfo(String user_id);

	// 비밀번호 찾기 - 임시 비밀번호 발급 
	void temporary_pw(String user_id, String user_pw);

	// 비밀번호 찾기 - 아이디와 이메일 비교하여 맞게 입력했는지 검사	
	UserEntity compareId_email(UserEntity entity);

//	클라이언트 로그인 시 state 값 1로 변경
	int changeLoginState(int user_pk);
//	로그아웃 시 state 리셋
	int changeLogoutState(int user_pk);
}
