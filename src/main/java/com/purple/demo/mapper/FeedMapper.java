package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.HashtagRelationEntity;
import com.purple.demo.model.MediaEntity;
import com.purple.demo.model.DTO.CommentListDTO;
import com.purple.demo.model.DTO.CommentWriteDTO;
import com.purple.demo.model.DTO.FeedBookmarkDTO;
import com.purple.demo.model.DTO.FeedDetailDTO;
import com.purple.demo.model.DTO.FeedFavoriteDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
   List<FeedListDTO> selFeedList(FeedListDTO dto);
   List<MediaEntity> selMediaList(FeedListDTO dto);
   List<HashtagEntity> selHashtagList(FeedListDTO dto);
   int isFavorite(int feed_pk, int user_pk);
   int isBookmark(FeedListDTO dto);
   int deleteFeed(int feed_pk);
   FeedDetailDTO selFeedDetail(FeedDetailDTO dto);

   // 좋아요
   FeedFavoriteDTO feedFavorite(FeedFavoriteDTO dto);
   int insFavorite(FeedFavoriteDTO dto);
   int delFavorite(FeedFavoriteDTO dto);
   int favoriteCount(FeedFavoriteDTO dto);

   int insFeed(FeedWriteDTO dto);
   int insFeedImg(MediaEntity entity);
   int insHashtag(HashtagEntity entity);
   
   // 북마크
   FeedBookmarkDTO feedBookmark(FeedBookmarkDTO dto);
   int insertBookmark(FeedBookmarkDTO dto);
   int deleteBookmark(FeedBookmarkDTO dto);

   // 해시태그
   int selHashtag_pk(HashtagEntity entity);
   int insHashtagRel(HashtagRelationEntity entity);

   List<CommentListDTO> selCommentList(int feed_pk);
   List<CommentListDTO> selReCommentList(int comment_parentpk);
   int insComment(CommentWriteDTO dto);
   int updCommentParentPk(CommentWriteDTO dto);
   int insReComment(CommentWriteDTO dto);
}
