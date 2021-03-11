package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeDTO extends NoticeEntity{
	private int recordCntPerPage;
	private int sIdx;
	private int page;
}
