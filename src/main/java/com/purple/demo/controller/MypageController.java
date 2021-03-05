package com.purple.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {

	@RequestMapping("")
	public String mypage() {
		return "/mypage";
	}

}
