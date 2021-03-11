package com.purple.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.purple.demo.service.FeedService;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedservice;

	// 로그인
	@RequestMapping(value = "")
	public String login() {
		return "/feed";
	}

}
