package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedDomain;
import com.purple.demo.model.FeedEntity;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.Hashtag_RelationEntity;
import com.purple.demo.model.MediaEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
   List<FeedDomain> selFeedList();
   FeedEntity selFeed(FeedEntity p);
   List<MediaEntity> selMediaList(FeedEntity p);
   List<HashtagEntity> selHashtagList(FeedEntity p);
   
}
