package com.purple.demo.service;

import java.util.*;

import com.github.jknack.handlebars.internal.Files;
import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.FeedImgDTO;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.FeedWriteDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.utils.PurpleFileUtils;
import com.purple.demo.model.DTO.FeedFavoriteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


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
    
    public String insfeed(FeedWriteDTO dto, List<MultipartFile> files){
        //유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setFeed_userpk(principal.getUser_pk());
        int feed_pk = dto.getFeed_pk();
        mapper.insfeed(dto);
 
        //업로드 할 파일 경로
		String folder = "/images/write/"+feed_pk;
		try {	
            for(int i =0; i< files.size(); i++) {
			    String fileNm = fUtils.transferTo(files.get(i), folder);
                MediaEntity med = new MediaEntity();
                med.setMedia_feedpk(dto.getFeed_pk());
                med.setMedia_url(folder + "/" + fileNm);
                System.out.println(med.getMedia_url());
				mapper.insfeedimg(med);
            }
  
		} catch(Exception e) {
			System.out.println("에러");
		}
        System.out.println(folder);
    
        return "";
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
