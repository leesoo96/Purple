<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/mypage/userinfo.css" />

<div id="userinfo_container">
  <div id="userinfo_backimg">
    <img src="resources/img/test/background.jpg">
  </div>

  <div id="userinfo_img">
    <img src="resources/img/common/basic_profile.png" alt="기본프로필사진" />
  </div>

  <form id="userinfo_content" action="" method="post">
    <div class="userinfo_nm">
      <span>홍길동</span>
    </div>
    <div class="userinfo_id">
      <span>@6WRihPAbtL14F1l</span>
    </div>
    <div class="userinfo_introduce">
      <span>하이루하이루하이루하이루하이루하이루하이루</span>
    </div>
    <div class="userinfo_registeration">
      <span>2021년 1월</span>
    </div>
    <div class="userinfo_following">
      <a href="#"><span style="font-size: 15px">0 Following</span></a>
      <a href="#"><span style="font-size: 15px">0 Followers</span></a>
    </div>
  </form>

  <div id="userinfo_button">
    <button id="userinfo_btn" onclick="friend_add()">친구 추가</button><br />
  </div>
</div>

<div id="userinfo_menu">
  <i class="fas fa-pen fa-2x"></i>
  <i class="fas fa-heart fa-2x"></i>
</div>


</div>
<!-- content_detail -->
