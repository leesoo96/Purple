package com.purple.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.mapper.MypageMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
    @Autowired
    private FeedMapper feedMapper;

    @Autowired
    private MypageMapper mypageMapper;

    public List<FeedListDTO> selUserpageFeedList(FeedListDTO param) {
        List<FeedListDTO> feed_list = new ArrayList<FeedListDTO>();
        param.setUser_pk(param.getCategory());
        param.setFeed_state(1);
        
        int first_page = (5*param.getPage_count());
        int end_page = first_page + 5;
        param.setFirst_page(first_page);
        param.setEnd_page(end_page);
        feed_list = mypageMapper.selMypageFeedList(param);
        
        for (int i = 0; i < feed_list.size(); i++ ) {
            feed_list.get(i).setUser_pk(param.getUser_pk());
            List<HashtagEntity> hashtag_list = new ArrayList<HashtagEntity>();
            hashtag_list = feedMapper.selHashtagList(feed_list.get(i));
            List<MediaEntity> media_list = new ArrayList<MediaEntity>();
            media_list = feedMapper.selMediaList(feed_list.get(i));
            feed_list.get(i).setMedia_url(media_list);
            feed_list.get(i).setHashtag_ctnt(hashtag_list);
            feed_list.get(i).setFavorite_state(feedMapper.isFavorite(feed_list.get(i).getFeed_pk(), param.getUser_pk()));
            feed_list.get(i).setBookmark_state(feedMapper.isBookmark(feed_list.get(i)));
        }
        return feed_list;
    }
}
