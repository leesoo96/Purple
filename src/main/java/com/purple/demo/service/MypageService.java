package com.purple.demo.service;

import java.util.Map;

import com.purple.demo.mapper.MypageMapper;
import com.purple.demo.model.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MypageService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private MypageMapper mapper;

    public int modUserInfo(UserEntity userEntity) {
        return mapper.modUserInfo(userEntity);
    }

    public int checkUserpw(Map<String, Object> map) {
        String db_pw = mapper.checkUserpw(Integer.parseInt((String) map.get("user_pk")));
        if (encoder.matches((String) map.get("user_pw"), db_pw)) {
            return 1;
        }
        return 0;
    }

    public int modUserPw(UserEntity userEntity) {
        userEntity.setUser_pw(encoder.encode(userEntity.getUser_pw()));
        return mapper.modUserPw(userEntity);
    }
}
