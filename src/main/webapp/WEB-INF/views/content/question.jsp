
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<link rel="stylesheet" href="resources/css/common/temp.css" />
<link rel="stylesheet" href="resources/css/cs/question.css" />
<script defer src="resources/js/cs/question.js"></script>
<div id="cs_question_container">
	<div id="cs_question_write">
		<a href="question_write"><button>+</button></a>
	</div>
	<c:forEach items="${questionData}" var="item">
		<div class="cs_question_content">
			<div class="cs_question_titlebar">
				<div class="cs_question_title">
					<span>[${item.question_typ}]</span><span>${item.question_title}</span>
				</div>
				<div class="cs_question_extratitle">
					<span>조회수: </span> <span>${item.question_views}</span><span>
						| </span><span>${item.question_writedate}</span>
				</div>
			</div>
			<div class="cs_question_detail">
				<p>${item.question_ctnt}</p>

				<span>눈누2.0 베타 서비스 바로가기<br /> 제보 메일: projectnoonnu@gmail.com
				</span>
				<button onclick="cs_del_btn(${item.question_pk})">삭제</button>
				<a href="question_upd?question_pk=${item.question_pk}">수정</a>
				<button class="cs_cmt_btn">댓글</button>
			</div>
			<div class="cs_cmt_vlew">
				<div class="cs_modal_close">
					<a href="#">close</a>
				</div>
				댓글
			</div>
		</div>
	</c:forEach>
</div>
<div id="cs_paging"></div>


