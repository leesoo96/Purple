package com.purple.demo.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feed")
public class FeedController {

//	로그인
	@RequestMapping(value="")
	public String login() {
		return "/feed";
	}
}
