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
	private CsMapper csMapper; 

	@Autowired
	private PurpleFileUtils fUtils;



	public NoticeDomain selNoticeList(NoticeDTO dto){
		if(dto.getRecordCntPerPage() == 0) {
			dto.setRecordCntPerPage(10);
		}
		if(dto.getPage() == 0) {
			dto.setPage(1);
		}		
		int sIdx = (dto.getPage() - 1) * dto.getRecordCntPerPage();
		dto.setSIdx(sIdx);

		NoticeDomain nd = new NoticeDomain();
		nd.setMaxPageNum(csMapper.selNoticeMaxPage(dto));
		nd.setList(csMapper.selNoticeList(dto));
		nd.setPage(dto.getPage());
		nd.setRecordCntPerPage(dto.getRecordCntPerPage());

		final int SIDE_NUM = 3; // 사이드 페이지 넘버
		int pageLen = SIDE_NUM * 2;
		int page = dto.getPage();
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
	
	//공지사항 
	public NoticeEntity selNotice(NoticeEntity entity) {
		return csMapper.selNotice(entity);
	}

	//공지사항 글등록
	public int regNotice(NoticeEntity entity) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();
		entity.setNotice_userpk(user_pk);
		return csMapper.regNotice(entity);
	}

	//공지사항 이미지
	public String notice_img(MultipartFile img, int notice_pk) {
		// 유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();  
		
		// 업로드 할 파일 경로
		String folder = "/images/notice/" + user_pk + "/" + notice_pk;
		
		try {

			// 삭제
			if (img != null) {
				fUtils.delFolder(fUtils.getRealPath(folder));
			}
			
			String fileNm = fUtils.transferTo(img, folder);

			String DbFileName = folder + "/" + fileNm;

			NoticeEntity noticeEntity = new NoticeEntity();
			noticeEntity.setNotice_img(DbFileName);
			noticeEntity.setNotice_pk(notice_pk);

			csMapper.notice_img_upd(noticeEntity);

			return DbFileName;
		} catch(Exception e) {
			return null;
		}	
	}
	
	//공지사항 수정
	public int notice_upd(NoticeEntity entity) {
		return csMapper.notice_upd(entity);
	}
	
	//공지사항 조회수
	public int updNoticevieView(NoticeEntity entity) {
		return csMapper.updNoticevieView(entity);
	}
	
	//공지사항 삭제
	public int Notice_del(NoticeEntity entity) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();
		
		String folder = "/images/notice/" + user_pk + "/" + entity.getNotice_pk();
		fUtils.delFolder(fUtils.getRealPath(folder));
		
		return csMapper.notice_del(entity);
	}

	// 문의사항
	public 	QuestionDomain selQuestionList(QuestionDTO dto){
		if(dto.getRecordCntPerPage() == 0) {
			dto.setRecordCntPerPage(10);
		}
		if(dto.getPage() == 0) {
			dto.setPage(1);
		}		
		int sIdx = (dto.getPage() - 1) * dto.getRecordCntPerPage();
		dto.setSIdx(sIdx);

		QuestionDomain nd = new QuestionDomain();
		nd.setMaxPageNum(csMapper.selQuestionMaxPage(dto));
		nd.setList(csMapper.selQuestionList(dto));
		nd.setPage(dto.getPage());
		nd.setRecordCntPerPage(dto.getRecordCntPerPage());

		final int SIDE_NUM = 3; // 사이드 페이지 넘버
		int pageLen = SIDE_NUM * 2;
		int page = dto.getPage();
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
	
	//문의사항 정보
	public QuestionEntity selQuestion(QuestionEntity entity) {
		return csMapper.selQuestion(entity);
	}
	
	//문의사항 글 등록
	public int regQuestion(QuestionEntity entity) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();  
		entity.setQuestion_userpk(user_pk);
		return csMapper.regQuestion(entity);
	}
	
	//문의사항 댓글 등록
	public int question_cmt_reg(AnswerEntity entity){
		return csMapper.question_cmt_reg(entity);
	}

	//문의사항 이미지
	public String question_img(MultipartFile img, int question_pk) {
		// 유저 pk 값
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk();  
		
		// 업로드 할 파일 경로
		String folder = "/images/question/" + user_pk + "/" + question_pk;
		try {
			if (img != null) {
				fUtils.delFolder(fUtils.getRealPath(folder));
			}
			String fileNm = fUtils.transferTo(img, folder);
			String DbFileName = folder + "/" + fileNm;

			QuestionEntity QuestionEntity = new QuestionEntity();
			QuestionEntity.setQuestion_img(DbFileName);
			QuestionEntity.setQuestion_pk(question_pk);

			csMapper.question_img_upd(QuestionEntity);
			
			return DbFileName;
		} catch(Exception e) {
			return null;
		}
	}
	
	//문의사항 수정
	public int question_upd(QuestionEntity entity) {
		return csMapper.question_upd(entity);
	}

	//문의사항 조회수
	public int updQuestionView(QuestionEntity entity) {
		return csMapper.updQuestionView(entity);
	}
	
	//문의사항 글 삭제
	public int question_del(QuestionEntity entity) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int user_pk = principal.getUser_pk(); 
		
		String folder = "/images/question/" + user_pk + "/" + entity.getQuestion_pk();
		fUtils.delFolder(fUtils.getRealPath(folder));
		
		return csMapper.question_del(entity);
	}

	//문의사항 댓글 삭제
	public int answer_del(AnswerEntity entity){
		return csMapper.answer_del(entity);
	}
}