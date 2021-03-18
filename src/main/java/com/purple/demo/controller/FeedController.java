package com.purple.demo.controller;

import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
	// @ResponseBody
	// @PostMapping("/uploadfeed")
	// public Map<String, Object> uploadfeed(@RequestBody FeedEntity p) {
	// 	Map<String, Object> feedResult = new HashMap<String, Object>();
	// 	return feedResult;
	// }
}
