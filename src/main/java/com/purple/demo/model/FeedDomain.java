package com.purple.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedDomain extends FeedListDTO{
    private List<FeedEntity> feedList;          // 피드 리스트
}