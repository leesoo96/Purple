package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
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
	public Map<String, Object> login(@RequestBody UserEntity entity, HttpSession session) {
		
		Map<String, Object> loginResult = new HashMap<String, Object>();
		loginResult.put("result", service.loadUserByUsername(null));

		return loginResult;
	}
}
