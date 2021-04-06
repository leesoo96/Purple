package com.purple.demo.mapper;

import java.util.List;

import com.purple.demo.model.AnswerEntity;
import com.purple.demo.model.NoticeDTO;
import com.purple.demo.model.NoticeEntity;
import com.purple.demo.model.QuestionDTO;  
import com.purple.demo.model.QuestionEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CsMapper {

	List<NoticeEntity> selNoticeList(NoticeDTO dto);
	
	NoticeEntity selNotice(NoticeEntity entity);

	int selNoticeMaxPage(NoticeDTO dto);
	
	int regNotice(NoticeEntity entity);
	
	int notice_upd(NoticeEntity entity);

	int notice_img_upd(NoticeEntity entity);
	
	int updNoticevieView(NoticeEntity entity);
	
	int notice_del(NoticeEntity entity);
	
	List<QuestionEntity> selQuestionList(QuestionDTO dto);
	
	QuestionEntity selQuestion(QuestionEntity entity);

	int selQuestionMaxPage(QuestionDTO dto);
	
	int regQuestion(QuestionEntity entity);

	int question_img_upd(QuestionEntity entity);
	
	int question_upd(QuestionEntity entity);

	int updQuestionView(QuestionEntity entity);
	
	int question_del(QuestionEntity entity);

	int question_cmt_reg(AnswerEntity entity);
	
	int answer_del(AnswerEntity entity);
}
