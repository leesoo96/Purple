package com.purple.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.purple.demo.mapper.BookmarkMapper;
import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.FeedListDTO;
import com.purple.demo.model.HashtagEntity;
import com.purple.demo.model.MediaEntity;
import com.purple.demo.model.UserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkMapper bookmarkMapper;

    @Autowired
    private FeedMapper feedMapper;

    public List<FeedListDTO> selBookmarkList(FeedListDTO dto) {
        List<FeedListDTO> feed_list = new ArrayList<FeedListDTO>();
        UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setFeed_state(1);
        dto.setUser_pk(principal.getUser_pk());
        
        int first_page = (5 * dto.getPage_count());
        int end_page = first_page + 5;
        dto.setFirst_page(first_page);
        dto.setEnd_page(end_page);

        feed_list = bookmarkMapper.selBookmarkList(dto);
        
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
}
