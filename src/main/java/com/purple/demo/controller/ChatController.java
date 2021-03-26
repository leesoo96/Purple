package com.purple.demo.controller;

import java.util.*;
import java.util.stream.Collectors;

import com.purple.demo.common.Const;
import com.purple.demo.model.ChatRelDTO;
import com.purple.demo.service.ChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private ChatService service;

    List<ChatRelDTO> list = new ArrayList<ChatRelDTO>();

    @RequestMapping("/room")
    public ModelAndView room(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/room");
        return mv;
    }
    
    @ResponseBody
    @RequestMapping("/createRoom")
    public Map<String, Object> createRoom(){   // 랜덤 방 생성 메서드
       Map<String, Object> map = new HashMap<String, Object>();
       String randomRoom = UUID.randomUUID().toString();
       map.put(Const.KEY_REUSLT, randomRoom);

       return map;
    }

    
}
