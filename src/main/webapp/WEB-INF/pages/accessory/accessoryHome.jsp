<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>附属品管理</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/homeView.css"> 
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	function checkAll(obj) {
		var isCheck = obj.checked;
		var checkList = document.getElementsByName("arrays");
		for(var i = 0;i < checkList.length;i++)
			checkList[i].checked = isCheck;
	}
	
	function showAddAccessory(flag) {
		if(flag == "true")
			$(".addAccessoryMask").css("display","block");
		else 
			$(".addAccessoryMask").css("display","none");
	}
	
	function deleteAccessory() {
		var myArray = new Array();
		var len = 0;
		var fruitId = document.getElementById("aFruitId").value;
		var arrays = document.getElementsByName("arrays");
		for(var i = 0;i < arrays.length;i++)
			if(arrays[i].checked)
				myArray[len++] = arrays[i].value;
		if (len < 1) 
			alert("请选择至少一项附属品！");
		else{
			$.ajax({
				type:'post',
				url:'${pageContext.request.contextPath}/accessory/deleteList.action',
				data:{"arrays":myArray,"fruitId":fruitId},
				traditional:true,
				success:function(data) {
					alert("删除成功！");
					//location.href="${pageContext.request.contextPath}/accessory/list.action?fruitId=" + fruitId;
					location.replace("${pageContext.request.contextPath}/accessory/list.action?fruitId=" + fruitId);
				}
			});
		}
	}
	
	function checkAddAccessory() {
		if($("#addAccessoryName").val() == null || $("#addAccessoryName").val() == "") {
			alert("附属名不能为空！");
			return false;
		}
		if($("#addAccessoryPrice").val() == null || $("#addAccessoryPrice").val() == "") {
			alert("价格不能为空！");
			return false;
		}
		if($("#addAccessoryPrice").val() <= 0) {
			alert("价格必须大于0！");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<button onclick="showAddAccessory('true')">
		添加
	</button>
	<button onclick="deleteAccessory()">
		删除
	</button>
	<c:if test="${list != null }">
		<table border=1>
			<tr>
				<td><input type="checkbox" onclick="checkAll(this)"></td>
				<td>名称</td>
				<td>价格</td>
				<td>创建日期</td>
			</tr>
			
			<c:forEach items="${list }" var="item" varStatus="status">
				<tr>
					<td><input type="checkbox" name="arrays" value="${item.accessoryId }" ></td>
					<td>${item.name }</td>
					<td>${item.price }</td>
					<td>${item.createTime }</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${list == null }">
		<b>结果为空！</b>
	</c:if>
	
	<div class="addAccessoryMask">
		<div class="c">
			<div class="change">
				<font class="font1" onclick="showAddAccessory('false')">x</font>
			</div>
			
			<form id="addAccessory" action="add.action" method="post" onsubmit="return checkAddAccessory()">
				名称：<input type="text" id="addAccessoryName" name="name" class="width1"><br/>
				价格：<input type="text" id="addAccessoryPrice" name="price" class="width1">
				<input type="hidden" id="aFruitId" name="fruitId" value="${fruitId }">
				<input type="submit" value="添加">
			</form>
		</div>
	</div>
</body>
</html>