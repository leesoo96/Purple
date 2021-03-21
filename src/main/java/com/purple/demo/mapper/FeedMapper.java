package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
   List<FeedListDTO> selFeedList(FeedListDTO param);
   List<MediaEntity> selMediaList(int feed_pk);
   List<HashtagEntity> selHashtagList(int feed_pk);
   int insfeed(FeedWriteDTO dto);
   int insfeedimg(MediaEntity dto);
}
