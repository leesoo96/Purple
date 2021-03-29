<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="menus">
          <div id="logo">
            <a href="/feed">
              <img src="/resources/img/common/logo1.png" alt="로고">
            </a>
          </div>
          <div id="home">
            <a href="/feed">
              <i class="fas fa-home menu_icons"></i>
              <span>홈</span>
            </a>
          </div>
          <div id="search">
            <a href="/search">
              <i class="fas fa-search menu_icons"></i>
              <span>검색하기</span>
            </a>
          </div>
          <div id="news">
            <a href="/alarm">
              <i class="far fa-bell menu_icons"></i>
              <span>알림</span>
            </a>
            <div id="alarm">
               <div>17</div> 
            </div>
          </div>
          <div id="bookmark">
            <a href="/bookmark">
              <i class="far fa-bookmark menu_icons"></i>
              <span>북마크</span>
            </a>
          </div>
          <div id="cs">
              <i class="fas fa-cogs menu_icons"></i>
              <span>고객센터</span>
              <div id="cs_menus">
                <a href="/notice">공지사항</a>
                <a href="/question">문의사항</a>
              </div>   
          </div>
         
          <button id="userWrite_btn" onclick="on()"><span>글쓰기</span></button>
          <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
          <sec:authentication property="principal" var="userInfo"/>

          <div id="temp_user">
            <c:if test="${empty userInfo.user_profileimg}">
              <img src="/resources/img/common/basic_profile.png" alt="기본프로필사진">
            </c:if>
            <c:if test="${not empty userInfo.user_profileimg}">
              <img src=${userInfo.user_profileimg} alt="기본프로필사진">
            </c:if>  

  				<p>${userInfo.user_id }</p>
         	</sec:authorize>
         	
            <i class="fas fa-grip-lines"></i>
          </div>
          <div class="only_modalClose"></div> <!-- user_modal창을 닫기위한 도구적 클래스 -->
          <div id="temp_user_modal">
            <c:if test="${empty userInfo.user_profileimg}">
              <img src="/resources/img/common/basic_profile.png" alt="기본프로필사진">
            </c:if>
            <c:if test="${not empty userInfo.user_profileimg}">
              <img src=${userInfo.user_profileimg} alt="기본프로필사진">
            </c:if>  
            
            <p>${userInfo.user_name }</p>
            <a href="/mypage">마이페이지</a>
            <a class="logout" href="/logout">로그아웃</a>
          </div>
          
        </div> <!-- menus -->