<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="resources/css/feed/feed.css">
<link rel="stylesheet" href="resources/css/feed/feedDetail.css">
<script defer src="resources/js/feed/feed.js"></script>
<script defer src="resources/js/feed/feedDetail.js"></script>
<div id="selectBox">
  <select name="feed">
    <option value="" selected>최신순</option>
    <option value="">인기순</option>
  </select>
</div>
<div id="feed">
   <%-- <c:forEach items="${feedListData}" var="item"> --%>
      <div class="feed_container">
         <div class="feed_title">
            <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span name="user_id">USER234</span>
            <span name="feed_writedate">2021-01-23</span> 
            <i class="far fa-bookmark"></i>
            <i class="fas fa-ellipsis-h"></i>
         </div>

         <%-- <div>${fn:length(item.media_url)}</div>   <!-- List 갯수 구하는 방법 --> --%>
         <%-- <c:if test="${!empty item.media_url}">  --%>
            <div class="feed_img">
               <div class="previous">
                  <i class="fas fa-chevron-left"></i>
               </div>
               <div class="feed_imgList">
                  <img src="resources/img/feed/demo3.PNG">
                  <img src="resources/img/feed/demo4.PNG">
                  <img src="resources/img/feed/demo3.PNG">
                  <img src="resources/img/feed/demo1.jpg">
               </div>
               <%-- <c:forEach items="${item.media_url}" var="media_url">
                  <img src="resources/img/feed/${media_url}" style="width: 100%">
               </c:forEach> --%>
               <div class="next">
                  <i class="fas fa-chevron-right"></i>
               </div>
            </div>
         <%-- </c:if> --%>

         <div class="feed_functionbar">
            <i class="far fa-heart"></i><span name="heartValue">22</span>
            <i class="fal fa-comment"></i><span name="cmtValue">11</span>
         </div>

         <div class="feed_content" onclick="feedDetail(this)">
            <%-- <c:if test="${item.hashtag_ctnt != null || item.hashtag_ctnt !=''}">
               <c:forEach items="${item.hashtag_ctnt}" var="hashtag_ctnt"> --%>
                  <p>
                     <a href="#" name="feed_content_hashtag">#해시태그</a>
                  </p>
               <%-- </c:forEach>
            </c:if> --%>
            <div>
               <p name="feed_content">
                  본문입니다.안녕하세요안녕하세요안녕하세요</p>
            </div>
         </div>
      </div>
   <%-- </c:forEach> --%>
</div>   <!-- feed -->

<div class="feed_overlay">
   <div class="feed_overlay_content">
      <div class="feedDetail">
               
      </div> 
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