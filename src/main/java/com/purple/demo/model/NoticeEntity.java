package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeEntity {
	
	private int notice_pk;
	private String notice_title;
	private String notice_ctnt;
	private String notice_writedate;
	private int notice_view;
	private int notice_userpk;
	//private String notice_file;
}
