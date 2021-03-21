package com.purple.demo.controller;

import java.util.HashMap;
import java.util.Map;

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

// @RequiredArgsConstructor //final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줍니다.
public class CsController {
	@Autowired
	private CsService service;

	// 공지사항 페이지(화면)
	@GetMapping("/notice")
	public String noticeList(Model model, NoticeDTO p) {
		model.addAttribute("noticeData", service.selNoticeList(p));
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
	public Map<String, Object> notice_write(@RequestBody NoticeEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		service.regNotice(p);
		noticeWriteResult.put("result", p.getNotice_pk());
		return noticeWriteResult;
	}

	//공지사항 이미지 등록
	@ResponseBody
	@PostMapping("/notice_img")
	public Map<String, Object> notice_img(MultipartFile img, @RequestParam("notice_pk") int notice_pk) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.notice_img(img, notice_pk));
		return noticeWriteResult;
	}

	 //공지사항 글 수정 (화면) 
	@GetMapping("/notice_upd")
	public String notice_upd(Model model, NoticeEntity p) {
		model.addAttribute("noticeUpd", service.selNotice(p));
		return "/notice_write";
	}

	// 공지사항 글 수정
	@ResponseBody
	@PostMapping("/notice_upd")
	public Map<String, Object> notice_upd(@RequestBody NoticeEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.notice_upd(p));
		System.out.println("성공");
		return noticeWriteResult;
	}

	// 공지사항 조회수

	@ResponseBody
	@PutMapping("/updNoticeView")
	public Map<String, Object> updNoticevieView(NoticeEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.updNoticevieView(p));
		noticeWriteResult.put("notice_view", p.getNotice_view());
		return noticeWriteResult;
	}

	// 공지사항 글삭제
	@ResponseBody
	@DeleteMapping("/notice_del")
	public Map<String, Object> notice_del(NoticeEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.Notice_del(p));
		return noticeWriteResult;
	}

	// 문의사항
	
	@GetMapping("/question")
	public String question(Model model, QuestionDTO p) {
		UserPrincipal principal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		p.setQuestion_userpk(principal.getUser_pk());   
		p.setUser_auth(principal.getUser_auth());
		// p.setQuestion_userpk(question_userpk);
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
		service.regQuestion(p);
		noticeWriteResult.put("result", p.getQuestion_pk());
		return noticeWriteResult;
	}

	@ResponseBody
	@PostMapping("/question_img")
	public Map<String, Object> question_img(MultipartFile img, @RequestParam("question_pk") int question_pk) {
		Map<String, Object> questionWriteResult = new HashMap<String, Object>();
		try{
			questionWriteResult.put("result", service.question_img(img, question_pk));
		} catch(Exception e) {
			questionWriteResult.put("result", "/images/question/basic_cs.jpg");
		}finally {
			return questionWriteResult;
		}
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

	// 문의사항 조회수
	@ResponseBody
	@PutMapping("/updQuestionView")
	public Map<String, Object> updQuestionView(QuestionEntity p) {
		Map<String, Object> QuestionWriteResult = new HashMap<String, Object>();
		QuestionWriteResult.put("result", service.updQuestionView(p));
		QuestionWriteResult.put("question_view", p.getQuestion_view());
		return QuestionWriteResult;
	}

	// 문의사항 글삭제
	@ResponseBody
	@DeleteMapping("/question_del")
	public Map<String, Object> question_del(QuestionEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.question_del(p));
		return noticeWriteResult;
	}

	//문의사항 댓글 등록
	@ResponseBody
	@PostMapping("/question_cmt_reg")
	public Map<String, Object> question_cmt_reg(@RequestBody AnswerEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.question_cmt_reg(p));
		return noticeWriteResult;
	}

	// 문의사항 댓글 삭제
	@ResponseBody
	@DeleteMapping("/answer_del")
	public Map<String, Object> answer_del(AnswerEntity p) {
		Map<String, Object> noticeWriteResult = new HashMap<String, Object>();
		noticeWriteResult.put("result", service.answer_del(p));
		return noticeWriteResult;
	}

}