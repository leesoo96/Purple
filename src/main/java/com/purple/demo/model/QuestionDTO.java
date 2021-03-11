package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO extends QuestionEntity{
    private int recordCntPerPage;
	private int sIdx;
	private int page;
}
