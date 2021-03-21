package com.purple.demo.controller;

import com.purple.demo.service.FeedService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import com.purple.demo.model.FeedImgDTO;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;

@Controller
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;
	
	// Feed List
	@ResponseBody
	@RequestMapping(value="")
	public ModelAndView feedList(Model model, FeedListDTO param){
		ModelAndView mv = new ModelAndView(); 
		List<FeedListDTO> list = new ArrayList<FeedListDTO>();
		list = feedService.selFeedList(param);
		model.addAttribute("feedListData", list);
		mv.setViewName("/feed");
		return mv;
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
	@GetMapping("/favorite/{user_pk}/{feed_pk}")
		public Map<String, Object> feedFavorite() {
			
			return null;
		}
}
