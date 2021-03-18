<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>

<link rel="stylesheet" href="resources/css/common/temp.css" />
<link rel="stylesheet" href="resources/css/cs/cs_write.css" />
<script defer src="resources/js/cs/notice_write.js"></script>

<div id="cs_write_title">${noticeUpd == null ? '공지사항 등록' : '공지사항 수정'}</div>
<form id="notice_write_form">
	<div><input type="hidden" name="notice_pk" value="${noticeUpd.notice_pk}"></div>
	<table id="cs_write_table">
		<tr>
			<th>제목</th>
			<td><input type="text" id="title" name="notice_title"
				placeholder="제목을 입력하세요" value="${noticeUpd.notice_title}"></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="notice_img" enctype="multipart/form-data" accept="img/**"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<img class="ctnt_img">
				<textarea  id="text" cols="60" rows="15" name="notice_ctnt"
					placeholder="내용을 입력하세요">${noticeUpd.notice_ctnt}</textarea>
			</td>
		</tr>
	</table>

	<div id="write_btns">
		<button id="cs_write_submit_btn" onclick="notice_write_submit_btn()">${noticeUpd == null ? '등록' : '수정'}</button>
		<button id="cs_write_return_btn" type="button" onclick="return_page()">돌아가기</button>
	</div>
</form>
