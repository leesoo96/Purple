package com.purple.demo.service;

import java.util.*;

import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.HashtagRelationEntity;
import com.purple.demo.model.MediaEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.model.DTO.CommentListDTO;
import com.purple.demo.model.DTO.CommentWriteDTO;
import com.purple.demo.model.DTO.FeedBookmarkDTO;
import com.purple.demo.model.DTO.FeedDetailDTO;
import com.purple.demo.model.DTO.FeedFavoriteDTO;
import com.purple.demo.utils.PurpleFileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FeedService {
    
    @Autowired
    private FeedMapper feedMapper;

    @Autowired
    private PurpleFileUtils fUtils;

    // Feed List
    public List<FeedListDTO> selFeedList(FeedListDTO dto) {
        List<FeedListDTO> feed_list = new ArrayList<FeedListDTO>();
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        dto.setFeed_state(1);
        
        int first_page = (5 * dto.getPage_count());
        int end_page = first_page + 5;
        
        dto.setFirst_page(first_page);
        dto.setEnd_page(end_page);
       
        feed_list = feedMapper.selFeedList(dto);
        
        for (int i = 0; i < feed_list.size(); i++ ) {
            feed_list.get(i).setUser_pk(principal.getUser_pk());

            List<HashtagEntity> hashtag_list = new ArrayList<HashtagEntity>();
            
            hashtag_list = feedMapper.selHashtagList(feed_list.get(i));

            List<MediaEntity> media_list = new ArrayList<MediaEntity>();
            
            media_list = feedMapper.selMediaList(feed_list.get(i));
            
            feed_list.get(i).setMedia_url(media_list);
            feed_list.get(i).setHashtag_ctnt(hashtag_list);
            feed_list.get(i).setFavorite_state(feedMapper.isFavorite(feed_list.get(i).getFeed_pk(), principal.getUser_pk()));
            feed_list.get(i).setBookmark_state(feedMapper.isBookmark(feed_list.get(i)));
        }
        return feed_list;
    }
    
    public void insFeed(FeedWriteDTO dto, MultipartFile[] files){
        //유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setFeed_userpk(principal.getUser_pk());
        feedMapper.insFeed(dto);
 
        //업로드 할 파일 경로
        if(!files[0].isEmpty()) {
		String folder = "/images/feed/" + dto.getFeed_pk();
        fUtils.makeFolders(fUtils.getRealPath(folder));
		try {	
            for(MultipartFile file : files) {
                MediaEntity entity = new MediaEntity();
			    String fileNm = fUtils.transferTo(file, folder);
                
                entity.setMedia_url(folder + "/" + fileNm);
                entity.setMedia_feedpk(dto.getFeed_pk());
                
                feedMapper.insFeedImg(entity);
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
        //해시 태그
        if(dto.getHashtag() != null) {
        for(int i=0; i < dto.getHashtag().size(); i++){
            System.out.println(dto.getHashtag().get(i));
            HashtagRelationEntity hrel = new HashtagRelationEntity();
            HashtagEntity htentity = new HashtagEntity();
            htentity.setHashtag_ctnt((String)dto.getHashtag().get(i));
            int state = feedMapper.insHashtag(htentity);
            hrel.setHtrel_feedpk(dto.getFeed_pk());
            if(state == 0) {
               int hashtag_pk = feedMapper.selHashtag_pk(htentity);
               hrel.setHtrel_hashtagpk(hashtag_pk);
            }else {
                hrel.setHtrel_hashtagpk(htentity.getHashtag_pk());
            }
        }
    }
    }
  

    public FeedFavoriteDTO feedFavorite(FeedFavoriteDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setFavorite_userpk(principal.getUser_pk());
        
        if(dto.getFavorite_state() ==  0) {
            int result = feedMapper.insFavorite(dto);
            dto.setFavorite_state(result);
        } else {
            feedMapper.delFavorite(dto);
            dto.setFavorite_state(0);
        }

        dto.setFavorite_count(feedMapper.favoriteCount(dto));

        return dto;
    }

    public FeedBookmarkDTO feedBookmark(FeedBookmarkDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setBookmark_userpk(principal.getUser_pk());

        if(dto.getBookmark_state() ==  0) {
            int result = feedMapper.insertBookmark(dto);
            dto.setBookmark_state(result);
        } else {
            feedMapper.deleteBookmark(dto);
            dto.setBookmark_state(0);
        }
        return dto;
    }

    public FeedDetailDTO feedDetail(FeedDetailDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto = feedMapper.selFeedDetail(dto);
        
        dto.setFeed_state(1);
        dto.setUser_pk(principal.getUser_pk());  
        dto.setFavorite_state(feedMapper.isFavorite(dto.getFeed_pk(), dto.getUser_pk()));
        dto.setBookmark_state(feedMapper.isBookmark((FeedListDTO)dto));
        dto.setMedia_url(feedMapper.selMediaList((FeedListDTO)dto));
        dto.setHashtag_ctnt(feedMapper.selHashtagList((FeedListDTO)dto));
        dto.setComment_list(feedMapper.selCommentList(dto.getFeed_pk()));
        
        return dto;
    }

    public int insComment(CommentWriteDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setComment_userpk(principal.getUser_pk());
        dto.setComment_state(1);

        dto.setComment_parentpk(dto.getComment_pk());
        
        return feedMapper.updCommentParentPk(dto);
    }

    public List<CommentListDTO> getCommentList(int feed_pk) {
        return feedMapper.selCommentList(feed_pk);
    }

    public int insReComment(CommentWriteDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setComment_userpk(principal.getUser_pk());
        dto.setComment_state(1);

        return feedMapper.insReComment(dto);
    }

    public List<CommentListDTO> getReCommentList(int comment_parentpk) {
        return feedMapper.selReCommentList(comment_parentpk);
    }

    public int deleteFeed(int feed_pk) {
        return feedMapper.deleteFeed(feed_pk);
    }
}
