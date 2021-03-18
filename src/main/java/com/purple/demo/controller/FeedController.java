package com.purple.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.service.FeedService;

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
}