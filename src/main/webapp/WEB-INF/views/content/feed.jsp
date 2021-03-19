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
<div id="feed" >
   <%-- <c:forEach items="${feedListData}" var="item"> --%>
      <div class="feed_container">
         <div class="feed_title">
            <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span>USER234</span><span>2021-01-23</span> 
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
                  <p>
                     <a href="#">#해시태그</a>
                  </p>
               <%-- </c:forEach>
            </c:if> --%>
            <p>
               본문입니다.
            </p>
         </div>
         <div class="feed_functionbar">
            <i class="far fa-heart"></i><span>22</span>
            <i class="fal fa-comment"></i><span>11</span>
            <i class="far fa-bookmark"></i>
         </div>
      </div>
      
      <div class="feed_container" >
         <div class="feed_title">
            <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span>USER234</span><span>2021-01-23</span> 
            <i class="fas fa-ellipsis-h"></i>
         </div>

         <%-- <c:if test="${!empty item.media_url}">  --%>
            <div class="feed_img" onload="loadImg()">
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
                  <p>
                     <a href="#">#해시태그</a>
                  </p>
               <%-- </c:forEach>
            </c:if> --%>
            <p>
               본문입니다.
            </p>
         </div>
         <div class="feed_functionbar">
            <i class="far fa-heart"></i><span>22</span>
            <i class="fal fa-comment"></i><span>11</span>
            <i class="far fa-bookmark"></i>
         </div>
      </div>
   <%-- </c:forEach> --%>

   <%-- <c:forEach items="${feedListData}" var="item"> --%>
      <div class="feed_container" >
         <div class="feed_title">
            <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
            <span>USER234</span><span>2021-01-23</span> 
            <i class="fas fa-ellipsis-h"></i>
         </div>

         <%-- <div>${fn:length(item.media_url)}</div>   <!-- List 갯수 구하는 방법 --> --%>
         <%-- <c:if test="${!empty item.media_url}">  --%>
            <div class="feed_img" onload="loadImg()">
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
                  <p>
                     <a href="#">#해시태그</a>
                  </p>
               <%-- </c:forEach>
            </c:if> --%>
            <p>
               본문입니다.
            </p>
         </div>
         <div class="feed_functionbar">
            <i class="far fa-heart"></i><span>22</span>
            <i class="fal fa-comment"></i><span>11</span>
            <i class="far fa-bookmark"></i>
         </div>
      </div>
   <%-- </c:forEach> --%>
</div>   <!-- feed -->

<div class="feed_overlay">
   <div class="feed_overlay_content">
      <div class="feedDetail">
               
         <div class="feedDetail_img">
            <div class="detail_previous">
               <i class="fas fa-chevron-left"></i>
            </div> 

            <%-- <img src="resources/img/feed/demo3.PNG">
            <img src="resources/img/feed/demo4.PNG">
            <img src="resources/img/feed/demo3.PNG">
            <img src="resources/img/feed/demo1.jpg"> --%>

            <div class="detail_next">
               <i class="fas fa-chevron-right"></i>
            </div>
         </div> <!-- feedDetail_img -->

         <div id="feedDetail_contents_container">
            <div class="feedDetail_contents">
               <div class="feed_title">
                  <%-- <img src="resources/img/common/basic_profile.png" alt="기본프로필사진"> --%>
                  <span>
                  <%-- user_id --%>
                  </span> 
                  <span>
                  <%-- Feb 11 --%>
                  </span> 
                  <i class="far fa-bookmark"></i> 
                  <i class="fas fa-times"></i>
               </div> 

               <div class="feed_content">
                  <p>
                     <a href="#">
                        <%-- #테스트용 --%>
                     </a> 
                     <a href="#">
                        <%-- #hashtag --%>
                     </a>
                  </p>
                  <p>
                     <%-- 2018년 3월 서비스를 시작한 눈누가 3년만에 새롭게 개편되었습니다. 오늘부터 개편된 2.0 버전의 눈누
                     베타 서비스를 시작합니다. 사용하시면서 오류나 불편한 점이 있다면 메일로 제보해주세요. 감사합니다! --%>
                  </p>
               </div>
               
               <div class="feed_functionbar">
                  <i class="far fa-heart"></i> 
                  <span>
                  <%-- 26 --%>
                  </span> 
                  <i class="fal fa-comment"></i> 
                  <span>
                  <%-- 22 --%>
                  </span>
               </div> 
               
               <%-- feed 댓글  
               <div class="feed_comment">
                  <div class="comment_read_container">
                     <div class="comment_read">
                        <div class="cmtReadDetail">
                           <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
                           <span>
                              @comment_first
                           </span>
                           <p>
                              댓글이 없어
                           </p>

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
                        </div>

                        <div class="cmtReadDetail">
                           <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
                           <span>
                              @comment_three
                           </span>
                           <p>
                              잠온다
                           </p>
                        </div> 
                     </div>  comment_read 
                  </div>
               </div>  feed_comment --%>

               <div class="comment_input">
                  <input type="text" placeholder="댓글을 입력해주세요">
                  <input type="submit" value="입력">
               </div>

            </div> <!-- feedDetail_contents -->
         </div>  <!-- feedDetail_contents_container -->
      </div> <!--  feed_detail -->
   </div> <!-- feed_overlay_content -->
</div>  <!-- feed_overlay  -->   