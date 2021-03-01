<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자로그인</title>
<link rel="icon" href="resources/static/img/common/favicon.ico" type="image/x-icon" />
</head>
<body>
	<form action="/admin/home" method="post">
		<input type="text" name="admin_id" placeholder="관리자아이디를 입력해주세요" required><br/>
		<input type="password" name="admin_pw" placeholder="관리자비밀번호를 입력해주세요" required>
		<input type="submit" value="로그인">
	</form>
</body>
</html>