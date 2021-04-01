package com.purple.demo.mapper;

import com.purple.demo.model.DTO.MessageDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatMapper {
    int insMessage(MessageDTO dto);
}
