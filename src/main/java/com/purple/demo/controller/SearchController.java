package com.purple.demo.controller;

import java.util.List;

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
    public List<FeedListDTO> selFeedSearch(@RequestBody FeedListDTO dto){
        return service.selFeedSearch(dto);
    }

    @ResponseBody
    @PostMapping("/searchHashtag")
    public List<FeedListDTO> searchHashtag(@RequestBody HashtagEntity dto){
        return service.searchHashtag(dto);
    }

    
    

}
