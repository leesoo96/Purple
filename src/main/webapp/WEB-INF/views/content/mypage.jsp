<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="resources/css/mypage/mypage.css">
<link rel="stylesheet" href="resources/css/mypage/mypagePw_update.css">
<script defer src="resources/js/mypage/mypage.js"></script>
<script defer src="resources/js/mypage/mypagePw_update.js"></script>


<div id="mypage_userinfo_container">
  <div id="mypage_userinfo_backimg">
    <!-- 배경 이미지 삽입 공간-->
  </div>

  <div id="mypage_userinfo_img">
    <img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
  </div>

  <form id="mypage_userinfo_content" action="" method="post">
    <div class="mypageuser_nm">
      <span>홍길동</span>
    </div>
    <div class="mypageuser_id">
      <span>@6WRihPAbtL14F1l</span>
    </div>
    <div class="mypageuser_introduce">
      <span>하이루하이루하이루하이루하이루하이루하이루</span>
    </div>
    <div class="mypageuser_registeration">
      <span>2021년 1월</span>
    </div>
    <div class="mypageuser_following">
      <a href="#"><span style="font-size: 15px;">0 Following</span></a>
      <a href="#"><span style="font-size: 15px;">0 Followers</span></a>
    </div>
  </form>

  <div id="mypage_userinfo_button">
    <button id="mypage_modcontent_btn" onclick="profileMod()">프로필 수정</button><br><br>
    <button id="mypage_pw_btn" onclick="popupCenter()">비밀번호 변경</button>
  </div>
</div>

<div id="mypage_menu">
  메뉴 넣을 자리
</div>

<div class="black_bg"></div>
<div class="modal_wrap">
  <div id="modal_background">
    <div class="mypage_mod">
      <div class="exit">
        <a href="" class="close">
          <img src="img/mypage/times-solid.svg" alt="닫기">
        </a>
      </div>

      <div class="mypage_top">
        <span>Edit profile</span>
        <div class="content_btn">
          <button type="button" id="mypage_modal_btn" onclick="return mypage_store();">Save</button>
        </div>
      </div>

      <div class="scroll">
        <div class="profile_background">
          <img src="img/temp/profie.png" class="profile_img" alt="기본프로필사진">
          <img src="img/mypage/add_photo.png" class="camera_icon" alt="카메라 사진">
          <img src="img/mypage/add_photo.png" class="backgroundcamera_icon" alt="카메라 사진">
        </div>
        
        <div class="mypage_mod_content">          
          <div class="mypage_user_name">
            <input type="text" class="nm_text" id="mypage_nm" name="user_nm" placeholder="Name" onkeyup="chkword(this,50)"> 
          </div>
          
          <div class="user_introduce">
            <textarea class="doc_text" id="mypage_counter" rows="8" cols="40" placeholder="Bio" onkeyup="chkword(this,160)"></textarea>
          </div>

          <div class="user_place">
            <input type="text" class="place_text" id="mypage_place" name="user_location" placeholder="Location" onkeyup="chkword(this,30)"> 
          </div>

          <div class="user_site">
            <input type="text" class="site_text" id="mypage_site" name="user_website" placeholder="Website" onkeyup="chkword(this,100)">
          </div>

          <div class="user_etc">
            <span class="user_birthdate">Birth date</span>
            <span class="dot">·</span>
            <span class="user_birthday">June 24, 1995</span>
            <span class="user_edit">
              <a href="#">Edit</a>
            </span>
          </div>
        </div>  <!-- mypage_mod_content -->
      </div>  <!-- scroll -->
    </div>  <!-- mypage_mod -->
  </div>  <!-- modal_background -->
</div>

<div class="pw_bg"></div>
<div class="modal_pw">
  <div class="modal_close">
    <a href="#"></a>
  </div>
  <div class="mypage_change">
    <span class="pw_title"><h1>비밀번호 변경</h1></span>
    
    <form action="" method="post" id="frm" onsubmit="return chkPw();">
      <div class="bigPw_line">
        <div>
          <input type="password" name="current_pw" placeholder="현재 비밀번호" minlength="4" maxlength="12">
        </div>
        <div>
          <input type="password" name="user_pw" placeholder="새로운 비밀번호" minlength="4" maxlength="12">
        </div>
        <div>
          <input type="password" name="chk_user_pw" placeholder="확인 비밀번호" maxlength="12">
        </div>
      </div>
      <div class="pw_btn">
        <button type="submit" class="mypagePw_modal_btn">저장</button>
      </div>
    </form>
  </div>  <!-- mypage_change -->
</div>  <!-- modal_pw -->

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