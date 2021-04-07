package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedListDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookmarkMapper {
    List<FeedListDTO> selBookmarkList(FeedListDTO dto);
}
