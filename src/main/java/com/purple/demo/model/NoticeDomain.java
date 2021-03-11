package com.purple.demo.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDomain {
    private int page;  //현재 페이지
	private int recordCntPerPage;  //한페이지 글 개수
	private int maxPageNum;  //총 페이지의 갯수
	private List<NoticeEntity> list;

	private int sPage;
	private int ePage;
    
}
