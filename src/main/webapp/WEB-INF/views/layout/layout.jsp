<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>

<head>
  <tiles:insertAttribute name="header" />
</head>

<body>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
  <sec:authentication property="principal" var="userInfo" />
  <input type="hidden" id="user_pk" name="user_pk" value="${userInfo.user_pk}">
</sec:authorize>
  <!-- 글쓰기 모달창 -->
  <div id="overlay">
  	<div id="write_container">
	  	<button id="write_btn" onclick="off()"><img src="resources/img/common/close_icon.png"></button>
	  	<div id="write_title">Upload Post</div>
	  		<form id="write_content" action="/feed/feed_write" method="post" enctype="multipart/form-data">
          <div class='write_preview' >
            <div id="add_image">사진을 추가해 주세요</div>
            <!-- 미리보기 공간 -->  	
        	</div>
            <img src="resources/img/common/close_icon.png" id="write_icon" onclick="document.getElementById('file').click();" />
            <input type="file" id="file" name="imgs" accept="image/*" onchange="setThumbnail(event);"  onchange="document.getElementById('txt').value=this.value;" multiple />	  			
            <textarea id="write_text" name="feed_ctnt" rows="7" placeholder="내용을 입력하세요"></textarea>
            <div id="write_hashtag">
              <input type="text" onkeyup="enterkey();" name = "hashtag_ctnt" class="text_hashtag" placeholder="#해시태그(최대10개)"  />
            </div>
          <input id="submit_btn" type="submit" value="upload">   
	      </form>        
  	</div>
  </div>

  <div id="container">
    <div id="layers">
      <div id="nav1">
        <tiles:insertAttribute name="leftSide" />
      </div> <!-- nav1 -->

      <div id="main">
        <tiles:insertAttribute name="content" />
      </div> <!-- content-->

      <div id="nav2">
        <tiles:insertAttribute name="rightSide" />
      </div><!-- nav2 -->

    </div> <!-- layers -->
  </div> <!-- container-->
</body>

</html>