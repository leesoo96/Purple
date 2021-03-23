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
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.UserEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.service.CommonService;
import com.purple.demo.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommonController {

	final UserServiceImpl service;
	
	@Autowired
	private CommonService commonService;

//	첫화면 
	@RequestMapping({"/", "/welcome"})
	public String first() {
		return "unusedtiles/welcome";
	}
	
//	회원가입
	@ResponseBody
	@PostMapping("/join")
	public Map<String, Object> join(@RequestBody UserEntity entity) {
		Map<String, Object> joinResult = new HashMap<String, Object>();
		joinResult.put(Const.KEY_REUSLT, service.join(entity));
			
		return joinResult;
	}
		
//	중복체크 
	@ResponseBody
	@GetMapping("/join/{user_id}")
	public Map<String, Object> overlap_Confirm(UserEntity entity) {
		Map<String, Object> value = new HashMap<String, Object>();
		value.put(Const.KEY_REUSLT, service.overlap_Confirm(entity));
			
		return value;
	}

//	비밀번호 찾기	
	@ResponseBody
	@RequestMapping("/findpw")
	public  Map<String, Object> findPw(@RequestBody UserEntity entity, HttpServletResponse res, Model model) {
		Map<String, Object> value = new HashMap<String, Object>();
		value.put(Const.KEY_REUSLT, service.findPw(res, entity));
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
		dto = service.selUserInfo(user_id);
		model.addObject("userInfo", dto);
		model.setViewName("/userpage");
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "/userpage/{user_id}", method= RequestMethod.POST)
	public Map<String, Object> userpageFeedList(@RequestBody FeedListDTO param){
		Map<String, Object> userpageFeedListResult = new HashMap<String, Object>();
		userpageFeedListResult.put("result", commonService.selUserpageFeedList(param));
		return userpageFeedListResult;
	}

}