<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="resources/css/mypage/userinfo.css">

<div id="userinfo_container">
    <div id="userinfo_backimg">
        <!-- 배경 이미지 삽입 공간-->
    </div>

    <div id="userinfo_img">
        <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
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
            <a href="#"><span style="font-size: 15px;">0 Following</span></a>
            <a href="#"><span style="font-size: 15px;">0 Followers</span></a>
        </div>
    </form>

    <div id="userinfo_button">
        <button id="userinfo_btn" onclick="friend_add()">친구 추가</button><br>
    </div>
</div>

<div id="userinfo_menu">
  <i class="fas fa-pen fa-2x"></i>
  <i class="fas fa-heart fa-2x"></i>
</div>

<div id="content_detail">
  <div class="upload_user">
    <img src="img/temp/profie.png" alt="기본프로필사진">
    <span>friends_threeeeeeeee</span>
    <div class="upload_content">
      <span>테스트용 텍스트입니다.</span>
    </div>
  </div>

  <div class="upload_user">
    <img src="img/temp/profie.png" alt="기본프로필사진">
    <span>friends_one</span>
    <div class="upload_content">
      <img src="img/temp/demo1.jpg" alt="">
      <img src="img/temp/demo4.png" alt="">
    </div>
  </div>

  <div class="upload_user">
    <img src="img/temp/profie.png" alt="기본프로필사진">
    <span>friends_two</span>
    <div class="upload_content">
      <span>테스트용 텍스트입니다.</span>
    </div>
  </div>

  <div class="upload_user">
    <img src="img/temp/profie.png" alt="기본프로필사진">
    <span>Unknown friend</span>
    <div class="upload_content">
      <img src="img/temp/demo2.png" alt="">
    </div>
  </div>

  <div class="upload_user">
    <img src="img/temp/profie.png" alt="기본프로필사진">
    <span>friends_threeeeeeeee</span>
    <div class="upload_content">
      <img src="img/temp/demo3.png" alt="">
    </div>
  </div>
</div>  <!-- content_detail -->