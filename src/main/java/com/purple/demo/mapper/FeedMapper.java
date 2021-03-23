package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;
import com.purple.demo.model.DTO.CommentListDTO;
import com.purple.demo.model.DTO.FeedBookmarkDTO;
import com.purple.demo.model.DTO.FeedDetailDTO;
import com.purple.demo.model.DTO.FeedFavoriteDTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedMapper {
   List<FeedListDTO> selFeedList(FeedListDTO param);
   List<MediaEntity> selMediaList(FeedListDTO param);
   List<HashtagEntity> selHashtagList(FeedListDTO param);
   int isFavorite(int feed_pk, int user_pk);
   int isBookmark(FeedListDTO param);

   FeedDetailDTO selFeedDetail(FeedDetailDTO dto);

   FeedFavoriteDTO feedFavorite(FeedFavoriteDTO dto);
   int insFavorite(FeedFavoriteDTO dto);
   int delFavorite(FeedFavoriteDTO dto);
   int favoriteCount(FeedFavoriteDTO dto);

   int insFeed(FeedWriteDTO dto);
   int insFeedImg(MediaEntity p);
   int insHashtag(HashtagEntity p);
   List<CommentListDTO> selCommentList(FeedDetailDTO dto);
   // 북마크
   FeedBookmarkDTO feedBookmark(FeedBookmarkDTO bmd);
   int insertBookmark(FeedBookmarkDTO bmd);
   int deleteBookmark(FeedBookmarkDTO bmd);
}
