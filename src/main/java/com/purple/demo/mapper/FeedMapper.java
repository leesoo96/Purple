package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.BookmarkEntity;
import com.purple.demo.model.CommentEntity;
import com.purple.demo.model.FavoriteEntity;
import com.purple.demo.model.FeedDomain;
import com.purple.demo.model.FeedEntity;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
   List<FeedDomain> selFeedList(FeedDomain param);       // 피드 리스트 전체
   FeedEntity selFeed(FeedEntity param);                 // 피드 1개 - FeedDetail
   List<MediaEntity> selMediaList(FeedEntity param);     // 피드 1개 당 미디어 리스트(이미지, 동영상)
   List<HashtagEntity> selHashtagList(FeedEntity param); // 피드 1개 당 해시태그 리스트
   int FavoriteCnt(FavoriteEntity param);                // 좋아요 갯수
   int CommentCnt(CommentEntity param);                  // 댓글 갯수
   int BookmarkState(BookmarkEntity param);              // 북마크 여부
   int FavoriteState(FavoriteEntity param);              // 좋아요 여부
   int insfeed(FeedWriteDTO dto);
   int insfeedimg(List<MediaEntity> p);
   
}
