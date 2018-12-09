<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>绑定用户</title>
<script type="text/javascript">
	function checkUserInput() {
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		if(userName == null || userName == "") {
			alert("用户名不能为空！");
			return false;
		}
		if(password == null || password == "") {
			alert("密码不能为空！");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<p>
		首次用QQ登录需要进行账号绑定，请输入相关信息进行绑定！
	</p>
	<form action="bindUser.action" method="post" onsubmit="return checkUserInput()">
		请输入用户名：<input type="text" name="userName" id="userName"><br />
		请输入密码：<input type="password" name="password" id="password"><br />
		<input type="submit" value="确定">
	</form>
</body>
</html>