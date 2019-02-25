<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>零售商管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/homeView.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/pageDivide.js"></script>
<script type="text/javascript">
	function changeStatus() {
		var status = document.getElementById("indexStatus").value;
		document.getElementById("status").value = status;
	}
	
	function editRetailer(id) {
		var message = "{'id':'" + id + "'}";
		$.ajax({
			type : 'post',
			url : '/Fruit-Sales-Platform/retailer/editRetailer.action',
			contentType : 'application/json;charset=utf-8',
			data : message,
			success : function(data) {
				$("#editName").val(data["name"]);
				$("#editTelephone").val(data["telephone"]);
				$("#editAddress").val(data["address"]);
				$("#retailerId").val(data["retailerId"]);
				$("#editStatus").val(data["status"]);
				$("#eStatus").val(data["status"]);

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
	
	function changeEditStatus() {
		//alert("状态变化");
		var status = document.getElementById("eStatus").value;
		//alert(status);
		document.getElementById("editStatus").value = status;
	}
	
	function deleteRetailer(id,name) {
		var con = confirm("你确认删除用户" + name + "吗？");
		if(con) {
			var message = "{'retailerId':'" + id + "'}";
			$.ajax({
				type : 'post',
				url : '/Fruit-Sales-Platform/contract/getRetailerAllContract.action',
				contentType : 'application/json;charset=utf-8',
				data : message,
				success : function(data) {
					if(data == "") {
						$("#dRetailerId").val(id);
						$("#dStartPage").val($("#startPage").val());
						$("#dCurrentPage").val($("#currentPage").val());
						$("#dPageSize").val($("#pageSize").val());
						$("#deleteForm").submit();
					}
					else {
						alert("用户" + name + "还有订单未删除，请先删除订单！");
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
	
	function checkAddRetailer() {
		if($("#addName").val() == null || $("#addName").val() == "") {
			alert("用户名不能为空！");
			return false;
		}
		if($("#addTelephone").val() == null || $("#addTelephone").val() == "") {
			alert("手机号不能为空！");
			return false;
		}
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		if(!myreg.test($("#addTelephone").val())) {
			alert("请输入有效的手机号码！");
			return false;
		}
		
		if($("#addAddress").val() == null || $("#addAddress").val() == "") {
			alert("地址不能为空！");
			return false;
		}
		$("#aCurrentPage").val($("#currentPage").val());
		$("#aStartPage").val($("#startPage").val());
		$("#aPageSize").val($("#pageSize").val());
		return true;
	}
	
	function checkUpdateRetailer() {
		if($("#editName").val() == null || $("#editName").val() == "") {
			alert("用户名不能为空！");
			return false;
		}
		if($("#editTelephone").val() == null || $("#editTelephone").val() == "") {
			alert("手机号不能为空！");
			return false;
		}
		var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		if(!myreg.test($("#editTelephone").val())) {
			alert("请输入有效的手机号码！");
			return false;
		}
		
		if($("#editAddress").val() == null || $("#editAddress").val() == "") {
			alert("地址不能为空！");
			return false;
		}
		return true;
	}
</script>
</head>
<body onload="init()">
	<%@ include file="../menu.jsp"%><br />
	<button onclick="showAddMask('true')">
		添加
	</button>
	<form id="listForm" action="list.action" method="post" onsubmit="resetPages()">
		姓名：<input type="text" name="name" value="${retailer.name }" class="width1" /> 
		手机：<input type="text" name="telephone" value="${retailer.telephone }" class="width1" /> 
		地址：<input type="text" name="address" value="${retailer.address }" class="width1" /><br /> <br />
		状态：<select id="indexStatus" onchange="changeStatus()">
			<c:choose>
				<c:when test="${retailer.status == -1 }">
					<option value="-1" selected="selected">全部</option>
				</c:when>
				<c:otherwise>
					<option value="-1">全部</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${retailer.status == 1 }">
					<option value="1" selected="selected">启用</option>
				</c:when>
				<c:otherwise>
					<option value="1">启用</option>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${retailer.status == 0 }">
					<option value="0" selected="selected">停用</option>
				</c:when>
				<c:otherwise>
					<option value="0">停用</option>
				</c:otherwise>
			</c:choose>
		</select> 
		<input type="hidden" name="status" id="status" value="${retailer.status }">
		创建日期：<input type="datetime-local" name="createTime" value="${retailer.createTime }"/><br /><br />
		开始日期：<input type="datetime-local" name="startTime" value="${startTime }"/>
		结束日期：<input type="datetime-local" name="endTime" value="${endTime }" />
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
				<td>手机号</td>
				<td>地址</td>
				<td>状态</td>
				<td>创建日期</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td>${status.index + 1 }</td>
					<td>${item.name }</td>
					<td>${item.telephone }</td>
					<td>${item.address }</td>
					<td>
						<c:if test="${item.status == 1 }">
							<font color="blue" class="font1">启用</font>
						</c:if> 
						<c:if test="${item.status == 0 }">
							<font color="red" class="font1">停用</font>
						</c:if>
					</td>
					<td>${item.createTime }</td>
					<td>
						<a onclick="editRetailer('${item.retailerId}')">编辑</a>|
						<a onclick="deleteRetailer('${item.retailerId}','${item.name }')">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${list == null }">
		<b>搜索结果为空！</b>
	</c:if>

	<div class="top">
		<a onclick="toPrePage()">上一页</a><a onclick="toNextPage()">下一页</a>
		<input type="text" id="pageNumber" class="width2">
		<button onclick="toLocationPage()">go</button>
		<div id="pageInfo"></div>
	</div>

	<div class="mask">
		<div class="c">
			<div class="change">
				修改信息<font onclick="cancelEdit()" class="font1">x</font>
			</div>
			<form action="edit.action" id="editForm" method="post" onsubmit="return checkUpdateRetailer()">
				姓名：<input type="text" id="editName" name="name" class="width1" /><br />
				手机：<input type="text" id="editTelephone" name="telephone" class="width1" /><br />
				地址：<input type="text" id="editAddress" name="address" class="width1" /><br />
				状态：<select id="eStatus" onchange="changeEditStatus()">
					<option value="1">启用</option>
					<option value="0">停用</option>
				</select><br />
				<input type="hidden" name="retailerId" id="retailerId">
				<input type="hidden" name="status" id="editStatus">
				<input type="hidden" name="startPage" id="eStartPage">
				<input type="hidden" name="currentPage" id="eCurrentPage">
				<input type="hidden" name="pageSize" id="ePageSize">
				<input type="submit" value="提交" >
			</form>
		</div>
	</div>
	
	<form action="delete.action" id="deleteForm" method="post">
		<input type="hidden" name="retailerId" id="dRetailerId">
		<input type="hidden" name="startPage" id="dStartPage">
		<input type="hidden" name="currentPage" id="dCurrentPage">
		<input type="hidden" name="pageSize" id="dPageSize">
	</form>
	
	<div class="addMask">
		<div class="c">
			<div class="change">
				添加信息<font onclick="showAddMask('false')" class="font1">x</font>
			</div>
			<form action="add.action" id="addForm" method="post" onsubmit="return checkAddRetailer()">
				姓名：<input type="text" id="addName" name="name" class="width1" /><br />
				手机：<input type="text" id="addTelephone" name="telephone" class="width1" /><br />
				地址：<input type="text" id="addAddress" name="address" class="width1" /><br />
				<input type="hidden" name="status" value="1">
				<input type="hidden" name="startPage" id="aStartPage">
				<input type="hidden" name="currentPage" id="aCurrentPage">
				<input type="hidden" name="pageSize" id="aPageSize">
				<input type="submit" value="添加">
			</form>
		</div>
	</div>
</body>
</html>