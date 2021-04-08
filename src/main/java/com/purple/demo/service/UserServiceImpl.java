package com.purple.demo.service;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;

import com.purple.demo.mapper.UserMapper;
import com.purple.demo.model.UserEntity;
import com.purple.demo.model.UserPrincipal;
 
// 시큐리티 설정에서 loginProcessingUrl("/login") 을 했을때
// "/login" 요청이 오면 자동으로 UserDetailsService 타입으로 ioc 되어있는
// loadUserByUsername 함수가 실행함
@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	// DB로부터 회원정보를 가져와서 존재하는 회원인지 아닌지를 체크하는 주요메소드 
	@Override
	public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
		UserEntity entity = new UserEntity();
		entity.setUser_id(user_id);

		UserEntity user = userMapper.loginUser(entity);
		return UserPrincipal.create(user);
	}
	


	// oauth2
	public UserDetails loadUserByUsername(String provider, String oauth_id) throws UsernameNotFoundException {
		UserEntity entity = new UserEntity();
		entity.setUser_provider(provider);
		entity.setOauth_id(oauth_id);	
		UserPrincipal user = userMapper.oauthloginUser(entity);	
		if(user == null) {
			return null;
		}
		return UserPrincipal.create(user);
	}
	

	// 회원가입
	public int join(UserEntity entity) {
		if(entity.getUser_pw() != null && !"".equals(entity.getUser_pw())) {
			entity.setUser_pw(encoder.encode(entity.getUser_pw()));
			entity.setOauth_typ("purple");
		}
		return userMapper.joinUser(entity);
	}
	
	// 중복 체크 
	public int overlap_Confirm(UserEntity entity) {
		return userMapper.overlap_Confirm(entity);
	}

	public UserEntity selUserInfo(String user_id) {
		return userMapper.selUserInfo(user_id);
	}

	// 비밀번호 찾기
	public int findPw(HttpServletResponse res, UserEntity entity) {
		res.setContentType("text/html;charset=utf-8");

		UserEntity dto = userMapper.compareId_email(entity);

		if(ObjectUtils.isEmpty(dto) || !entity.getUser_id().equals(dto.getUser_id())) {
			// 아이디가 없거나 틀렸을 경우 
			return 1;
		}  
		else if(!entity.getUser_email().equals(dto.getUser_email())) { 
			// 이메일이 틀렸을 경우
			return 2;
		}
		else {	// 이메일 일치 
			// 임시 비밀번호 만들기
			String temp_pw = "";
			for (int i = 0; i < 12; i++) {
				temp_pw += (char) ((Math.random() * 26) + 97);
			}
			
			// 임시 비밀번호 설정
			dto.setUser_pw(temp_pw);

			// 임시 비밀번호 암호화
			String bcryptTemp_pw = encoder.encode(temp_pw);

			// 임시 비밀번호 저장 
			userMapper.temporary_pw(dto.getUser_id(), bcryptTemp_pw);

			// 이메일 발송
			return sendEmail(dto); // 3
		}
	}

	// 임시 비밀번호 발급을 위한 이메일 발송
	public int sendEmail(UserEntity entity) {
		
		// Mail server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "purple210409"; // 보내는 사람 아이디
		String hostSMTPpwd = "mrpshuscuzizaent"; // 앱 비밀번호

		// 보내는 사람
		String fromEmail = "purple@purple.com";
		String fromName = "purple";

		// 보내는 이메일 제목과 내용
		String subject = "[PURPLE] 임시 비밀번호 발급 메일입니다.";
		String msg = "";
		msg += "<h2>" + entity.getUser_id() + " 님의 임시 비밀번호 입니다. 비밀번호를 변경하시기 바랍니다.</h2>";
		msg += "<h4>임시 비밀번호 : " + entity.getUser_pw() + "</h4>";

		// 받는 사람의 메일 주소 
		String mail = entity.getUser_email();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSLOnConnect(true); // SSL 사용
			email.setHostName(hostSMTP);
			email.setSmtpPort(465); // gmail SMTP 포트 번호 
			
			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setStartTLSEnabled(true); // TLS 사용
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();

			return 3;
		} catch (Exception e) {
			// 메일 형식이 틀렸을 때
			return 4;
		}
    }
}