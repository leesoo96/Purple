package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.MediaEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
   List<FeedListDTO> selFeedList(FeedListDTO param);
   List<String> selMediaList(int feed_pk);
   List<String> selHashtagList(int feed_pk);
   int insfeed(FeedWriteDTO dto);
   int insfeedimg(List<MediaEntity> p);
}
