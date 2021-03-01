package com.purple.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.purple.demo.mapper.UserMapper;
import com.purple.demo.model.UserEntity;
import com.purple.demo.model.UserPrincipal;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	// DB로부터 회원정보를 가져와서 존재하는 회원인지 아닌지를 체크하는 주요메소드 
	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		UserEntity entity = new UserEntity();
		entity.setUser_id(user_id);
		
		UserEntity user = mapper.loginUser(entity);
		if(user == null) {
			return null;
		}
		
		return UserPrincipal.create(user);
	}

//	회원가입
	public int join(UserEntity entity) {
		if(entity.getUser_pw() != null && !"".equals(entity.getUser_pw())) {
			entity.setUser_pw(encoder.encode(entity.getUser_pw()));
		}
		return mapper.joinUser(entity);
	}
	
//	중복 체크 
	public int overlap_Confirm(UserEntity entity) {
		return mapper.overlap_Confirm(entity);
	}
}
