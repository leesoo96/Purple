package com.purple.demo.controller;

import com.purple.demo.service.FeedService;
import com.purple.demo.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import com.purple.demo.common.Const;
import com.purple.demo.mapper.UserMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.model.DTO.CommentWriteDTO;
import com.purple.demo.model.DTO.FeedBookmarkDTO;
import com.purple.demo.model.DTO.FeedDetailDTO;
import com.purple.demo.model.DTO.FeedFavoriteDTO;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;

	@RequestMapping(value = "")
	public String feed(){
		return "/feed";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Map<String, Object> feedList(@RequestBody FeedListDTO dto){
		Map<String, Object> feedListResult = new HashMap<String, Object>();
		feedListResult.put(Const.KEY_RESULT, feedService.selFeedList(dto));

		return feedListResult;
	}
	
	@ResponseBody
	@RequestMapping(value = "/detail/{feed_pk}", method = RequestMethod.GET)
	public Map<String, Object> feedDetail(@PathVariable int feed_pk) {
		Map<String, Object> feedDetailResult = new HashMap<String, Object>();
		FeedDetailDTO dto = new FeedDetailDTO();
		dto.setFeed_pk(feed_pk);
		feedDetailResult.put(Const.KEY_RESULT, feedService.feedDetail(dto));

		return feedDetailResult;
	}

	@PostMapping("/feed_write")
	public String feed_write(FeedWriteDTO dto, @RequestParam(value = "imgs") MultipartFile[] files) {
		feedService.insFeed(dto, files);

		return "redirect:/feed";
	}
	
	@ResponseBody
	@PostMapping("/favorite")
	public Map<String, Object> feedFavorite(@RequestBody FeedFavoriteDTO dto) {
		Map<String, Object> feedFavoriteResult = new HashMap<String, Object>();
		feedFavoriteResult.put(Const.KEY_RESULT, feedService.feedFavorite(dto));
		
		return feedFavoriteResult;
	}

	@ResponseBody
	@PostMapping("/bookmark")
	public Map<String, Object> feedBookmark(@RequestBody FeedBookmarkDTO dto) {
		Map<String, Object> feedBookmarkResult = new HashMap<String, Object>();
		feedBookmarkResult.put(Const.KEY_RESULT, feedService.feedBookmark(dto));

		return feedBookmarkResult;
	}

	@ResponseBody
	@PostMapping("/comment")
	public Map<String, Object> insComment(@RequestBody CommentWriteDTO dto) {
		Map<String, Object> insCommentResult = new HashMap<String, Object>();
		insCommentResult.put(Const.KEY_RESULT, feedService.insComment(dto));
		
		return insCommentResult;
	}

	@ResponseBody
	@PostMapping("/recomment")
	public Map<String, Object> insReComment(@RequestBody CommentWriteDTO dto) {
		Map<String, Object> insReCommentResult = new HashMap<String, Object>();
		insReCommentResult.put(Const.KEY_RESULT, feedService.insReComment(dto));
		
		return insReCommentResult;
	}

	@ResponseBody
	@RequestMapping(value="/getcomment/{feed_pk}", method = RequestMethod.GET)
	public Map<String, Object> getCommentList(@PathVariable int feed_pk) {
		Map<String, Object> CommentListResult = new HashMap<String, Object>();
		CommentListResult.put(Const.KEY_RESULT, feedService.getCommentList(feed_pk));
		
		return CommentListResult;
	}

	@ResponseBody
	@RequestMapping(value="/getrecomment/{comment_parentpk}", method = RequestMethod.GET)
	public Map<String, Object> getReCommentList(@PathVariable int comment_parentpk) {
		Map<String, Object> getReCommentListResult = new HashMap<String, Object>();
		getReCommentListResult.put(Const.KEY_RESULT, feedService.getReCommentList(comment_parentpk));
		
		return getReCommentListResult;
	}

	@ResponseBody
	@PutMapping("/deleteFeed")
	public Map<String, Object> deleteFeed(@RequestBody int feed_pk) {
		Map<String, Object> deleteFeedResult = new HashMap<String, Object>();
		deleteFeedResult.put(Const.KEY_RESULT, feedService.deleteFeed(feed_pk));
		
		return deleteFeedResult;
	}
}
