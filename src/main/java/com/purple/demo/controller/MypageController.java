package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.purple.demo.common.Const;
import com.purple.demo.model.UserEntity;
import com.purple.demo.service.MypageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@Autowired
	private MypageService service;

	@RequestMapping("")
	public String mypage() {
		return "/mypage";
	}

	@ResponseBody
	@PutMapping("/mod_userinfo")
	public Map<String, Object> mod_userinfo(@RequestBody UserEntity userEntity) {
		Map<String, Object> modUserInfoResult = new HashMap<String, Object>();
		modUserInfoResult.put(Const.KEY_REUSLT, service.modUserInfo(userEntity));
		return modUserInfoResult;
	}

	@ResponseBody
	@PostMapping("/pw_check")
	public Map<String, Object> checkUserPw(@RequestBody Map<String, Object> map) {
		Map<String, Object> checkUserPwResult = new HashMap<String, Object>();
		checkUserPwResult.put(Const.KEY_REUSLT, service.checkUserpw(map));
		return checkUserPwResult;
	}

	@ResponseBody
	@PostMapping("/mod_userpw")
	public Map<String, Object> modUserPw(@RequestBody UserEntity userEntity) {
		Map<String, Object> modUserPwResult = new HashMap<String, Object>();
		modUserPwResult.put(Const.KEY_REUSLT, service.modUserPw(userEntity));
		return modUserPwResult;
	}
}
