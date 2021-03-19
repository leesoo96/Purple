package com.purple.demo.controller;

import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.*;

import com.purple.demo.model.FeedImgDTO;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;

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

//@RequestMapping("/feed_write")
//public void feed_write(@RequestParam("")List test) {
//	System.out.println(test);
//}
	
	@PostMapping("/feed_write")
		public String feed_write(FeedWriteDTO dto, FeedImgDTO imgdto) {
			System.out.println(imgdto.getImgs());
			System.out.println(dto.getFeed_ctnt());
			System.out.println(dto.getHashtag());
			//service.insfeed(dto,imgdto);
			
			return "redirect:/feed";
		}
}
