<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="/resources/css/mypage/mypage.css" rel="stylesheet">
<script defer src="/resources/js/mypage/mypage.js"></script>
<script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="/resources/css/feed/feed.css">
<link rel="stylesheet" href="/resources/css/feed/feedDetail.css">
<script defer src="/resources/js/feed/feedDetail.js"></script>
<div id="modal_background"></div>
<!-- modal_background -->
<div id="userMod_container">
    <div id="userMod_top">
        <div id="close_userMod">
            <img alt="닫기" onclick="openCloseModal('#userMod_container', 'none')" src="/resources/img/common/close_icon.png">
        </div>
        <span>Edit Profile</span>
        <div id="save_userMod">
            <button id="save_userModBtn" type="button">Save</button>
        </div>
    </div>
    <div id="userMod_content">
        <div id="mod_userbackground">
            <label for="mod_background">
                <img onerror="this.style.display='none'" src="">
            </label>
            <input accept="image/jpeg, image/jpg, image/png" id="mod_background" type="file"/>
        </div>
        <div id="mod_userimg">
            <label for="mod_img">
                <img src="/resources/img/common/basic_profile.png"/>
            </label>
            <input accept="image/jpeg, image/jpg, image/png" id="mod_img" type="file"/>
        </div>
        <form id="userModFrm">
            <input maxlength="10" name="mod_id" placeholder="Id" type="text">
            <button name="id_check" type="button">중복확인</button>
            <input maxlength="5" name="mod_name" placeholder="Name" type="text">
            <input maxlength="20" name="mod_bio" placeholder="Bio" type="text">
            <input maxlength="30" name="mod_location" placeholder="Location" type="text">
            <input name="find_Address" type="button" onclick="postCode()" value="주소 찾기">
            <input maxlength="30" name="mod_email" placeholder="Email" type="text">
            <input data-placeholder="생년월일을 선택해주세요" name="mod_birth" type="date">
        </form>
    </div>
</div>

<div id="userPwMod_container">
    <div id="userPwMod_top">
        <span>비밀번호 변경</span>
        <div id="close_userPwMod">
            <img alt="닫기" onclick="openCloseModal('#userPwMod_container', 'none')" src="/resources/img/common/close_icon.png">
        </div>
    </div>
    <form name="userPwModFrm">
        <input name="user_pw" placeholder="현재 비밀번호" type="password">
        <input name="mod_pw" placeholder="변경 할 비밀번호" type="password">
        <input name="mod_chkpw" placeholder="비밀번호 확인" type="password">
        <button name="pwModBtn" type="button">변경하기</button>
    </form>
</div>
<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
    <sec:authentication property="principal" var="userInfo"/>
    <div id="mypage_userinfo_container">
        <div id="mypage_userinfo_backimg">
            <img onerror="this.style.display='none'" src="${userInfo.user_backgroundimg}">
        </div>
        <div id="mypage_userinfo_img">
            <c:if test="${not empty userInfo.user_profileimg}">
                <img src=${userInfo.user_profileimg} onerror="this.src='/resources/img/common/basic_profile.png'" alt="기본프로필사진">
            </c:if>
            <c:if test="${empty userInfo.user_profileimg}">
                <img alt="기본프로필사진" src="/resources/img/common/basic_profile.png">
            </c:if>
        </div>
        <div id="mypage_userinfo_content">
            <div class="mypageuser_id">
                <span>${userInfo.user_id}</span>
            </div>
            <div class="mypageuser_nm">
                <p>${userInfo.user_name}</p>
            </div>
            <div class="mypageuser_birth">
                <span>${userInfo.user_birth}</span>
            </div>
            <div class="mypageuser_location">
                <span>${userInfo.user_location}</span>
            </div>
            <div class="mypageuser_email">
                <span>${userInfo.user_email}</span>
            </div>
            <div class="mypageuser_bio">
                <span>${userInfo.user_bio}</span>
            </div>
        </div>
        <div id="mypage_userinfo_button">
            <button id="mypage_modcontent_btn" onclick="openCloseModal('#userMod_container', 'block')">프로필 수정</button>
         
            <button id="mypage_pw_btn" onclick="openCloseModal('#userPwMod_container', 'block')">비밀번호 변경</button>
        </div>
    </div>
</sec:authorize>
<div id="feed">

</div>
<div class="feed_overlay">
    <div class="feedDetail">
             
    </div> 
 </div>