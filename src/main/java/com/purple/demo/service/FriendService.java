package com.purple.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.purple.demo.mapper.FriendMapper;
import com.purple.demo.model.FriendDTO;
import com.purple.demo.model.UserPrincipal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendService {
    
    final FriendMapper friendMapper;

    public List<FriendDTO> getRecommandFriendList(FriendDTO dto) {
        List<FriendDTO> friendList = new ArrayList<FriendDTO>();
        friendList = friendMapper.getRecommandFriendList(dto);
        
        if(friendList.size() <= 5) {
        // 친구가 없는 사용자에게 랜덤으로 목록을 출력합니다 
            friendList = friendMapper.getRandomRecommandFriendList(dto);
            return friendList;
        }
        return friendList;
    }

    public int addNewFriend(FriendDTO dto) {
        return friendMapper.addNewFriend(dto);
    }

    public int delFriend(FriendDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setUser_pk(principal.getUser_pk());
        
        return friendMapper.delFriend(dto);
    }

    // 이미 친구인지 확인
    public int friendCheck(FriendDTO dto) {
        List<FriendDTO> friendPkList = friendMapper.friendCheck(dto);

        for (FriendDTO friendDTO : friendPkList) {
            if (friendDTO.getFriend_pk() == dto.getFriend_pk()) {
                return 0;
            }
        }
        return 1;
    }
}
