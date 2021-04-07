<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>purple</title>
 	<link rel="icon" href="resources/img/common/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="resources/css/common/welcome.css">
	<script defer type="text/javascript" src="resources/js/common/welcome.js"></script>
	<script defer type="text/javascript" src="resources/js/common/utils.js"></script>
</head>
<body>
	<div id="form-wrap">
		<div id="logo">
      		<img src="resources/img/common/logo2.png" alt="로고">
      		<span>PURPLE</span>
    	</div>

    	<div id="button-wrap">
      		<div id="btn-wrap"></div>
      		<button type="button" class="togglebtn selectFixed" onclick="login()"><span>LOGIN</span></button>
      		<button type="button" class="togglebtn" onclick="register()"><span>REGISTER</span></button>
    	</div>

      	<form id="loginFrm" class="input-login" action="/login" method="post">
        	<input type="text" value="${cookie.RememberId.value}" name="user_id" class="input-field" placeholder="아이디를 입력해주세요" required>
        	<input type="password" name="user_pw" class="input-field" placeholder="비밀번호를 입력해주세요" required>
        	
        	<c:choose>
        		<c:when test="${cookie.RememberId.value == null}">
        			<input type="checkbox" id="remember_us" name="remember_userId">      			
        		</c:when>
        		<c:otherwise>
        			<input type="checkbox" id="remember_us" name="remember_userId" checked>
        		</c:otherwise>
        	</c:choose>
        	<span>아이디 기억하기</span>  	

        	<input type="submit" id="login_Btn" class="input_submit" value="로그인">
        	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<div><a href='/oauth2/authorization/google'>google</a></div>
			<div><a href='/oauth2/authorization/facebook'>facebook</a></div>
			<div><a href='/oauth2/authorization/naver'>naver</a></div>
			<div><a href='/oauth2/authorization/kakao'>kakao</a></div>
     	</form>

		<!-- 비밀번호 찾기 -->
		<div id="findpw">
			<span class="findpw_btn">비밀번호 찾기</span>
			
			<div class="findpw_modal">
				<div class="findpw_use">
					<h2>비밀번호 찾기</h2>
					<span>
						<input class="findpw_id" type="text" name="user_id" placeholder="회원가입한 아이디를 입력해주세요">
					</span>
					<span>
						<input class="findpw_email" type="email" name="user_email" placeholder="회원가입한 이메일 주소를 입력해주세요">
					</span>
					<span>
						<input class="find_btn" type="button" value="찾기">
					</span>
					<span class="findpw_quit">닫기</span>
				</div>
			</div>
		</div>
		
		<c:if test="${not empty loginFailMsg}">
           <p id="errMSG">${requestScope.loginFailMsg}</p>
        </c:if>

    	<form id="registerFrm" class="input-register">
      		<input type="text" name="user_id" class="input-field" maxlength="20" placeholder="아이디를 입력해주세요" required>
      		<input type="button" id="overlap_id" value="중복확인">
      		<span class="ChkFail">이미 존재하는 아이디입니다.</span>
      		<span class="ChkSuccess">사용가능한 아이디입니다.</span>

      		<input type="text" name="user_name" class="input-field" placeholder="이름을 입력해주세요" required>

      		<input type="password" name="user_pw" class="input-field" placeholder="비밀번호를 입력해주세요" required>
      		<input type="password" name="user_pw_check" class="input-field" placeholder="비밀번호 확인" required>
      	
      		<input type="email" name="user_email" class="input-field" placeholder="이메일을 입력해주세요" required>

      		<input type="date" name="user_birth" class="input-field" data-placeholder="생년월일을 선택해주세요" required>
      		<input type="button" id="join_btn" class="input_submit" value="회원가입">
    	</form>
  	</div>
</body>
</html>