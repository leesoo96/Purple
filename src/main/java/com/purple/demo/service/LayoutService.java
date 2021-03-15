package com.purple.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.purple.demo.mapper.LayoutMapper;
import com.purple.demo.model.FriendDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LayoutService {

    @Autowired
    private LayoutMapper mapper;

    public List<FriendDTO> getRecommandFriendList(FriendDTO dto){
        List<FriendDTO> friendList = new ArrayList<FriendDTO>();
        friendList = mapper.getRecommandFriendList(dto);
        if(friendList == null) {
        // 친구가 없는 사용자에게 랜덤으로 목록을 출력합니다 
            friendList = mapper.getRandomRecommandFriendList(dto);
            return friendList;
        }
        return friendList;
    }
}
