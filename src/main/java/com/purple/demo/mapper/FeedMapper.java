package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.CommentEntity;
import com.purple.demo.model.FeedDomain;
import com.purple.demo.model.FeedEntity;
import com.purple.demo.model.HashTagEntity;
import com.purple.demo.model.Hashtag_RelationEntity;
import com.purple.demo.model.MediaEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
    FeedEntity selFeed(FeedEntity p);
    List<FeedEntity> selFeedList();
    List<HashTagEntity> selHashTagList(FeedEntity p);
	List<MediaEntity> selMediaList(FeedEntity p);
    List<Hashtag_RelationEntity> selHashtag_RelationList(FeedEntity p);
}
