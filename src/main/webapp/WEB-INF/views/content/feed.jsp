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
   <c:forEach items="${feedListData}" var="item">
      <div class="feed_container">
         <div class="feed_title">
            <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span>${item.user_id}</span><span>${item.feed_writedate}</span> <i class="far fa-bookmark"></i>
            <i class="fas fa-ellipsis-h"></i>
         </div>

         <%-- <div>${fn:length(item.media_url)}</div>   <!-- List 갯수 구하는 방법 --> --%>
         <c:if test="${!empty item.media_url}"> 
            <div class="feed_img">
               <div class="previous">
                  <i class="fas fa-chevron-left"></i>
               </div>
               <c:forEach items="${item.media_url}" var="media_url">
                  <img src="resources/img/feed/${media_url}" style="width: 100%">
               </c:forEach>
               <div class="next">
                  <i class="fas fa-chevron-right"></i>
               </div>
            </div>
         </c:if>

         <div class="feed_functionbar">
            <i class="far fa-heart"></i><span>${item.favorite_count}</span>
            <i class="fal fa-comment"></i><span>${item.comment_count}</span>
         </div>

         <div class="feed_content">
            <c:if test="${item.hashtag_ctnt != null || item.hashtag_ctnt !=''}">
               <c:forEach items="${item.hashtag_ctnt}" var="hashtag_ctnt">
                  <p>
                     <a href="#">${hashtag_ctnt}</a>
                  </p>
               </c:forEach>
            </c:if>
            <p>
               ${item.feed_ctnt}
            </p>
         </div>
      </div>
   </c:forEach>
</div>   <!-- feed -->