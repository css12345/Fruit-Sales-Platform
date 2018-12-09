<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>主页</title>
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
</head>
<body>
	<%@ include file="menu.jsp" %>
	<p>${bindMessage }</p>
</body>
</html>