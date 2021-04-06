package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.purple.demo.common.Const;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.service.BookmarkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {
    
    @Autowired
    private BookmarkService bookmarkService;

    @RequestMapping(value = "")
    public String bookmark() {
        return "/bookmark";
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Map<String, Object> bookmarkList(@RequestBody FeedListDTO dto){
		Map<String, Object> bookmarkListResult = new HashMap<String, Object>();
		bookmarkListResult.put(Const.KEY_RESULT, bookmarkService.selBookmarkList(dto));
		
        return bookmarkListResult;
    }
}
