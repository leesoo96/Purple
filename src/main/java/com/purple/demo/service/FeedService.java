package com.purple.demo.service;

import java.util.*;

import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.model.DTO.FeedBookmarkDTO;
import com.purple.demo.model.DTO.FeedDetailDTO;
import com.purple.demo.model.DTO.FeedFavoriteDTO;
import com.purple.demo.utils.PurpleFileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class FeedService {
    
    @Autowired
    private FeedMapper mapper;
    private PurpleFileUtils fUtils;

    // Feed List
    public List<FeedListDTO> selFeedList(FeedListDTO param) {
        List<FeedListDTO> feed_list = new ArrayList<FeedListDTO>();
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        param.setFeed_state(1);
        int first_page = (5*param.getPage_count());
        int end_page = first_page + 5;
        param.setFirst_page(first_page);
        param.setEnd_page(end_page);
        feed_list = mapper.selFeedList(param);
        
        for (int i = 0; i < feed_list.size(); i++ ) {
            feed_list.get(i).setUser_pk(principal.getUser_pk());
            List<HashtagEntity> hashtag_list = new ArrayList<HashtagEntity>();
            hashtag_list = mapper.selHashtagList(feed_list.get(i));
            List<MediaEntity> media_list = new ArrayList<MediaEntity>();
            media_list = mapper.selMediaList(feed_list.get(i));
            feed_list.get(i).setMedia_url(media_list);
            feed_list.get(i).setHashtag_ctnt(hashtag_list);
            feed_list.get(i).setFavorite_state(mapper.isFavorite(feed_list.get(i).getFeed_pk(), principal.getUser_pk()));
            feed_list.get(i).setBookmark_state(mapper.isBookmark(feed_list.get(i)));
        }
        return feed_list;
    }

    public FeedFavoriteDTO feedFavorite(FeedFavoriteDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setFavorite_userpk(principal.getUser_pk());
        if(dto.getFavorite_state() ==  0) {
            int result = mapper.insFavorite(dto);
            dto.setFavorite_state(result);
        } else {
            mapper.delFavorite(dto);
            dto.setFavorite_state(0);
        }
        dto.setFavorite_count(mapper.favoriteCount(dto));
        return dto;
    }

    public FeedBookmarkDTO feedBookmark(FeedBookmarkDTO bmd) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bmd.setBookmark_userpk(principal.getUser_pk());

        if(bmd.getBookmark_state() ==  0) {
            int result = mapper.insertBookmark(bmd);
            bmd.setBookmark_state(result);
        } else {
            mapper.deleteBookmark(bmd);
            bmd.setBookmark_state(0);
        }
        return bmd;
    }

    public FeedDetailDTO feedDetail(FeedDetailDTO dto) {
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setUser_pk(principal.getUser_pk());
        dto.setFeed_state(1);
        dto = mapper.selFeedDetail(dto);
        dto.setFavorite_state(mapper.isFavorite(dto.getFeed_pk(), dto.getUser_pk()));
        dto.setBookmark_state(mapper.isBookmark((FeedListDTO)dto));
        dto.setMedia_url(mapper.selMediaList((FeedListDTO)dto));
        dto.setHashtag_ctnt(mapper.selHashtagList((FeedListDTO)dto));
        dto.setComment_list(mapper.selCommentList(dto));
        System.out.println(dto.getFeed_pk());
        System.out.println(dto.getUser_pk());
        System.out.println(mapper.isBookmark((FeedListDTO)dto));
        return dto;
    }
    /*
    public int insfeed(FeedWriteDTO dto, FeedImgDTO imgdto){
        List<MediaEntity> insList = new ArrayList();
		int result = 0;
		try {
			MediaEntity rme = null;
			for(MultipartFile file : imgdto.getImgs()) {				
				String fileNm = fileUtils.transferTo(file, path);
				
				rme = new MediaEntity();					
				rme.setMedia_url(fileNm);
				
				insList.add(rme);
			}
			
			result = mapper.insfeedimg(insList);
		} catch(Exception e) {
			System.out.println("에러");
		}
        System.out.println(folder);
    
        return "";
    }
    */
}
