<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>用户设置</title>
<style>
* {
	margin: 0;
	padding: 0;
}

#menuContent a {
	text-decoration: none;
	color: #ffffff
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	function checkOther() {
		if (document.getElementById("userName").value == "") {
			alert("用户名不能为空");
			return false;
		} 
		else if (document.getElementById("name").value == "") {
			alert("姓名不能为空");
			return false;
		} 
		else if (document.getElementById("telephone").value == ""
			|| !(/^1[34578]\d{9}$/.test(document
					.getElementById("telephone").value))) {
			alert("手机号格式有误");
			return false;
		}
		return true;
	}
	
	function checkPassword() {
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var confirmPassword = $("#confirmPassword").val();
		if(oldPassword == "") {
			alert("请输入原密码！");
			return false;
		}
		if(newPassword == "") {
			alert("请输入新密码！");
			return false;
		}
		if(confirmPassword == "") {
			alert("请输入确认密码！");
			return false;
		}
		if(oldPassword == newPassword) {
			alert("原密码与新密码不能相同！");
			return false;
		}
		if(newPassword != confirmPassword) {
			alert("两次新密码输入不一致！");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<%@ include file="menu.jsp" %>
	<form action="updateOther.action" method="post" onsubmit="return checkOther()">
		用户名：<input id="userName" name="userName" type="text" value="${user.userName }"><br/>
		姓名：<input id="name" name="name" type="text" value="${user.name }"><br/>
		电话：<input id="telephone" name="telephone" type="text" value="${user.telephone }"><br/>
		<input type="submit" value="修改">
	</form>
	<br/><br/><br/>
	<hr/>
	<form action="updatePassword.action" method="post" onsubmit="return checkPassword()">
		原密码：<input id="oldPassword" name="oldPassword" type="password"><br/>
		新密码：<input id="newPassword" name="newPassword" type="password"><br/>
		确认密码：<input id="confirmPassword" name="confirmPassword" type="password"><br/>
		<input type="submit" value="修改">
	</form>
	<br/><br/><br/>
	<font color="red">${updateMessage }</font>
</body>
</html>