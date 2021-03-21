package com.purple.demo.controller;

import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import com.purple.demo.common.Const;
import com.purple.demo.model.FeedImgDTO;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.DTO.FeedBookmarkDTO;
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
	
	@PostMapping("/feed_write")
		public String feed_write(FeedWriteDTO dto, @RequestParam("imgs") List<MultipartFile> files) {
			// System.out.println(imgs);
			// System.out.println(files[0]);
			// System.out.println(files);
			for(int i=0; i < files.size(); i++) {
				System.out.println(files.get(i));
			}
			feedService.insfeed(dto, files);			
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
