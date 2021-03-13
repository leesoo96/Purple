<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<link rel="stylesheet" href="resources/css/mypage/mypage.css">
<script defer src="resources/js/mypage/mypage.js"></script>


<div id="modal_background"></div>
<!-- modal_background -->
<div id="userMod_container">
	<div id="userMod_top">
		<div id="close_userMod">
			<img src="resources/img/common/close_icon.png" onclick="openCloseModal('#userMod_container', 'none')" alt="닫기">
		</div>
		<span>Edit Profile</span>
		<div id="save_userMod">
			<button type="button" id="save_userModBtn">Save</button>
		</div>
	</div>
	<div id="userMod_content">
		<div id="mod_userbackground">
			<label for="mod_background">
				<img src="" onerror="this.style.display='none'">
			</label>
			<input type="file" id="mod_background" accept="image/jpeg, image/jpg, image/png"/>
        </div>
		<div id="mod_userimg">
			<label for="mod_img">
       			<img src="resources/img/common/basic_profile.png"/>
    		</label>
			<input id="mod_img" type="file" accept="image/jpeg, image/jpg, image/png"/>
		</div>
		<form id="userModFrm">
			<input type="text" name="mod_id" placeholder="Id" maxlength="10">
			<input type="button" name="id_check" value="중복확인">
			<input type="text" name="mod_name" placeholder="Name" maxlength="5">
			<input type="text" name="mod_bio" placeholder="Bio" maxlength="20">
			<input type="text" name="mod_location" placeholder="Location" maxlength="30">
			<input type="text" name="mod_website" placeholder="Website" maxlength="30">
			<input type="date" name="mod_birth" data-placeholder="생년월일을 선택해주세요">
		</form>
	</div>
</div>

<div id="userPwMod_container">
	<div id="userPwMod_top">
		<span>비밀번호 변경</span>
		<div id="close_userPwMod">
			<img src="resources/img/common/close_icon.png" onclick="openCloseModal('#userPwMod_container', 'none')" alt="닫기">
		</div>
	</div>	
	<form name="userPwModFrm">
		<input type="password" name="user_pw" placeholder="현재 비밀번호">
		<input type="password" name="mod_pw" placeholder="변경 할 비밀번호">
		<input type="password" name="mod_chkpw" placeholder="비밀번호 확인">
		<input type="button" name="pwModBtn" value="변경하기">
	</form>
</div>


<div id="mypage_userinfo_container">
	<div id="mypage_userinfo_backimg">
		<img src="resources/img/test/background.jpg">
	</div>
	<div id="mypage_userinfo_img">
		<img src="resources/img/common/basic_profile.png" alt="기본프로필사진">
	</div>

	<form id="mypage_userinfo_content" action="" method="post">
		<div class="mypageuser_id">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
				<sec:authentication property="principal" var="userInfo" />
				<span>@${userInfo.user_id}</span>
			</sec:authorize>
		</div>

		<div class="mypageuser_nm">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
				<sec:authentication property="principal" var="userInfo" />
				<p>${userInfo.user_name}</p>
			</sec:authorize>
		</div>

		<div class="mypageuser_bio">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
				<sec:authentication property="principal" var="userInfo" />
				<span>${userInfo.user_bio}</span>
			</sec:authorize>
		</div>

		<div class="mypageuser_birth">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
				<sec:authentication property="principal" var="userInfo" />
				<span>${userInfo.user_birth}</span>
			</sec:authorize>
		</div>
		<div class="mypageuser_following">
			<a href="#"><span style="font-size: 15px;">0 Following</span></a> <a href="#"><span style="font-size: 15px;">0 Followers</span></a>
		</div>
	</form>

	<div id="mypage_userinfo_button">
		<button id="mypage_modcontent_btn" onclick="openCloseModal('#userMod_container', 'block')">프로필 수정</button>
		<br> <br>
		<button id="mypage_pw_btn" onclick="openCloseModal('#userPwMod_container', 'block')">비밀번호 변경</button>
	</div>
</div>

<div id="selectBox">
	<select name="myfeed">
		<option value="0" selected>내 피드</option>
		<option value="1">좋아요</i></option>
	</select>
</div>

<!-- content_detail -->