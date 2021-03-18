package com.purple.demo.controller;

import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.*;

import com.purple.demo.model.FeedListDTO;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	// Feed List
	@RequestMapping(value="")
	public String feedList(Model model, FeedListDTO param){
		List<FeedListDTO> list = new ArrayList<FeedListDTO>();
		list = feedService.selFeedList(param);
		model.addAttribute("feedListData", list);
		
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
