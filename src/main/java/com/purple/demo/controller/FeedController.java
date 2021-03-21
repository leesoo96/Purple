package com.purple.demo.controller;

import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import com.purple.demo.model.FeedImgDTO;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.DTO.FeedBookmarkDTO;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	// Feed
	@RequestMapping(value="")
	public String feed(){
		return "/feed";
	}

	@ResponseBody
	@RequestMapping(value="", method = RequestMethod.POST)
	public Map<String, Object> feedList(@RequestBody FeedListDTO param){
		Map<String, Object> feedListResult = new HashMap<String, Object>();
		feedListResult.put("result", feedService.selFeedList(param));
		return feedListResult;
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
	
	@ResponseBody
	@GetMapping("/favorite/{user_pk}/{feed_pk}")
		public Map<String, Object> feedFavorite() {
			
			return null;
		}

	// Bookmark
	@ResponseBody
	@PostMapping("/bookmark")
	public Map<String, Object> feedBookmark(@RequestBody FeedBookmarkDTO bmd) {
		Map<String, Object> feedBookmarkResult = new HashMap<String, Object>();
		feedBookmarkResult.put("result", feedService.feedBookmark(bmd));

		return feedBookmarkResult;
	}
}
