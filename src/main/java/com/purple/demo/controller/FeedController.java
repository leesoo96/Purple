package com.purple.demo.controller;

import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import com.purple.demo.common.Const;
import com.purple.demo.model.FeedEntity;
import com.purple.demo.model.FeedImgDTO;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.UserPrincipal;
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
}
