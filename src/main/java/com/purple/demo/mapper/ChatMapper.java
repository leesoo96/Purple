package com.purple.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
    int noReadMessage(String room_id);
}
