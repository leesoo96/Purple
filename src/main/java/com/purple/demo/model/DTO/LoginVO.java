package com.purple.demo.model.DTO;

import java.util.ArrayList;
import java.util.Collection;

import com.purple.demo.model.UserEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"user_id"})
public class LoginVO extends UserEntity implements UserDetails {

    String user_id = this.getUser_id();
    // private String user_pw;
    // private String user_auth;

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(this.getUser_auth()));
		return auth;
	}
	@Override
	public String getPassword() {
		return this.getUser_pw();
	}
	@Override
	public String getUsername() {
		return user_id;
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
