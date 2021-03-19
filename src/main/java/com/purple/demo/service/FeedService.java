package com.purple.demo.service;

import java.util.*;

import com.purple.demo.common.MyFileUtils;
import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.FeedListDTO;

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
        List<FeedListDTO> result_list = new ArrayList<FeedListDTO>();
        param.setFeed_state(1);
        result_list = mapper.selFeedList(param);
        
        for (int i = 0; i < result_list.size(); i++ ) {
            int feed_pk = result_list.get(i).getFeed_pk();

            List<String> hashtag_list = new ArrayList<String>();
            hashtag_list = mapper.selHashtagList(feed_pk);
            List<String> media_list = new ArrayList<String>();
            media_list = mapper.selMediaList(feed_pk);
            
            result_list.get(i).setMedia_url(media_list);
            result_list.get(i).setHashtag_ctnt(hashtag_list);
        }
        return result_list;
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
