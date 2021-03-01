<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>purple</title>
    <link rel="stylesheet" href="resources/css/welcome/welcome.css">
    <link rel="icon" href="resources/favicon.ico" type="image/x-icon">
</head>
<body>
	<div id="form-wrap">
		<div id="logo">
      		<img src="resources/img/welcome/logo2.png" alt="로고">
      		<span>PURPLE</span>
    	</div>

    	<div id="button-wrap">
      		<div id="btn-wrap"></div>
      		<button type="button" class="togglebtn selectFixed" onclick="login()"><span>LOGIN</span></button>
      		<button type="button" class="togglebtn" onclick="register()"><span>REGISTER</span></button>
    	</div>

      	<form:form role="form" id="loginFrm" class="input-login" action="/login" method="post">
        	<input type="text" name="user_id" value="${cookie.user_check.value }" class="input-field" placeholder="아이디를 입력해주세요" required>
        	<input type="password" name="user_pw" class="input-field" placeholder="비밀번호를 입력해주세요" required>
        	<div class="checkbox">
                <label>
                    <form:checkbox path="rememberId"/>아이디 기억
                </label>
            </div>
        	<input type="submit" id="login_Btn" class="input_submit" value="로그인">
        	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
     	</form:form>
     	
     	<c:if test="${not empty loginFailMsg}">
     		<p id="errMSG">${requestScope.loginFailMsg}</p>
     	</c:if>

    	<form id="registerFrm" class="input-register">
      		<input type="text" name="user_id" class="input-field" maxlength="20" placeholder="아이디를 입력해주세요">
      		<input type="button" id="overlap_id" value="중복확인">
      		<span class="ChkFail">이미 존재하는 아이디입니다.</span>
      		<span class="ChkSuccess">사용가능한 아이디입니다.</span>

      		<input type="text" name="user_name" class="input-field" placeholder="성함을 입력해주세요">

      		<input type="password" name="user_pw" class="input-field" placeholder="비밀번호를 입력해주세요">
      		<input type="password" name="user_pw_check" class="input-field" placeholder="비밀번호 확인">
      	
      		<input type="email" name="user_email" class="input-field" placeholder="이메일을 입력해주세요">

      		<input type="date" name="user_birth" class="input-field" data-placeholder="생년월일을 선택해주세요">
      		<input type="button" id="join_btn" class="input_submit" value="회원가입">
    	</form>
  	</div>
      
     <script type="text/javascript" src="resources/js/welcome/welcome.js"></script>
</body>
</html>