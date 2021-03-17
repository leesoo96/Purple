
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<link rel="stylesheet" href="resources/css/common/temp.css" />
<link rel="stylesheet" href="resources/css/cs/question.css" />
<script defer src="resources/js/cs/question.js"></script>

<div id="cs_question_container">

	<div id="cs_question_write">
		<a href="question_write">문의사항 등록</a>
	</div>

	<div id="question_title">
		<span>문의사항</span>
	</div>

	<c:forEach items="${requestScope.questionData.list}" var="item">
		<div class="cs_question_content">
			<div class="cs_question_titlebar" data-pk="${item.question_pk}">
				<div class="cs_question_title">
					<span>[
					<c:choose>
					   <c:when test="${item.question_type == 0}">
					   계정관리
					   </c:when>
					
					   <c:otherwise>
					   서비스 이용
					   </c:otherwise>
					</c:choose>
							]</span><span>${item.question_title}</span>
				</div>
				<div class="cs_question_extratitle">
					<span>조회수: </span> <span>${item.question_view}</span><span>
						| </span>
						<span>
							<fmt:parseDate value="${item.question_writedate}" var="dateValue" pattern="yyyy-MM-dd HH:mm:ss"/>
							<fmt:formatDate value="${dateValue}" pattern="yyyy.MM.dd"/>
							<c:out value="${today}"/>
						</span>
				</div>
			</div>
			<div class="cs_question_detail">
				<div class="question_detailPlus">
					<p>${item.question_ctnt}</p>
				</div>

				<div class="question_use">
					<a href="question_upd?question_pk=${item.question_pk}">수정</a>
					<button onclick="cs_del_btn(${item.question_pk})">삭제</button>
				</div>
				<P>${item.answer_ctnt}</P>
				<span>
					<fmt:parseDate value="${item.answer_writedate}" var="dateValue" pattern="yyyy-MM-dd HH:mm:ss"/>
					<fmt:formatDate value="${dateValue}" pattern="yyyy.MM.dd"/>
					<c:out value="${today}"/>
				</span>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
					<c:if test="${item.answer_pk == 0}">
						<div class="cs_cmt_reg" data-pk="${item.question_pk}">입력</div>
						<textarea class="cs_cmt_textarea"></textarea>
					</c:if>
					<div></div>
					<c:if test="${item.answer_pk != 0}">
						<button onclick="answer_del_btn(${item.answer_pk})">삭제</button>
					</c:if>
				</sec:authorize>
			</div>
		</div>
	</c:forEach>
</div>

<!-- 페이징-->
<form id="pageFrm" action="/question" method="get">
	<input type="hidden" name="page" value="1">
</form>

<c:if test="${requestScope.questionData.SPage > 1}">
	<div id="pagingLocation">
		<span class="page" onclick="pageClick(1)">1</span>
		<span>...</span>
	</div>
</c:if>	
		
<c:forEach begin="${requestScope.questionData.SPage}" end="${requestScope.questionData.EPage}" var="i">			
	<div id="pagingLocation">
		<span class="page ${requestScope.questionData.page == i ? 'selected' : ''}" onclick="pageClick(${i})">${i}</span>
	</div>
</c:forEach>
		
<c:if test="${requestScope.questionData.EPage < requestScope.questionData.maxPageNum}">
	<div id="pagingLocation">
		<span>...</span>
		<span class="page" onclick="pageClick(${requestScope.questionData.maxPageNum})">${requestScope.questionData.maxPageNum}</span>
	</div>
</c:if>


<div class="cs_black_bg"></div>



