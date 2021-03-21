<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" href="resources/css/feed/feed.css">
<link rel="stylesheet" href="resources/css/feed/feedDetail.css">
<script defer src="resources/js/feed/feed.js"></script>
<script defer src="resources/js/feed/feedDetail.js"></script>
<div id="selectBox">
  <select name="feed">
    <option value="1" selected>최신순</option>
    <option value="2">인기순</option>
    <option value="3">친구</option>
  </select>
</div>
<div id="feed">
   <%-- <c:forEach items="${feedListData}" var="item"> --%>
      <div class="feed_container" >
         <div class="feed_title">
            <img class="title_img" src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span name="user_id">USER234</span><span name="feed_writedate">2021-01-23</span> 
            <i class="fas fa-ellipsis-h"></i>
         </div>

         <%-- <div>${fn:length(item.media_url)}</div>   <!-- List 갯수 구하는 방법 --> --%>
         <%-- <c:if test="${!empty item.media_url}">  --%>
            <div class="feed_img">
               <div class="previous" onclick="previousImg(this)">
                  <i class="fas fa-chevron-left"></i>
               </div>
               <div class="feed_imgList">
               <img src="/resources/img/feed/demo1.jpg">
               <img src="/resources/img/feed/demo3.PNG">
               <img src="/resources/img/feed/demo4.PNG">
               </div>
               <div class="next" onclick="nextImg(this)">
                  <i class="fas fa-chevron-right"></i>
               </div>
            </div>
         <%-- </c:if> --%>


         <div class="feed_content">
            <%-- <c:if test="${item.hashtag_ctnt != null || item.hashtag_ctnt !=''}">
               <c:forEach items="${item.hashtag_ctnt}" var="hashtag_ctnt"> --%>
                  <a name="detailCtnt_hashtag" href="#">#해시태그1</a>
  
               <%-- </c:forEach>
            </c:if> --%>
            <div onclick="feedDetail(this)">
               <p name="feed_content">
                  본문입니다.안녕하세요안녕하세요안녕하세요</p>
            </div>
         </div>
         <div class="feed_functionbar">
            <i class="fal fa-comment" onclick="feedDetail(this)"></i><span name="cmtValue">11</span>
            <i class="far fa-heart"></i><span name="heartValue">22</span>
            <i class="far fa-bookmark"></i>
         </div>
      </div>
   <%-- </c:forEach> --%>
      
   <%-- <c:forEach items="${feedListData}" var="item"> --%> 
      <div class="feed_container" >
         <div class="feed_title">
            <img class="title_img" src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span name="user_id">USER22232</span><span name="feed_writedate">2021-03-19</span> 
            <i class="fas fa-ellipsis-h"></i>
         </div>

         <%-- <div>${fn:length(item.media_url)}</div>   <!-- List 갯수 구하는 방법 --> --%>
         <%-- <c:if test="${!empty item.media_url}">  --%>
            <div class="feed_img">
               <div class="previous" onclick="previousImg(this)">
                  <i class="fas fa-chevron-left"></i>
               </div> --%>
               <div class="feed_imgList">
               <%-- <img src="resources/img/feed/demo1.jpg">
               <img src="resources/img/feed/demo3.PNG">
               <img src="resources/img/feed/demo4.PNG"> --%>
               </div>
               <%-- <div class="next" onclick="nextImg(this)">
                  <i class="fas fa-chevron-right"></i>
               </div> --%>
            </div>
         <%-- </c:if> --%>


         <div class="feed_content">
            <%-- <c:if test="${item.hashtag_ctnt != null || item.hashtag_ctnt !=''}">
               <c:forEach items="${item.hashtag_ctnt}" var="hashtag_ctnt"> --%>
                  <a name="detailCtnt_hashtag" href="#">#해시태그29</a>
  
               <%-- </c:forEach>
            </c:if> --%>
            <div onclick="feedDetail(this)">
               <p name="feed_content">
                  본문입니다.2333333안녕2하세22222요안녕2하2세요22안녕2222하세요2</p>
            </div>
         </div>
         <div class="feed_functionbar">
            <i class="fal fa-comment" onclick="feedDetail(this)"></i><span name="cmtValue">7</span>
            <i class="far fa-heart"></i><span name="heartValue">19</span>
            <i class="far fa-bookmark"></i>
         </div>
      </div>

   <%-- <c:forEach items="${feedListData}" var="item"> --%>
      <div class="feed_container" >
         <div class="feed_title">
            <img class="title_img" src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span name="user_id">USER2341234</span><span name="feed_writedate">2021-01-23</span> 
            <i class="fas fa-ellipsis-h"></i>
         </div>

         <%-- <c:if test="${!empty item.media_url}">  --%>
            <div class="feed_img">
               <div class="previous" onclick="previousImg(this)">
                  <i class="fas fa-chevron-left"></i>
               </div>
               <div class="feed_imgList">
               <img src="resources/img/feed/demo1.jpg">
               <img src="resources/img/feed/demo3.PNG">
               <img src="resources/img/feed/demo4.PNG">
               </div>
               <div class="next" onclick="nextImg(this)">
                  <i class="fas fa-chevron-right"></i>
               </div>
            </div>
         <%-- </c:if> --%>

         <div class="feed_content" onclick="feedDetail(this)">
            <%-- <c:if test="${item.hashtag_ctnt != null || item.hashtag_ctnt !=''}">
               <c:forEach items="${item.hashtag_ctnt}" var="hashtag_ctnt"> --%>
                  <a name="detailCtnt_hashtag" href="#">#해시태그31</a>
  
               <%-- </c:forEach>
            </c:if> --%>
            <div onclick="feedDetail(this)">
               <p name="feed_content">
                  본문입니다.안녕하세요안녕하세요안녕하세요</p>
            </div>
         </div>
         <div class="feed_functionbar">
            <i class="fal fa-comment" onclick="feedDetail(this)"></i><span name="cmtValue">11</span>
            <i class="far fa-heart"></i><span name="heartValue">22</span>
            <i class="far fa-bookmark"></i>
         </div>
      </div>
   <%-- </c:forEach> --%>

   <div class="feed_container" >
         <div class="feed_title">
            <img class="title_img" src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span name="user_id">USER222442</span><span name="feed_writedate">2021-03-19</span> 
            <i class="fas fa-ellipsis-h"></i>
         </div>

         <%-- <div>${fn:length(item.media_url)}</div>   <!-- List 갯수 구하는 방법 --> --%>
         <%-- <c:if test="${!empty item.media_url}">  --%>
            <div class="feed_img" onload="loadImg()">
               <%-- <div class="previous" onclick="previousImg(this)">
                  <i class="fas fa-chevron-left"></i>
               </div> --%>
               <div class="feed_imgList">
               <%-- <img src="resources/img/feed/demo1.jpg">
               <img src="resources/img/feed/demo3.PNG">
               <img src="resources/img/feed/demo4.PNG"> --%>
               </div>
               <%-- <div class="next" onclick="nextImg(this)">
                  <i class="fas fa-chevron-right"></i>
               </div> --%>
            </div>
         <%-- </c:if> --%>


         <div class="feed_content">
            <%-- <c:if test="${item.hashtag_ctnt != null || item.hashtag_ctnt !=''}">
               <c:forEach items="${item.hashtag_ctnt}" var="hashtag_ctnt"> --%>
                  <a name="detailCtnt_hashtag" href="#">#해시태그22</a>
  
               <%-- </c:forEach>
            </c:if> --%>
            <div onclick="feedDetail(this)">
               <p name="feed_content">
                  본문입니다.2222222안녕2하세22222요안녕2하2세요22안녕2222하세요2</p>
            </div>
         </div>
         <div class="feed_functionbar">
            <i class="fal fa-comment" onclick="feedDetail(this)"></i><span name="cmtValue">7</span>
            <i class="far fa-heart"></i><span name="heartValue">19</span>
            <i class="far fa-bookmark"></i>
         </div>
      </div>
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
