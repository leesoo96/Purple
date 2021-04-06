package com.purple.demo.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
@EqualsAndHashCode(of = "user_id")
public class UserEntity implements UserDetails {
// 클래스명 UserVO로 바꾸기 

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(user_auth));
		return auth;
	}

	@Override
	public String getPassword() {
		return this.user_pw;
	}
	@Override
	public String getUsername() {
		return this.user_id;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	} 
}
