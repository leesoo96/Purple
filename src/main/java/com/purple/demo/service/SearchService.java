package com.purple.demo.service;

import java.util.List;

import com.purple.demo.mapper.SearchMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.DTO.SearchUserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    private SearchMapper mapper;

    public List<SearchUserDTO> selUserSearch(SearchUserDTO dto){
        return mapper.selUserSearch(dto);
    }

    public List<FeedListDTO> selFeedSearch(FeedListDTO dto){
        return mapper.selFeedSearch(dto);
    }
}
