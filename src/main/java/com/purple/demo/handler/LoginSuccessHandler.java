package com.purple.demo.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.purple.demo.common.Const;
import com.purple.demo.model.UserPrincipal;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String rememberCheck = request.getParameter("remember_userId");
		HttpSession session = request.getSession();
		
		session.setAttribute(Const.KEY_LOGINUSER, authentication.getPrincipal());
		
		// UserDetails를 구현한 사용자 객체를 리턴
		UserPrincipal p = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Cookie cookie = new Cookie("RememberId", p.getUser_id());
		if(rememberCheck != null) {
			cookie.setMaxAge(60 * 60 * 24 * 7);	// 쿠키 저장 기한 지정		
		} else {
			// 쿠키를 가지고 와서 rememberId 쿠키를 지워줘야하는 부분
			cookie.setMaxAge(0); // rememberId 쿠키 기한 초기화 => 쿠키 제거 
		}
		response.addCookie(cookie); 
		// 아이디 기억여부와는 상관없이 쿠키는 생성된다
		// => 쿠키 기한을 지정해주기때문에 상황에 따른 쿠키의 기한을 저장시켜줘야한다
		// => 로그아웃을 할 경우 아이디 기억여부에 따라 쿠키유지 또는 쿠키제거
	

		// new code start 		
		String sessionId = session.getId();		
		
		// new code end 

		response.sendRedirect("/feed");
	}
}
