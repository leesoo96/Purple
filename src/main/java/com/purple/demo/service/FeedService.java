package com.purple.demo.service;

import java.util.List;

import com.purple.demo.mapper.FeedMapper;
import com.purple.demo.model.FeedDomain;
import com.purple.demo.model.FeedEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {
    
    @Autowired
    private FeedMapper mapper;

    public List<FeedDomain> selFeedList() {
        return mapper.selFeedList();
    }

    public int regFeed(FeedEntity p) {
		return mapper.regFeed(p);
	}
}
