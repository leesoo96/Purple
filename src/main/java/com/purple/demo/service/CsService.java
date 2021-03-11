package com.purple.demo.service;




import com.purple.demo.mapper.CsMapper;
import com.purple.demo.model.NoticeDTO;
import com.purple.demo.model.NoticeDomain;
import com.purple.demo.model.NoticeEntity;
import com.purple.demo.model.QuestionDTO;
import com.purple.demo.model.QuestionDomain;
import com.purple.demo.model.QuestionEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsService {
	
	@Autowired
	private CsMapper mapper; 

	public 	NoticeDomain selNoticeList(NoticeDTO p){
		if(p.getRecordCntPerPage() == 0) {
			p.setRecordCntPerPage(10);
		}
		if(p.getPage() == 0) {
			p.setPage(1);
		}		
		int sIdx = (p.getPage() - 1) * p.getRecordCntPerPage();
		p.setSIdx(sIdx);

		NoticeDomain nd = new NoticeDomain();
		nd.setMaxPageNum(mapper.selNoticeMaxPage(p));
		nd.setList(mapper.selNoticeList(p));
		nd.setPage(p.getPage());
		nd.setRecordCntPerPage(p.getRecordCntPerPage());

		final int SIDE_NUM = 3; //사이드 페이지 넘버
		int pageLen = SIDE_NUM * 2;
		int page = p.getPage();
		int maxPage = nd.getMaxPageNum();
		
		int sPage = page - SIDE_NUM;
		int ePage = page + SIDE_NUM;
		
		if(pageLen < maxPage) {	
			if(sPage < 1) {
				sPage = 1;
			} else if(sPage >= maxPage - pageLen) {
				sPage = maxPage - pageLen;
			}
			
			if(ePage > maxPage) {
				ePage = maxPage;
			} else if(ePage <= pageLen) {
				ePage = pageLen + 1;
			}
		} else {
			sPage = 1;
			ePage = maxPage;
		}
		
		nd.setSPage(sPage);
		nd.setEPage(ePage);	

		return nd;
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
	
	public 	QuestionDomain selQuestionList(QuestionDTO p){
		if(p.getRecordCntPerPage() == 0) {
			p.setRecordCntPerPage(10);
		}
		if(p.getPage() == 0) {
			p.setPage(1);
		}		
		int sIdx = (p.getPage() - 1) * p.getRecordCntPerPage();
		p.setSIdx(sIdx);

		QuestionDomain nd = new QuestionDomain();
		nd.setMaxPageNum(mapper.selQuestionMaxPage(p));
		nd.setList(mapper.selQuestionList(p));
		nd.setPage(p.getPage());
		nd.setRecordCntPerPage(p.getRecordCntPerPage());

		final int SIDE_NUM = 3; //사이드 페이지 넘버
		int pageLen = SIDE_NUM * 2;
		int page = p.getPage();
		int maxPage = nd.getMaxPageNum();
		
		int sPage = page - SIDE_NUM;
		int ePage = page + SIDE_NUM;
		
		if(pageLen < maxPage) {	
			if(sPage < 1) {
				sPage = 1;
			} else if(sPage >= maxPage - pageLen) {
				sPage = maxPage - pageLen;
			}
			
			if(ePage > maxPage) {
				ePage = maxPage;
			} else if(ePage <= pageLen) {
				ePage = pageLen + 1;
			}
		} else {
			sPage = 1;
			ePage = maxPage;
		}
		
		nd.setSPage(sPage);
		nd.setEPage(ePage);	

		return nd;
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
