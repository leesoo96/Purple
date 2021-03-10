package com.purple.demo.service;

import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.FeedDomain;
import com.purple.demo.model.FeedEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    
    @Autowired
    private FeedMapper mapper;

    public FeedDomain detailFeed(FeedEntity p){
        FeedDomain result =  new FeedDomain();
        result.setFeedEntity(mapper.selFeed(p));
        result.setMediaEntity(mapper.selMediaList(p));
        
        return result;
    }
}
