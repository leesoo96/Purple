package com.purple.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import com.purple.demo.common.Const;
import com.purple.demo.model.FeedDomain;
import com.purple.demo.service.FeedService;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedservice;
	
//	로그인
	@RequestMapping(value="")
	public String login() {
		return "/feed";
	}

	// 피드
	@ResponseBody
	@GetMapping("/feed")
	public Map<String, Object> selFeedList(@RequestBody FeedDomain param){
		Map<String, Object> FeedListResult = new HashMap<String, Object>();
		FeedListResult.put(Const.KEY_FEEDLISTDATA, feedservice.selFeedList(param));

		return FeedListResult;	// JSON 객체로 변환되어 전송.
	}
}