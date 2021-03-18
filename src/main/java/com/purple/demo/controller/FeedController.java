package com.purple.demo.controller;


import java.util.List;

import com.purple.demo.model.FeedEntity;
import com.purple.demo.model.FeedImgDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
