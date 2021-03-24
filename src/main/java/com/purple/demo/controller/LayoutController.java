package com.purple.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purple.demo.common.Const;
import com.purple.demo.mapper.LayoutMapper;
import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.service.LayoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/layout")
public class LayoutController {
    
    @Autowired
    private LayoutMapper mapper;

    @Autowired
    private LayoutService service;

    // RightLayout ONLY //////////////////////////////////////////////
    // 알 수도 있는 사람 목록 
    @ResponseBody
    @PostMapping("/recommandFriend")
    public List<FriendDTO> getRecommandFriendList(@RequestBody FriendDTO dto) {
        return service.getRecommandFriendList(dto);
    }

    // 친구목록
    @ResponseBody
    @PostMapping("/getFriendList")
    public List<UserEntity> getFriendList(@RequestBody UserEntity entity) {
        return mapper.getFriendList(entity);
    }

    // 대화목록
    @ResponseBody
    @PostMapping("/getFriendChatList")
    public List<ChatRoomDTO> getFriendChatList(@RequestBody ChatRoomDTO dto) {
        return mapper.getFriendChatList(dto);
    }

    // 친구 추가
    @ResponseBody
	@PostMapping("/addNewFriend")
	public Map<String, Object> addNewFriend(@RequestBody FriendDTO dto) {
        Map<String, Object> addNewFriend = new HashMap<String, Object>();
        int result = service.friendCheck(dto);
        if (result == 1) {
            addNewFriend.put(Const.KEY_REUSLT, service.addNewFriend(dto));
        }else if(result == 0){
            addNewFriend.put(Const.KEY_REUSLT, "0");
        }
        return addNewFriend;

	}

    //이미 친구인지 확인
    @ResponseBody
	@PostMapping("/friendCheck")
    public Map<String, Object> friendCheck(@RequestBody FriendDTO dto) {
        Map<String, Object> friendCheck = new HashMap<String, Object>();
        int result = service.friendCheck(dto);
        if (result == 1) {
            friendCheck.put(Const.KEY_REUSLT, "1");
        }else if(result == 0){
            friendCheck.put(Const.KEY_REUSLT, "0");
        }
        return friendCheck;
    }

    // 친구 삭제
    @ResponseBody
	@PostMapping("/delFriend")
	public Map<String, Object> delFriend(@RequestBody FriendDTO dto) {
		Map<String, Object> delFriend = new HashMap<String, Object>();
		delFriend.put(Const.KEY_REUSLT, service.delFriend(dto));
		return delFriend;
	}
}
