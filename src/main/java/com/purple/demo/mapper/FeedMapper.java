package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedDomain;
import com.purple.demo.model.FeedEntity;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;
import com.purple.demo.model.UserEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
   List<FeedDomain> selFeedList();
   FeedEntity selFeed(FeedEntity param);
   List<MediaEntity> selMediaList(FeedEntity param);
   List<HashtagEntity> selHashtagList(FeedEntity param);
   List<UserEntity> selUserList(UserEntity param);
}
