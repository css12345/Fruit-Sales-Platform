<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>货物管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/homeView.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pageDivide.js"></script>
<script type="text/javascript">
	function editCommodities(id) {
		var message = "{'id':'" + id + "'}";
		$.ajax({
			type : 'post',
			url : '/Fruit-Sales-Platform/commodities/editCommodities.action',
			contentType : 'application/json;charset=utf-8',
			data : message,
			success : function(data) {
				$("#editName").val(data["name"]);
				$("#editPrice").val(data["price"]);
				$("#editLocality").val(data["locality"]);
				$("#fruitId").val(data["fruitId"]);

				$(".mask").css("display", "block");

				$("#eStartPage").val($("#startPage").val());
				$("#eCurrentPage").val($("#currentPage").val());
				$("#ePageSize").val($("#pageSize").val());
			}
		});
	}
	
	function cancelEdit() {
		$(".mask").css("display","none");
	}
	
	function deleteCommodities(id,name) {
		var con = confirm("你确认删除商品" + name + "吗？");
		if(con) {
			var message = "{'fruitId':'" + id + "'}";
			$.ajax({
				type : 'post',
				url : '/Fruit-Sales-Platform/contract/getCommodityAllContract.action',
				contentType : 'application/json;charset=utf-8',
				data : message,
				success : function(data) {
					if(data == "") {
						$("#dFruitId").val(id);
						$("#dStartPage").val($("#startPage").val());
						$("#dCurrentPage").val($("#currentPage").val());
						$("#dPageSize").val($("#pageSize").val());
						$("#deleteForm").submit();
					}
					else {
						alert("商品" + name + "还在订单中，请先删除订单！");
					}
				}
			});
		}
	}
	
	function showAddMask(flag) {
		if(flag == "true")
			$(".addMask").css("display","block");
		else 
			$(".addMask").css("display","none");
	}
	
	function checkUpdateCommodities() {
		if($("#editName").val() == null || $("#editName").val() == "") {
			alert("商品名不能为空！");
			return false;
		}
		if($("#editPrice").val() == null || $("#editPrice").val() == "") {
			alert("价格不能为空！");
			return false;
		}
		
		if($("#editLocality").val() == null || $("#editLocality").val() == "") {
			alert("产地不能为空！");
			return false;
		}
		return true;
	}
	
	function checkAddCommodities() {
		if($("#addName").val() == null || $("#addName").val() == "") {
			alert("商品名不能为空！");
			return false;
		}
		if($("#addPrice").val() == null || $("#addPrice").val() == "") {
			alert("价格不能为空！");
			return false;
		}
		
		if($("#addLocality").val() == null || $("#addLocality").val() == "") {
			alert("产地不能为空！");
			return false;
		}
		
		$("#aCurrentPage").val($("#currentPage").val());
		$("#aStartPage").val($("#startPage").val());
		$("#aPageSize").val($("#pageSize").val());
		return true;
	}
	
	function openwin(fruitId) {
		var url = "${pageContext.request.contextPath}/accessory/list.action?fruitId=" + fruitId;
		window.open(url,"附属品","height=400,width=700,scrollbars=yes");
	}
</script>
</head>
<body onload="init()">
	<%@ include file="../menu.jsp"%><br />
	<button onclick="showAddMask('true')">
		添加
	</button>
	<form id="listForm" action="list.action" method="post" onsubmit="resetPages()">
		名称：<input type="text" name="name" value="${commodities.name }" class="width1" /> 
		产地：<input type="text" name="locality" value="${commodities.locality }" class="width1" /> 
		价格：<input type="number" id="price1" name="startPrice" min="0.0" step="0.1" value="${startPrice }" />
		-<input type="number" id="price2" name="endPrice" min="0.0" step="0.1" value="${endPrice }" /><br /><br />
		创建日期：<input type="datetime-local" name="startTime" value="${startTime }" />
				-<input type="datetime-local" name="endTime" value="${endTime }" />
		<input type="submit" value="搜索" /><br />
		<c:if test="${errorMsg }">
			<font color="red" class="font1">${errorMsg }</font><br />
		</c:if>
		<input type="hidden" name="startPage" id="startPage" value="${startPage }" />
		<input type="hidden" name="currentPage" id="currentPage" value="${currentPage }" /> 
		<input type="hidden" name="pageSize" id="pageSize" value="${pageSize }" /> 
		<input type="hidden" name="sumPageNumber" id="sumPageNumber" value="${sumPageNumber }" /> 
		<input type="hidden" name="countNumber" id="countNumber" value="${countNumber }" />
	</form>
	<hr class="top" />
	<c:if test="${list != null }">
		<table border=1>
			<tr>
				<td>序号</td>
				<td>姓名</td>
				<td>价格</td>
				<td>产地</td>
				<td>创建日期</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${status.index + 1 }</td>
					<td>${item.name }</td>
					<td>${item.price }</td>
					<td>${item.locality }</td>
					<td>${item.createTime }</td>
					<td>
						<a onclick="editCommodities('${item.fruitId}')">编辑</a>|
						<a onclick="deleteCommodities('${item.fruitId}','${item.name }')">删除</a>|
						<a onclick="openwin('${item.fruitId}')">附属品</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${list == null }">
		<b>搜索结果为空！</b>
	</c:if>

	<div class="top">
		<a onclick="toPrePage()">上一页</a><a onclick="toNextPage()">下一页</a> <input
			type="text" id="pageNumber" class="width2">
		<button onclick="toLocationPage()">go</button>
		<div id="pageInfo"></div>
	</div>

	<div class="mask">
		<div class="c">
			<div class="change">
				修改信息<font onclick="cancelEdit()" class="font1">x</font>
			</div>
			<form action="edit.action" id="editForm" method="post" onsubmit="return checkUpdateCommodities()">
				姓名：<input type="text" id="editName" name="name" class="width1" /><br />
				价格：<input type="number" min="0.0" step="0.1" id="editPrice" name="price" /><br />
				产地：<input type="text" id="editLocality" name="locality" class="width1" /><br />
				<input type="hidden" name="fruitId" id="fruitId">
				<input type="hidden" name="startPage" id="eStartPage">
				<input type="hidden" name="currentPage" id="eCurrentPage">
				<input type="hidden" name="pageSize" id="ePageSize">
				<input type="submit" value="提交">
			</form>
		</div>
	</div>
	
	<form action="delete.action" id="deleteForm" method="post">
		<input type="hidden" name="fruitId" id="dFruitId">
		<input type="hidden" name="startPage" id="dStartPage">
		<input type="hidden" name="currentPage" id="dCurrentPage">
		<input type="hidden" name="pageSize" id="dPageSize">
	</form>
	
	<div class="addMask">
		<div class="c">
			<div class="change">
				添加信息<font onclick="showAddMask('false')" class="font1">x</font>
			</div>
			<form action="add.action" id="addForm" method="post" onsubmit="return checkAddCommodities()">
				姓名：<input type="text" id="addName" name="name" class="width1" /><br />
				价格：<input type="number" min="0.0" step="0.1" id="addPrice" name="price" class="width1" /><br />
				地址：<input type="text" id="addLocality" name="locality" class="width1" /><br />
				<input type="hidden" name="startPage" id="aStartPage">
				<input type="hidden" name="currentPage" id="aCurrentPage">
				<input type="hidden" name="pageSize" id="aPageSize">
				<input type="submit" value="添加" >
			</form>
		</div>
	</div>
</body>
</html>