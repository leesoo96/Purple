<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="/resources/css/feed/feed.css">
<link rel="stylesheet" href="/resources/css/feed/feedDetail.css">
<%-- <script defer src="/resources/js/feed/sockjs.min.js"></script> --%>
<script defer src="/resources/js/feed/feed.js"></script>
<script defer src="/resources/js/feed/feedDetail.js"></script>

<div id="selectBox">
  <select name="feed">
    <option value="1" selected>최신순</option>
    <option value="2">인기순</option>
  </select>
</div>
<div id="feed">
</div>   <!-- feed -->

<div class="feed_overlay">
      <div class="feedDetail">
               
      </div> 
   </div>

<!-- 댓글 html 코드 
<div class="cmtReadDetail">
   <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
      <span>
         @comment_first
      </span>

      <p>
         댓글이 없어
      </p>


   대댓글 
   <div class="cmt_reply">
      <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
      <span>
         @comment_first
      </span>

      <p>
         댓글 생김
      </p>
   </div>

   <div class="cmt_reply">
      <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
      <span>
         @comment_first
      </span>

      <p>
         대대대대댓글
      </p>
   </div>
</div>

<div class="cmtReadDetail">
   <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
   <span>
      @comment_two
   </span>

   <p>
      흐에에ㅔㅔ
   </p>
</div> -->            
