package com.purple.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.DTO.SearchUserDTO;
import com.purple.demo.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/search")
public class SearchController {
	
	@Autowired
    private SearchService service;

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
}
