package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediaEntity {
    
    private int media_pk; //미디어번호
    private int media_feedpk; //피드번호
    private String media_url; //미디어경로
    private String media_type; //미디어타입

}
