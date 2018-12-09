<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>购销合同管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pageDivide.js"></script>
<script type="text/javascript">
	function changeType() {
		$("#type").val($("#indexType").val());
	}
	function addContract() {
		var url = "${pageContext.request.contextPath}/contract/toAddPage.action";
		window.open(url,"创建合同","height=700,width=700,scrollbars=yes");
	}
	function getContractDetail(id) {
		var url = "${pageContext.request.contextPath}/contract/getContractDetail.action?contractId=" + id;
		window.open(url,"合同详情","hegith=700,width=700,scrollbars=yes");
	}
	function deleteContract(id,barCode) {
		if(window.confirm("你确定要删除编号为" + barCode + "的合同信息吗？")) {
			$("#dContractId").val(id);
			$("#dStartPage").val($("#startPage").val());
			$("#dCurrentPage").val($("#currentPage").val());
			$("#dPageSize").val($("#pageSize").val());
			$("#deleteForm").submit();
		}
	}
	function editContract(id) {
		var url = "${pageContext.request.contextPath}/contract/toEditPage.action?contractId=" + id;
		window.open(url,"编辑合同","height=700,width=700,scrollbars=yes");
	}
</script>
<link type="text/css"
	href="${pageContext.request.contextPath }/css/homeView.css"
	rel="stylesheet">
</head>
<body onload="init()">
	<%@include file="../menu.jsp"%><br />
	<button onclick="addContract()">添加</button>
	<form id="listForm" action="list.action" method="post" onsubmit="resetPages()">
		合同号：<input type="text" name="barCode" class="width1" value="${contractVo.barCode }"> 
		零售商：<input type="text" name="retailerName" class="width1" value="${contractVo.retailerName }">
		类型：<select id="indexType" onchange="changeType()">
			<c:choose>
				<c:when test="${contractVo.type == -1 }">
					<option value="-1" selected="selected">全部</option>
				</c:when>
				<c:otherwise>
					<option value="-1">全部</option>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${contractVo.type == 1 }">
					<option value="1" selected="selected">省外</option>
				</c:when>
				<c:otherwise>
					<option value="1">省外</option>
				</c:otherwise>
			</c:choose>

			<c:choose>
				<c:when test="${contractVo.type == 0 }">
					<option value="0" selected="selected">省内</option>
				</c:when>
				<c:otherwise>
					<option value="0">省内</option>
				</c:otherwise>
			</c:choose>
		</select> 
		<input type="hidden" name="type" id="type" value="${contractVo.type }">
		创建日期：<input type="datetime-local" name="startTime" value="${startTime }"> 
				-<input type="datetime-local" name="endTime" value="${endTime }">
		<input type="submit" value="搜索"><br />
		<c:if test="${errorMsg }">
			<font color="red" class="font1">${errorMsg }</font>
		</c:if>
		<input type="hidden" name="startPage" id="startPage" value="${startPage }"> 
		<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }">
		<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }">
		<input type="hidden" name="sumPageNumber" id="sumPageNumber" value="${sumPageNumber }">
		<input type="hidden" name="countNumber" id="countNumber" value="${countNumber }">
	</form>
	<hr class="top">
	<c:if test="${list != null }">
		<table border=1>
			<tr>
				<td>序号</td>
				<td>合同编号</td>
				<td>零售商</td>
				<td>类型</td>
				<td>创建日期</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${status.index + 1 }</td>
					<td><a href="#" onclick="getContractDetail('${item.contractId}')">${item.barCode }</a></td>
					<td>${item.retailerName }</td>
					<td>
						<c:if test="${item.type == 1 }">
							<font color="blue" class="font1">省外</font>
						</c:if>
						<c:if test="${item.type == 0 }">
							<font color="green" class="font1">省内</font>
						</c:if>
					</td>
					<td>${item.createTime }</td>
					<td>
						<a onclick="editContract('${item.contractId}')">编辑</a>|
						<a onclick="deleteContract('${item.contractId}','${item.barCode }')">删除</a>
					</td>
			</c:forEach>
		</table>
	</c:if>
	
	<form action="delete.action" id="deleteForm" method="post">
		<input type="hidden" name="contractId" id="dContractId">
		<input type="hidden" name="startPage" id="dStartPage">
		<input type="hidden" name="currentPage" id="dCurrentPage">
		<input type="hidden" name="pageSize" id="dPageSize">
	</form>
	
	<c:if test="${list == null }">
		<b>搜索结果为空！</b>
	</c:if>
	
	<div class="top">
		<a onclick="toPrePage()">上一页</a><a onclick="toNextPage()">下一页</a>
		<input type="text" id="pageNumber" class="width2">
		<button onclick="toLocationPage()">go</button>
		<div id="pageInfo"></div>
	</div>
</body>
</html>