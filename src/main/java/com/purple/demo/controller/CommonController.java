package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.purple.demo.common.Const;
import com.purple.demo.mapper.UserMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.UserEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.service.CommonService;
import com.purple.demo.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommonController {

	final UserServiceImpl userService;
	final UserMapper userMapper;

	@Autowired
	private CommonService commonService;

//	첫화면 
	@RequestMapping({"/", "/welcome"})
	public String first() {
		return "unusedtiles/welcome";
	}

	// oauth 타입
	@ResponseBody // 로그아웃
	@GetMapping("/oauth2Typ")
	public Map<String, Object> oauth2_typ(UserEntity entity) {
		Map<String, Object> oauth2_typ = new HashMap<String, Object>();
		String oauthTyp = commonService.oauth2_typ(entity).getOauth_typ();
		
		// state code 
		// UserPrincipal p = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// userMapper.changeLogoutState(p.getUser_pk()); // 로그아웃 상태로 전환
		
		switch (oauthTyp) {
			case "kakao": 
				oauth2_typ.put("result", "1");
				oauth2_typ.put("url", "https://kauth.kakao.com/oauth/logout?client_id=dcd77beafc72e48753f0d5c6a3de7357&logout_redirect_uri=http://localhost:8090/logout");
				break;

			case "facebook": 
				oauth2_typ.put("result", "2");
				break;

			case "google": 
				oauth2_typ.put("result", "3");
				oauth2_typ.put("url", "https://www.google.com/accounts/Logout?continue=https://appengine.google.com");
				break;

			case "naver": 
				oauth2_typ.put("result", "4");
				oauth2_typ.put("url", "http://nid.naver.com/nidlogin.logout");
				break;

			default : 
				oauth2_typ.put("result", "0");
		}
		return oauth2_typ;
	}
	
	// @RequestMapping("/sessionLogout")
	// public void sessionLogout() {
	// 	UserPrincipal p = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// 	userMapper.changeLogoutState(p.getUser_pk()); // 로그아웃 상태로 전환
	// }


	// @RequestMapping("/duplLogin")
	// public String duplLogin() {
	// 	UserPrincipal p = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	// 	userMapper.changeLoginState(p.getUser_pk());

	// 	if(p.getUser_state() == 0) { // 로그인하기 전의 state 상태
	// 		return "redirect:feed"; // feed로 넘어갈때 state 값이 1로 변경됩니다.
	// 	} else {
	// 		return "redirect:logout"; 
	// 		/* 이미 로그인 -> 
	// 		중복로그인을 방어하기위한 부분으로 다른 브라우저에서
	// 		동일한 아이디로 로그인을 시도할 경우 /welcome으로 이동해서 
	// 		중복 로그인을 방어합니다.
	// 		*/
	// 	}
	// }

//	회원가입
	@ResponseBody
	@PostMapping("/join")
	public Map<String, Object> join(@RequestBody UserEntity entity) {
		Map<String, Object> joinResult = new HashMap<String, Object>();
		joinResult.put(Const.KEY_RESULT, userService.join(entity));
			
		return joinResult;
	}
		
//	중복체크 
	@ResponseBody
	@GetMapping("/join/{user_id}")
	public Map<String, Object> overlap_Confirm(UserEntity entity) {
		Map<String, Object> value = new HashMap<String, Object>();
		value.put(Const.KEY_RESULT, userService.overlap_Confirm(entity));
			
		return value;
	}

//	비밀번호 찾기	
	@ResponseBody
	@RequestMapping("/findpw")
	public  Map<String, Object> findPw(@RequestBody UserEntity entity, HttpServletResponse res, Model model) {
		Map<String, Object> value = new HashMap<String, Object>();
		value.put(Const.KEY_RESULT, userService.findPw(res, entity));
		
		return value;
	}

	@ResponseBody
	@RequestMapping("/userpage/{user_id}")
	public ModelAndView userInfo(@PathVariable String user_id) {
		ModelAndView model = new ModelAndView();
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(user_id.equals(principal.getUser_id())) {
			model.setViewName("/mypage");
			return model;
		}

		UserEntity dto = new UserEntity();
		dto = userService.selUserInfo(user_id);
		model.addObject("userInfo", dto);
		model.setViewName("/userpage");

		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/userpage/{user_id}", method = RequestMethod.POST)
	public Map<String, Object> userpageFeedList(@RequestBody FeedListDTO dto){
		Map<String, Object> userpageFeedListResult = new HashMap<String, Object>();
		userpageFeedListResult.put(Const.KEY_RESULT, commonService.selUserpageFeedList(dto));
		
		return userpageFeedListResult;
	}
}