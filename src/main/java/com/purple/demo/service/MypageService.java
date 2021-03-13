package com.purple.demo.service;

import com.purple.demo.mapper.MypageMapper;
import com.purple.demo.model.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageService {

    @Autowired
    private MypageMapper mapper;

    public int modUserInfo(UserEntity userEntity) {
        return mapper.modUserInfo(userEntity);
    }
}
