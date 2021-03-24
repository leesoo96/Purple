package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.DTO.SearchUserDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SearchMapper {
    List<SearchUserDTO> selUserSearch(SearchUserDTO dto);

    List<FeedListDTO> selFeedSearch(FeedListDTO dto);

    List<FeedListDTO> searchHashtag(HashtagEntity dto);
}
