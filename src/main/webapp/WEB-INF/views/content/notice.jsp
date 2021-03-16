
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<link rel="stylesheet" href="resources/css/common/temp.css" />
<link rel="stylesheet" href="resources/css/cs/notice.css" />
<script defer src="resources/js/cs/notice.js"></script>

<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	<div id="cs_reg">
		<a href="notice_write">공지사항 등록</a>
	</div>
</sec:authorize>
<c:forEach items="${requestScope.noticeData.list}" var="item">
	<div id="cs_notice_container">
		<div class="cs_notice_content">
			<div class="cs_notice_titlebar" data-pk="${item.notice_pk}">
				<div class="cs_notice_title">
					<span>[공지사항]</span><span>${item.notice_title}</span>
				</div>
				<div class="cs_notice_extratitle">
					<span>조회수: </span> <span>${item.notice_view}</span><span>  | 
					</span>
					<span>
					<fmt:parseDate value="${item.notice_writedate}" var="dateValue" pattern="yyyy-MM-dd HH:mm:ss"/>
					<fmt:formatDate value="${dateValue}" pattern="yyyy.MM.dd"/>
						<c:out value="${today}"/>

					</span>
				</div>
			</div>
			<div class="cs_notice_detail">
				<img src="/src/main/webapp/resources/img/cs/notice/${item.notice_img}">
				<p>${item.notice_ctnt}</p>

				<span>눈누2.0 베타 서비스 바로가기<br /> 제보 메일: projectnoonnu@gmail.com
				</span>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<button onclick="cs_del_btn(${item.notice_pk})">삭제</button>
					<a href="notice_upd?notice_pk=${item.notice_pk}">수정</a>
				</sec:authorize>
			</div>
		</div>
	</div>
</c:forEach>

<!-- 페이징-->
<form id="pageFrm" action="/notice" method="get">
	<input type="hidden" name="page" value="1">
</form>

<c:if test="${requestScope.noticeData.SPage > 1}">
	<span class="page" onclick="pageClick(1)">1</span>
	<span>...</span>
</c:if>	
		
<c:forEach begin="${requestScope.noticeData.SPage}" end="${requestScope.noticeData.EPage}" var="i">			
	<span class="page ${requestScope.noticeData.page == i ? 'selected' : ''}" onclick="pageClick(${i})">${i}</span>
</c:forEach>
		
<c:if test="${requestScope.noticeData.EPage < requestScope.noticeData.maxPageNum}">
	<span>...</span>
	<span class="page" onclick="pageClick(${requestScope.noticeData.maxPageNum})">${requestScope.noticeData.maxPageNum}</span>
</c:if>