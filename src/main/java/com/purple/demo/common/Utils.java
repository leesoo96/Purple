package com.purple.demo.common;

import com.purple.demo.mapper.CommonMapper;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Utils {

    final CommonMapper commonMapper;

    public int getUserPkFromId(String user_id) {
        return commonMapper.getUserPkFromId(user_id);
    }
}
