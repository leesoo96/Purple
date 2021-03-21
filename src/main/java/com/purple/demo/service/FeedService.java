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
        param.setFeed_state(1);
        feed_list = mapper.selFeedList(param);
        
        for (int i = 0; i < feed_list.size(); i++ ) {
            int feed_pk = feed_list.get(i).getFeed_pk();

            List<HashtagEntity> hashtag_list = new ArrayList<HashtagEntity>();
            hashtag_list = mapper.selHashtagList(feed_pk);
            List<MediaEntity> media_list = new ArrayList<MediaEntity>();
            media_list = mapper.selMediaList(feed_pk);
            
            feed_list.get(i).setMedia_url(media_list);
            feed_list.get(i).setHashtag_ctnt(hashtag_list);
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
    
}
