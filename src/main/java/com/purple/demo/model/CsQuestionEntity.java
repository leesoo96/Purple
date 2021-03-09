package com.purple.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsQuestionEntity extends CsAnswerEntity{
		private int question_pk;
		private String question_title;
		private String question_ctnt;
		private String question_writedate;
		private int question_views;
		private String question_typ;
		private int question_userpk;
	
}
