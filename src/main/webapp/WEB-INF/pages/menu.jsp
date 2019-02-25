<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>导航栏</title>
</head>
<body>
	<div id="menuContent"
		style="background-color: #173e65; color: #ffffff; height: 100px;">
		<h1 style="margin-left: 10px; margin-top: 10px;">水果网络销售平台</h1>
		<div style="margin-left: 10px;">
			<a href="${pageContext.request.contextPath }/commodities/list.action">货物管理</a>| 
			<a href="${pageContext.request.contextPath }/retailer/list.action?status=-1">零售商管理</a>|
			<a href="${pageContext.request.contextPath }/contract/list.action?type=-1">购销合同</a>| 
			<a href="${pageContext.request.contextPath }/user/setting.action">用户设置</a>
			<a href="${pageContext.request.contextPath }/user/logout.action" style="float: right;margin-right: 10px;">
				退出
			</a>
		</div>
	</div>
	<div style="background-color: #cccccc">
		<span style="margin-left: 10px;">欢迎您，${sessionScope.user.name }</span>
	</div>
</body>
</html>