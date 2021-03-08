package com.purple.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.purple.demo.model.UserEntity;
import com.purple.demo.service.UserServiceImpl;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private UserServiceImpl service;

//	로그인
	@RequestMapping(value="")
	public String login() {
		return "/feed";
	}
	
	@ResponseBody
	@RequestMapping(value="", method= RequestMethod.POST)
	public HashMap<String, Object> login(@RequestBody UserEntity entity, HttpSession session) {
		
		HashMap<String, Object> loginResult = new HashMap<String, Object>();
		loginResult.put("result", service.loadUserByUsername(entity.getUser_id()));

		return loginResult;
	}
}
