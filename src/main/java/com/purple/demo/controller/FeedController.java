package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.purple.demo.model.FeedEntity;
import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService service;

//	로그인
	@RequestMapping(value="")
	public String login() {
		return "/feed";
	}

//  피드 글업로드
	@ResponseBody
	@PostMapping("/uploadfeed")
	public Map<String, Object> uploadfeed(@RequestBody FeedEntity p) {
		Map<String, Object> feedResult = new HashMap<String, Object>();
		return feedResult;
	}
}
