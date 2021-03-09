package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.CsNoticeEntity;
import com.purple.demo.model.CsQuestionEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsMapper {

	List<CsNoticeEntity> selNoticeList(CsNoticeEntity p);
	
	CsNoticeEntity selNotice(CsNoticeEntity p);
	
	int regNotice(CsNoticeEntity p);
	
	int notice_upd(CsNoticeEntity p);
	
	int updNoticevieView(CsNoticeEntity p);
	
	int notice_del(CsNoticeEntity p);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	List<CsQuestionEntity> selQuestionList(CsQuestionEntity p);
	
	CsQuestionEntity selQuestion(CsQuestionEntity p);
	
	int regQuestion(CsQuestionEntity p) ;
	
	int question_upd(CsQuestionEntity p);

	int updQuestionView(CsQuestionEntity p);
	
	int question_del(CsQuestionEntity p);
}
