package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.DTO.SearchUserDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchMapper {
    List<SearchUserDTO> selUserSearch(SearchUserDTO dto);
}
