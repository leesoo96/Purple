package com.purple.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.purple.demo.mapper.LayoutMapper;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LayoutService {

    @Autowired
    private LayoutMapper mapper;

    public List<FriendDTO> getRecommandFriendList(FriendDTO dto) {
        List<FriendDTO> friendList = new ArrayList<FriendDTO>();
        friendList = mapper.getRecommandFriendList(dto);
        if(friendList.isEmpty()) {
        // 친구가 없는 사용자에게 랜덤으로 목록을 출력합니다 
            friendList = mapper.getRandomRecommandFriendList(dto);
            return friendList;
        }
        return friendList;
    }

    public int addNewFriend(FriendDTO dto) {
        return mapper.addNewFriend(dto);
    }

    public int delFriend(FriendDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setUser_pk(principal.getUser_pk());
        return mapper.delFriend(dto);
    }

     //이미 친구인지 확인
     public int friendCheck(FriendDTO dto) {

        List<FriendDTO> friendPkList = mapper.friendCheck(dto);

        for (FriendDTO friendDTO : friendPkList) {
            if (friendDTO.getFriend_pk() == dto.getFriend_pk()) {
                return 0;
            }
        }
         return 1;
     }
 }
