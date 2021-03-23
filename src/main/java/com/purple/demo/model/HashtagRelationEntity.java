package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HashtagRelationEntity {
    private int htrel_hashtagpk;    // 해시태그 번호
    private int htrel_feedpk;       // 피드 번호
}