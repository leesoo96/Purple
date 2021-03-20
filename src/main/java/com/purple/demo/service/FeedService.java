package com.purple.demo.service;

import java.util.*;

import com.purple.demo.common.MyFileUtils;
import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FeedService {
    
    @Autowired
    private FeedMapper mapper;
    private MyFileUtils fileUtils;

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
			e.printStackTrace();
		}
		
		System.out.println("path : " + path)
        return mapper.insfeed(dto);
    }
    */
}
