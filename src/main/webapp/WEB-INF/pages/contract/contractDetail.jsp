<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>合同详情</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/contractDetail.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	function printPage() {
		$("#p").css("display","none");
		window.print();
		$("#p").css("display","block");
	}
</script>
</head>
<body>
	<button id="p" onclick="printPage()">打印</button>
	<h2>购销合同</h2>
	<div class="createTime">创建日期：${contract.createTime }</div>
	合同编码：<b class="barCode">${contract.barCode }</b><br />
	类型：<c:if test="${contract.type == 0 }">省内</c:if>
	<c:if test="${contract.type == 1 }">省外</c:if>
	<div class="info">
		零售商信息：<br />
		姓名：${contract.retailer.name }<br />
		联系电话：${contract.retailer.telephone }<br />
		送货地址：${contract.retailer.address }<br />
	</div><hr>
	
	<div class="info">
		<c:if test="${contract.commoditiesList != null }">
			<table class="commoditiesInformation" border=1>
				<tr>
					<td>名称</td>
					<td>价格</td>
					<td>产地</td>
					<td>附属品</td>
					<td>数量</td>
				</tr>
				<c:forEach items="${contract.commoditiesList }" var="item">
					<tr>
						<td>${item.name }</td>
						<td>${item.price }元/斤</td>
						<td>${item.locality }</td>
						<td>
							<c:if test="${item.accessoryList != null }">
								<c:forEach items="${item.accessoryList }" var="accessoryItem">
									${accessoryItem.name }:${accessoryItem.price }元/斤<br />
								</c:forEach>
							</c:if>
							<c:if test="${item.accessoryList == null || item.accessoryList.size() == 0 }">
								无
							</c:if>
						</td>
						<td>${item.number }斤</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
</body>
</html>