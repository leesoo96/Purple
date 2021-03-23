package com.purple.demo.controller;

import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.DTO.FeedBookmarkDTO;
import com.purple.demo.model.DTO.FeedDetailDTO;
import com.purple.demo.model.DTO.FeedFavoriteDTO;

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
	
	@ResponseBody
	@RequestMapping(value="/detail/{feed_pk}", method = RequestMethod.GET)
	public Map<String, Object> feedDetail(@PathVariable int feed_pk) {
		Map<String, Object> feedDetailResult = new HashMap<String, Object>();
		FeedDetailDTO dto = new FeedDetailDTO();
		dto.setFeed_pk(feed_pk);
		feedDetailResult.put("result", feedService.feedDetail(dto));
		return feedDetailResult;
	}

	@PostMapping("/feed_write")
		public String feed_write(FeedWriteDTO dto
		, @RequestParam("imgs") MultipartFile[] files
		, @RequestParam String[] hashtag) {
			feedService.insFeed(dto, files, hashtag);	
			return "redirect:/feed";
		}
	
	@ResponseBody
	@PostMapping("/favorite")
		public Map<String, Object> feedFavorite(@RequestBody FeedFavoriteDTO dto) {
			Map<String, Object> feedFavoriteResult = new HashMap<String, Object>();
			feedFavoriteResult.put("result", feedService.feedFavorite(dto));
			return feedFavoriteResult;
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
