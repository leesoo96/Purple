package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
	private int user_pk; // 회원번호
	private String user_id; // 아이디
	private String user_pw; // 비밀번호
	private String user_name; // 이름
	private String user_profileimg; // 프로필사진
	private String user_backgroundimg; // 배경사진
	private String user_email; // 이메일주소
	private String user_location; // 주소
	private String user_bio; // 자기소개
	private String user_birth; // 생일
	private String user_joindate; // 가입날짜
	private int user_state; // 회원상태
	private String user_auth; // 회원권한
	private String provider;
}
