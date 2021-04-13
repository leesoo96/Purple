package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.purple.demo.common.Const;
import com.purple.demo.model.AnswerEntity;
import com.purple.demo.model.NoticeDTO;
import com.purple.demo.model.NoticeEntity;
import com.purple.demo.model.QuestionDTO;
import com.purple.demo.model.QuestionEntity;
import com.purple.demo.model.UserPrincipal;
import com.purple.demo.service.CsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CsController {

	@Autowired
	private CsService csService;

	// 공지사항 페이지(화면)
	@GetMapping("/notice")
	public String noticeList(Model model, NoticeDTO dto) {
		model.addAttribute("noticeData", csService.selNoticeList(dto));
		return "/notice";
	}

	// 공지사항 글등록 (화면)
	@GetMapping("/notice_write")
	public String notice_write() {
		return "/notice_write";
	}

	// 공지사항 글등록
	@ResponseBody
	@PostMapping("/notice_write")
	public Map<String, Object> notice_write(@RequestBody NoticeEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		csService.regNotice(entity);
		noticeWriteResult.put(Const.KEY_RESULT, entity.getNotice_pk());
		
		return noticeWriteResult;
	}

	//공지사항 이미지 등록
	@ResponseBody
	@PostMapping("/notice_img")
	public Map<String, Object> notice_img(MultipartFile img, @RequestParam("notice_pk") int notice_pk) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put(Const.KEY_RESULT, csService.notice_img(img, notice_pk));
		
		return noticeWriteResult;
	}

	 //공지사항 글 수정 (화면) 
	@GetMapping("/notice_upd")
	public String notice_upd(Model model, NoticeEntity entity) {
		model.addAttribute("noticeUpd", csService.selNotice(entity));
		return "/notice_write";
	}

	// 공지사항 글 수정
	@ResponseBody
	@PostMapping("/notice_upd")
	public Map<String, Object> notice_upd(@RequestBody NoticeEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put(Const.KEY_RESULT, csService.notice_upd(entity));

		return noticeWriteResult;
	}

	// 공지사항 조회수

	@ResponseBody
	@PutMapping("/updNoticeView")
	public Map<String, Object> updNoticevieView(NoticeEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put(Const.KEY_RESULT, csService.updNoticevieView(entity));
		noticeWriteResult.put("notice_view", entity.getNotice_view());
		return noticeWriteResult;
	}

	// 공지사항 글삭제
	@ResponseBody
	@DeleteMapping("/notice_del")
	public Map<String, Object> notice_del(NoticeEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put(Const.KEY_RESULT, csService.Notice_del(entity));
		
		return noticeWriteResult;
	}

	// 문의사항
	@GetMapping("/question")
	public String question(Model model, QuestionDTO dto) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		dto.setQuestion_userpk(principal.getUser_pk());   
		dto.setUser_auth(principal.getUser_auth());

		model.addAttribute("questionData", csService.selQuestionList(dto));
		return "/question";
	}

	//문의사항 글 등록(화면)
	@GetMapping("/question_write")
	public String question_write() {
		return "/question_write";
	}

	//문의사항 글 등록
	@ResponseBody
	@PostMapping("/question_write")
	public Map<String, Object> regQuestion(@RequestBody QuestionEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		csService.regQuestion(entity);
		noticeWriteResult.put(Const.KEY_RESULT, entity.getQuestion_pk());

		return noticeWriteResult;
	}

	//문의사항 이미지 등록
	@ResponseBody
	@PostMapping("/question_img")
	public Map<String, Object> question_img(MultipartFile img, @RequestParam("question_pk") int question_pk) {
		Map<String, Object> questionWriteResult = new HashMap<String, Object>();
		try{
			questionWriteResult.put(Const.KEY_RESULT, csService.question_img(img, question_pk));
		} catch(Exception e) {
			questionWriteResult.put(Const.KEY_RESULT, "/images/question/basic_cs.jpg");
		}finally {
			return questionWriteResult; 
		}
	}
	
	//문의사항 댓글 등록
	@ResponseBody
	@PostMapping("/question_cmt_reg")
	public Map<String, Object> question_cmt_reg(@RequestBody AnswerEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put(Const.KEY_RESULT, csService.question_cmt_reg(entity));

		return noticeWriteResult;
	}

	//문의사항 글 수정(화면)
	@GetMapping("/question_upd")
	public String question_upd(Model model, QuestionEntity entity) {
		model.addAttribute("questionUpd", csService.selQuestion(entity));
		return "/question_write";
	}

	//문의사항 글 수정
	@ResponseBody
	@PostMapping("/question_upd")
	public Map<String, Object> question_upd(@RequestBody QuestionEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put(Const.KEY_RESULT, csService.question_upd(entity));

		return noticeWriteResult;
	}

	// 문의사항 조회수
	@ResponseBody
	@PutMapping("/updQuestionView")
	public Map<String, Object> updQuestionView(QuestionEntity entity) {
		Map<String, Object> QuestionWriteResult = new HashMap<String, Object>();
		QuestionWriteResult.put(Const.KEY_RESULT, csService.updQuestionView(entity));
		QuestionWriteResult.put("question_view", entity.getQuestion_view());
		
		return QuestionWriteResult;
	}

	// 문의사항 글삭제
	@ResponseBody
	@DeleteMapping("/question_del")
	public Map<String, Object> question_del(QuestionEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put(Const.KEY_RESULT, csService.question_del(entity));

		return noticeWriteResult;
	}

	// 문의사항 댓글 삭제
	@ResponseBody
	@DeleteMapping("/answer_del")
	public Map<String, Object> answer_del(AnswerEntity entity) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put(Const.KEY_RESULT, csService.answer_del(entity));

		return noticeWriteResult;
	}

}