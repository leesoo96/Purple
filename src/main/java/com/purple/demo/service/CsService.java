package com.purple.demo.service;



import java.util.List;

import com.purple.demo.mapper.CsMapper;
import com.purple.demo.model.CsNoticeEntity;
import com.purple.demo.model.CsQuestionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsService {
	
	@Autowired
	private CsMapper mapper; 

	public 	List<CsNoticeEntity> selNoticeList(CsNoticeEntity p){
		return mapper.selNoticeList(p);
	}
	
	public CsNoticeEntity selNotice(CsNoticeEntity p) {
		return mapper.selNotice(p);
	}
	
	public int regNotice(CsNoticeEntity p) {
		return mapper.regNotice(p);
	}
	
	public int notice_upd(CsNoticeEntity p) {
		return mapper.notice_upd(p);
	}
	
	public int updNoticevieView(CsNoticeEntity p) {
		return mapper.updNoticevieView(p);
	}
	
	public int Notice_del(CsNoticeEntity p) {
		return mapper.notice_del(p);
	}
	

	//문의사항
	
	public 	List<CsQuestionEntity> selQuestionList(CsQuestionEntity p){
		return mapper.selQuestionList(p);
	}
	
	public CsQuestionEntity selQuestion(CsQuestionEntity p) {
		return mapper.selQuestion(p);
	}
	
	public int regQuestion(CsQuestionEntity p) {
		return mapper.regQuestion(p);
	}
	
	public int question_upd(CsQuestionEntity p) {
		return mapper.question_upd(p);
	}

	public int updQuestionView(CsQuestionEntity p) {
		return mapper.updQuestionView(p);
	}
	
	public int question_del(CsQuestionEntity p) {
		return mapper.question_del(p);
	}
	
}