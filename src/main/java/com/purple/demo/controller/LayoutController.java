package com.purple.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.purple.demo.common.Const;
import com.purple.demo.mapper.LayoutMapper;
import com.purple.demo.model.ChatRoomDTO;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserEntity;
import com.purple.demo.service.LayoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/layout")
public class LayoutController {
    
    @Autowired
    private LayoutMapper mapper;

    @Autowired
    private LayoutService layoutService;

    // @Autowired
    // private ChatService chatService;

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

    // 방 정보 가져오기 
    // @ResponseBody
    // @RequestMapping("/getRoom")
	// public List<ChatRoomDTO> getRoom(){
    //     // chatService.getRoom();
	// 	return roomList;
	// }

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
        System.out.println(dto.getChatroom_id());
        return mapper.getChatList(dto);
    }
}
