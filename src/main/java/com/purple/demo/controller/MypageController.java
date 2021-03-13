package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.purple.demo.common.Const;
import com.purple.demo.model.UserEntity;
import com.purple.demo.service.MypageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
