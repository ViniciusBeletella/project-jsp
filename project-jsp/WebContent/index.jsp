<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to VNSComp</title>
<link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form action="LoginCheck" method="post" class="login-form">
			Login: <input type="text" id="uname" name="uname"> <br/>
			Password: <input type="password" id="password" name="password"><br />
			<button type="submit" value="login">Login</button>
			</form>
		</div>
	</div>
</body>
</html>