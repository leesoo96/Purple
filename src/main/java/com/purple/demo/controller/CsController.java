package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.purple.demo.model.NoticeDTO;
import com.purple.demo.model.NoticeEntity;
import com.purple.demo.model.QuestionDTO;
import com.purple.demo.model.QuestionEntity;
import com.purple.demo.service.CsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

//@RequiredArgsConstructor //final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줍니다.
public class CsController { 
	@Autowired
	private CsService service;
	
	//공지사항 페이지(화면)
	@GetMapping("/notice")
	public String noticeList(Model model, NoticeDTO p) {
		model.addAttribute("noticeData", service.selNoticeList(p));
		return "/notice";
	}
	
	//공지사항 글등록 (화면) 
	@GetMapping("/notice_write")
	public String notice_write() {
		return "/notice_write";
	}
	 
	//공지사항 글등록
	@ResponseBody
	@PostMapping("/notice_write")
	public Map<String, Object> notice_write(@RequestBody NoticeEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.regNotice(p));
		return noticeWriteResult;
	}
	 //공지사항 글 수정 (화면) 
	@GetMapping("/notice_upd")
	public String notice_upd(Model model, NoticeEntity p) {
		model.addAttribute("noticeUpd", service.selNotice(p)); 
		return "/notice_write";
	}
	
	//공지사항 글 수정
	@ResponseBody
	@PostMapping("/notice_upd") 
	public Map<String, Object> notice_upd(@RequestBody NoticeEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.notice_upd(p));
		System.out.println("성공");
		return noticeWriteResult;
	}
	
	//공지사항 조회수
	
	@ResponseBody
	@PutMapping("/updNoticeView")
	public Map<String, Object> updNoticevieView(NoticeEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.updNoticevieView(p));
		noticeWriteResult.put("notice_view", p.getNotice_view());
		return noticeWriteResult;
	}
	
	
	//공지사항 글삭제
	@ResponseBody
	@DeleteMapping("/notice_del")
	public Map<String, Object> notice_del(NoticeEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.Notice_del(p));
		return noticeWriteResult;
	}
	
	//문의사항
	
	
	@GetMapping("/question")
	public String question(Model model, QuestionDTO p){
		model.addAttribute("questionData", service.selQuestionList(p));
		return "/question";
	}
	
	@GetMapping("/question_write")
	public String question_write() {
		return "/question_write";
	}
	
	@ResponseBody
	@PostMapping("/question_write")
	public Map<String, Object> regQuestion(@RequestBody QuestionEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.regQuestion(p));
		return noticeWriteResult;
	}
	@GetMapping("/question_upd")
	public String question_upd(Model model, QuestionEntity p) {
		model.addAttribute("questionUpd", service.selQuestion(p)); 
		return "/question_write";
	}

	@ResponseBody
	@PostMapping("/question_upd") 
	public Map<String, Object> question_upd(@RequestBody QuestionEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.question_upd(p));
		System.out.println("성공");
		return noticeWriteResult;
	}

	//문의사항 조회수
	@ResponseBody
	@PutMapping("/updQuestionView")
	public Map<String, Object> updQuestionView(QuestionEntity p) {
		Map<String, Object> QuestionWriteResult = new HashMap<String, Object>();
		QuestionWriteResult.put("result", service.updQuestionView(p));
		QuestionWriteResult.put("question_view", p.getQuestion_view());
		return QuestionWriteResult;
	}
	
	//문의사항 글삭제
		@ResponseBody
		@DeleteMapping("/question_del")
		public Map<String, Object> question_del(QuestionEntity p) {
			Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
			noticeWriteResult.put("result", service.question_del(p));
			return noticeWriteResult;
		}
		
	
	
}