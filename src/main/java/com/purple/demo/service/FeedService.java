package com.purple.demo.service;

import java.util.List;

import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.BookmarkEntity;
import com.purple.demo.model.CommentEntity;
import com.purple.demo.model.FavoriteEntity;
import com.purple.demo.model.FeedDomain;
import com.purple.demo.model.FeedEntity;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    
    @Autowired
    private FeedMapper mapper;

    // 피드 리스트
    public List<FeedDomain> selFeedList(FeedDomain param) {
        return mapper.selFeedList(param);
    }

    // 피드 1개
    public FeedEntity selFeed(FeedEntity param){
        return mapper.selFeed(param);
    }

    /* 피드 1개 당 미디어 리스트(이미지, 동영상)
    public List<MediaEntity>selMediaList(FeedEntity param){
        return mapper.selMediaList(param);
    }

    // 피드 1개 당 해시태그 리스트
    public List<HashtagEntity> selHashtagList(FeedEntity param){
        return mapper.selHashtagList(param);
    }
    */
    // 좋아요 갯수
    public int FavoriteCnt(FavoriteEntity param){
        return mapper.FavoriteCnt(param);
    }

    // 댓글 갯수
    public int CommentCnt(CommentEntity param){
        return mapper.CommentCnt(param);
    }

    // 북마크 여부
    public int BookmarkState(BookmarkEntity param){
        return mapper.BookmarkState(param);
    }

    // 좋아요 여부
    public int FavoriteState(FavoriteEntity param){
        return mapper.FavoriteState(param);
    }
}
