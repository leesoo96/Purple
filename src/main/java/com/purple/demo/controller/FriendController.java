package com.purple.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.purple.demo.common.Const;
import com.purple.demo.mapper.FriendMapper;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;
import com.purple.demo.service.FriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private FriendService friendService;

    // 알 수도 있는 사람 목록 
    @ResponseBody
    @PostMapping("/recommand")
    public List<FriendDTO> getRecommandFriendList(@RequestBody FriendDTO dto) {
        return friendService.getRecommandFriendList(dto);
    }

    // 친구목록
    @ResponseBody
    @PostMapping("/getFriendList")
    public List<UserEntity> getFriendList(@RequestBody UserEntity entity) {
        return friendMapper.getFriendList(entity);
    }

    // 친구 추가
    @ResponseBody
	@PostMapping("/addNewFriend")
	public Map<String, Object> addNewFriend(@RequestBody FriendDTO dto) {
        Map<String, Object> addNewFriend = new HashMap<String, Object>();
        int result = friendService.friendCheck(dto);
        if (result == 1) {
            addNewFriend.put(Const.KEY_RESULT, friendService.addNewFriend(dto));
        }else if(result == 0){
            addNewFriend.put(Const.KEY_RESULT, "0");
        }
        return addNewFriend;

	}

    // 이미 친구인지 확인
    @ResponseBody
	@PostMapping("/friendCheck")
    public Map<String, Object> friendCheck(@RequestBody FriendDTO dto) {
        Map<String, Object> friendCheck = new HashMap<String, Object>();
        int result = friendService.friendCheck(dto);
        
        if (result == 1) {
            friendCheck.put(Const.KEY_RESULT, "1");
        }else if(result == 0){
            friendCheck.put(Const.KEY_RESULT, "0");
        }
        return friendCheck;
    }

    // 친구 삭제
    @ResponseBody
	@PostMapping("/delFriend")
	public Map<String, Object> delFriend(@RequestBody FriendDTO dto) {
		Map<String, Object> delFriend = new HashMap<String, Object>();
		delFriend.put(Const.KEY_RESULT, friendService.delFriend(dto));
		
        return delFriend;
	}
}
