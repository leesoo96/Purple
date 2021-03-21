package com.purple.demo.service;




import com.purple.demo.mapper.CsMapper;
import com.purple.demo.model.AnswerEntity;
import com.purple.demo.model.NoticeDTO;
import com.purple.demo.model.NoticeDomain;
import com.purple.demo.model.NoticeEntity;
import com.purple.demo.model.QuestionDTO;
import com.purple.demo.model.QuestionDomain;
import com.purple.demo.model.QuestionEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.utils.PurpleFileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsService {
	
	@Autowired
	private CsMapper mapper; 

	@Autowired
	private PurpleFileUtils fUtils;


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

	public String notice_img(MultipartFile img, int notice_pk) {

		//유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();  
		
		//업로드 할 파일 경로
		String folder = "/images/notice/"+user_pk+"/"+notice_pk;
		try {
			fUtils.delFolder(fUtils.getRealPath(folder));
			String fileNm = fUtils.transferTo(img, folder);
			String DbFileName = folder + "/" + fileNm;

			NoticeEntity noticeEntity = new NoticeEntity();
			noticeEntity.setNotice_img(DbFileName);
			noticeEntity.setNotice_pk(notice_pk);

			mapper.notice_img_upd(noticeEntity);
			return DbFileName;
		} catch(Exception e) {
			return null;
		}
		
	}
	
	public int notice_upd(NoticeEntity p) {
		return mapper.notice_upd(p);
	}
	
	public int updNoticevieView(NoticeEntity p) {
		return mapper.updNoticevieView(p);
	}
	
	public int Notice_del(NoticeEntity p) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk(); 
		String folder = "/images/notice/"+user_pk+"/"+p.getNotice_pk();
		fUtils.delFolder(fUtils.getRealPath(folder));
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

	public String question_img(MultipartFile img, int question_pk) {
		//유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();  
		//업로드 할 파일 경로
		String folder = "/images/question/"+user_pk+"/" + question_pk;
		try {
			fUtils.delFolder(fUtils.getRealPath(folder));
			String fileNm = fUtils.transferTo(img, folder);
			String DbFileName = folder + "/" + fileNm;

			QuestionEntity QuestionEntity = new QuestionEntity();
			QuestionEntity.setQuestion_img(DbFileName);
			QuestionEntity.setQuestion_pk(question_pk);

			mapper.question_img_upd(QuestionEntity);
			return DbFileName;
		} catch(Exception e) {
			return null;
		}
	}
	
	public int question_upd(QuestionEntity p) {
		return mapper.question_upd(p);
	}

	public int updQuestionView(QuestionEntity p) {
		return mapper.updQuestionView(p);
	}
	
	public int question_del(QuestionEntity p) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk(); 
		String folder = "/images/question/"+user_pk+"/"+p.getQuestion_pk();
		fUtils.delFolder(fUtils.getRealPath(folder));
		return mapper.question_del(p);
	}

	public int question_cmt_reg(AnswerEntity p){
		return mapper.question_cmt_reg(p);
	}

	public int answer_del(AnswerEntity p){
		return mapper.answer_del(p);
	}
}
