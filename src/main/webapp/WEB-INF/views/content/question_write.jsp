<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<link rel="stylesheet" href="/resources/css/common/temp.css" />
<link rel="stylesheet" href="/resources/css/cs/cs_write.css" />
<script defer src="/resources/js/cs/question_write.js"></script>

<div id="cs_write_title">${questionUpd == null ? '문의사항 등록' : '문의사항 수정'}</div>
<form id="question_write_form">

	<div>
		<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
			<sec:authentication property="principal" var="userInfo" />
			<input type="hidden" name="question_userpk" value="${userInfo.user_pk}">
		</sec:authorize>
	</div>
	<div><input type="hidden" name="question_pk" value="${questionUpd.question_pk}"></div>
	<table id="cs_write_table">
		<tr>
			<th>제목</th>
			<td><input type="text" id="title" name="question_title" value="${questionUpd.question_title}"
				placeholder="제목을 입력하세요"></td>
		</tr>
		<tr>
			<th>문의유형</th>
			<td id="category"><select name="question_type">
					<option value="0" selected="selected">계정 관리</option>
					<option value="1">서비스이용</option>
			</select></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="question_img" accept="img/**"></td>
		</tr>
		<tr>
			<th>문의내용</th>
			<td>
				<img class="ctnt_img">
				<textarea id="text" cols="60" rows="15" name="question_ctnt" 
					placeholder="내용을 입력하세요">${questionUpd.question_ctnt}</textarea></td>
		</tr>
	</table>

	<div id="write_btns">
		<button id="cs_write_submit_btn" onclick="question_write_submit_btn()">${questionUpd == null ? '등록' : '수정'}</button>
		<button id="cs_write_return_btn" type="button" onclick="return_page()">돌아가기</button>
	</div>
</form>
