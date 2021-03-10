package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.NoticeEntity;
import com.purple.demo.model.QuestionEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsMapper {

	List<NoticeEntity> selNoticeList(NoticeEntity p);
	
	NoticeEntity selNotice(NoticeEntity p);

	NoticeEntity selNotice_count(NoticeEntity p);
	
	int regNotice(NoticeEntity p);
	
	int notice_upd(NoticeEntity p);
	
	int updNoticevieView(NoticeEntity p);
	
	int notice_del(NoticeEntity p);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	List<QuestionEntity> selQuestionList(QuestionEntity p);
	
	QuestionEntity selQuestion(QuestionEntity p);
	
	int regQuestion(QuestionEntity p) ;
	
	int question_upd(QuestionEntity p);

	int updQuestionView(QuestionEntity p);
	
	int question_del(QuestionEntity p);
}
