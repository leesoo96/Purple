package com.purple.demo.controller;

import java.util.*;

import com.purple.demo.common.Const;
import com.purple.demo.mapper.LayoutMapper;
import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;
import com.purple.demo.model.DTO.MessageDTO;
import com.purple.demo.service.LayoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private LayoutService layoutService;

    // RightLayout ONLY //////////////////////////////////////////////
    // 알 수도 있는 사람 목록 
    @ResponseBody
    @PostMapping("/recommandFriend")
    public List<FriendDTO> getRecommandFriendList(@RequestBody FriendDTO dto) {
        return layoutService.getRecommandFriendList(dto);
    }

    // 친구목록
    @ResponseBody
    @PostMapping("/getFriendList")
    public List<UserEntity> getFriendList(@RequestBody UserEntity entity) {
        return mapper.getFriendList(entity);
    }

    // 친구 추가
    @ResponseBody
	@PostMapping("/addNewFriend")
	public Map<String, Object> addNewFriend(@RequestBody FriendDTO dto) {
        Map<String, Object> addNewFriend = new HashMap<String, Object>();
        int result = layoutService.friendCheck(dto);
        if (result == 1) {
            addNewFriend.put(Const.KEY_REUSLT, layoutService.addNewFriend(dto));
        }else if(result == 0){
            addNewFriend.put(Const.KEY_REUSLT, "0");
        }
        return addNewFriend;

	}

    // 이미 친구인지 확인
    @ResponseBody
	@PostMapping("/friendCheck")
    public Map<String, Object> friendCheck(@RequestBody FriendDTO dto) {
        Map<String, Object> friendCheck = new HashMap<String, Object>();
        int result = layoutService.friendCheck(dto);
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
		delFriend.put(Const.KEY_REUSLT, layoutService.delFriend(dto));
		return delFriend;
	}

    // 채팅방 생성
    @ResponseBody
    @PostMapping("/getRoom")
    public Map<String, Object> getRoom(@RequestBody ChatRoomDTO dto){
		Map<String, Object> getRoom = new HashMap<String, Object>();
        getRoom.put(Const.KEY_REUSLT, layoutService.getRoom(dto));

        return getRoom;
    }
    
    // 대화목록
    @ResponseBody
    @PostMapping("/getChatList")
    public List<ChatRoomDTO> getFriendChatList(@RequestBody ChatRoomDTO dto) {
        System.out.println(dto.getChatroom_userpk());
        return mapper.getChatList(dto);
    }

    @ResponseBody
    @GetMapping("/enterChatroom/{room_id}")
    public List<MessageDTO> enterChatroom(@PathVariable String room_id) {
        return layoutService.enterChatroom(room_id);
    }

    @ResponseBody
    @GetMapping("/getnoreadallmessage/{user_id}")
    public int getNoReadAllMessage(@PathVariable String user_id) {
        return layoutService.getNoReadAllMessage(user_id);
    }

    @ResponseBody
    @GetMapping("/readmessage/{room_id}")
    public int readMessage(@PathVariable String room_id) {
        return layoutService.readMessage(room_id);
    }

    @ResponseBody
    @GetMapping("/getalarmcount/{user_id}")
    public int getAlarmCount(@PathVariable String user_id) {
        return layoutService.getAlarmCount(user_id);
    }

    @ResponseBody
    @GetMapping("/readalarm/{user_pk}")
    public int readAlarm(@PathVariable int user_pk) {
        return layoutService.readAlarm(user_pk);
    }
}
