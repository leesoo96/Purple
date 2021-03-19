package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.purple.demo.model.UserEntity;
import com.purple.demo.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommonController {

	final UserServiceImpl service;
		
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
		joinResult.put("result", service.join(entity));
			
		return joinResult;
	}
		
//	중복체크 
	@ResponseBody
	@GetMapping("/join/{user_id}")
	public Map<String, Object> overlap_Confirm(UserEntity entity) {
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("result", service.overlap_Confirm(entity));
			
		return value;
	}

	@RequestMapping("/userpage")
	public String userInfo() {
		return "/userpage";
	}
}