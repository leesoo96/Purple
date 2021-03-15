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
            friendList = mapper.getRandomRecommandFriendList(dto);
            return friendList;
        }
        return friendList;
    }
}
