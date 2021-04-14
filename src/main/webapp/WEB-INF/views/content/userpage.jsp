<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/resources/css/mypage/userpage.css" />
<link rel="stylesheet" href="/resources/css/feed/feed.css">
<link rel="stylesheet" href="/resources/css/feed/feedDetail.css">

<script defer src="/resources/js/mypage/userpage.js"></script>


<input type="hidden" id="friend_pk" value=${userInfo.user_pk} />
<div id="userpage_userinfo_container">
  <div id="userpage_userinfo_backimg">
    <img onerror="this.style.display='none'" src="${userInfo.user_backgroundimg}">
  </div>
  <div id="userpage_userinfo_img">
    <c:if test="${not empty userInfo.user_profileimg}">
      <img src=${userInfo.user_profileimg} onerror="this.src='/resources/img/common/basic_profile.png'" alt="기본프로필사진">
  </c:if>
  <c:if test="${empty userInfo.user_profileimg}">
      <img alt="기본프로필사진" src="/resources/img/common/basic_profile.png">
  </c:if>
  </div>
  <div id="userpage_userinfo_content">
      <div class="userpageuser_id">
          <span>${userInfo.user_id}</span>
      </div>
      <div class="userpageuser_nm">
          <p>${userInfo.user_name}</p>
      </div>
      <div class="userpageuser_birth">
          <span>${userInfo.user_birth}</span>
      </div>
      <div class="userpageuser_location">
          <span>${userInfo.user_location}</span>
      </div>
      <div class="userpageuser_email">
          <span>${userInfo.user_email}</span>
      </div>
      <div class="userpageuser_bio">
          <span>${userInfo.user_bio}</span>
      </div>
  </div>
  <div id="userpage_userinfo_button">
    <button id="add_friend_btn">친구 추가</button>
  </div>
</div>

<div id="feed">
</div>
<div class="feed_overlay">
    <div class="feedDetail">
             
    </div> 
 </div>