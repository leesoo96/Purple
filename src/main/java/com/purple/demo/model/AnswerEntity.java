package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerEntity {
    private int answer_pk;
	private String answer_ctnt;
	private String answer_writedate;
	private int answer_userpk;
	private int answer_state;
}
