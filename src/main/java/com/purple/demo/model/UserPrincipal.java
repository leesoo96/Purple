package com.purple.demo.model;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

// loadUserByUsername 메소드가 userDetails를 리턴
@Data
@EqualsAndHashCode(of = "user_id")
public class UserPrincipal extends UserEntity implements UserDetails, Principal {
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal(UserEntity user) {
		this.setUser_pk(user.getUser_pk());
		this.setUser_profileimg(user.getUser_profileimg());
		this.setUser_backgroundimg(user.getUser_backgroundimg());
		this.setUser_id(user.getUser_id());
		this.setUser_pw(user.getUser_pw());
		this.setUser_birth(user.getUser_birth());
		this.setUser_location(user.getUser_location());
		this.setUser_bio(user.getUser_bio());
		this.setUser_name(user.getUser_name());
		this.setUser_email(user.getUser_email());
		this.setUser_auth(user.getUser_auth());

		authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getUser_auth()));
	}
	
//	시큐리티 로그인 관련 
	 public static UserPrincipal create(UserEntity user) {
	        List<GrantedAuthority> authorities = Collections.
	        		singletonList(new SimpleGrantedAuthority(user.getUser_auth()));
	        return new UserPrincipal(user);
	    }
	
//	한 계정에 권한을 몇 개 가지고 있는지 확인하는 메소드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(this.getUser_auth()));
	}
	
//	유저 비밀번호
	@Override
	public String getPassword() {
		return this.getUser_pw();
	}
	
//	유저 아이디 
	@Override
	public String getUsername() {
		return this.getUser_id();
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

	@Override
	public String getName() {
		return null;
	}
}