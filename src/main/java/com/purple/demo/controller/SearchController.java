package com.purple.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.DTO.SearchUserDTO;
import com.purple.demo.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
    private SearchService service;

    @ResponseBody
	@RequestMapping("/{hashtag_ctnt}")
	public ModelAndView feedSearchHashtag(@PathVariable String hashtag_ctnt) {
        ModelAndView model = new ModelAndView();
        System.out.println(hashtag_ctnt);
        model.addObject("data", "#" +hashtag_ctnt);
        model.setViewName("/search");
        return model;
    }

    @RequestMapping("")
	public String search() {
		return "/search";
	}
	
    @ResponseBody
    @PostMapping("/searchUser")
    public List<SearchUserDTO> selUserSearch(@RequestBody SearchUserDTO dto){
        return service.selUserSearch(dto);
    }

    @ResponseBody
    @PostMapping("/searchFeed")
    public Map<String, Object> selFeedSearch(@RequestBody FeedListDTO dto){
        Map<String, Object> searchFeedListResult = new HashMap<String, Object>();
		searchFeedListResult.put("result", service.selFeedSearch(dto));
		return searchFeedListResult;
    }

    @ResponseBody
    @PostMapping("/searchHashtag")
    public Map<String, Object> searchHashtag(@RequestBody FeedListDTO dto){
        System.out.println(dto.getSearch_hashtag_ctnt());
        Map<String, Object> searchFeedListResult = new HashMap<String, Object>();
        searchFeedListResult.put("result", service.searchHashtag(dto));
        return searchFeedListResult;
    }

    
    

}
