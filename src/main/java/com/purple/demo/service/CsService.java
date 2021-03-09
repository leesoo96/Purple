package com.purple.demo.service;



import java.util.List;

import com.purple.demo.mapper.CsMapper;
import com.purple.demo.model.NoticeEntity;
import com.purple.demo.model.QuestionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsService {
	
	@Autowired
	private CsMapper mapper; 

	public 	List<NoticeEntity> selNoticeList(NoticeEntity p){
		return mapper.selNoticeList(p);
	}
	
	public NoticeEntity selNotice(NoticeEntity p) {
		return mapper.selNotice(p);
	}
	
	public int regNotice(NoticeEntity p) {
		return mapper.regNotice(p);
	}
	
	public int notice_upd(NoticeEntity p) {
		return mapper.notice_upd(p);
	}
	
	public int updNoticevieView(NoticeEntity p) {
		return mapper.updNoticevieView(p);
	}
	
	public int Notice_del(NoticeEntity p) {
		return mapper.notice_del(p);
	}
	

	//문의사항
	
	public 	List<QuestionEntity> selQuestionList(QuestionEntity p){
		return mapper.selQuestionList(p);
	}
	
	public QuestionEntity selQuestion(QuestionEntity p) {
		return mapper.selQuestion(p);
	}
	
	public int regQuestion(QuestionEntity p) {
		return mapper.regQuestion(p);
	}
	
	public int question_upd(QuestionEntity p) {
		return mapper.question_upd(p);
	}

	public int updQuestionView(QuestionEntity p) {
		return mapper.updQuestionView(p);
	}
	
	public int question_del(QuestionEntity p) {
		return mapper.question_del(p);
	}
	
}