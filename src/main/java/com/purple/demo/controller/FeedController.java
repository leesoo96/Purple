package com.purple.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

	@GetMapping("")
	public String selFeedList(Model model, FeedDomain param){
		List<FeedDomain> feedList = feedservice.selFeedList(param);
		model.addAttribute(Const.KEY_FEEDLISTDATA, feedList);

		return "/feed";
	}
}
