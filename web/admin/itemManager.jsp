<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function itemManagerDelete(itemManagerId) {
		if(confirm("您确定要删除这个项目管理员吗？")) {
			window.location="itemManager?action=delete&itemManagerId="+itemManagerId;
		}
	}
	
	$(document).ready(function(){
		$("ul li:eq(1)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			项目管理员管理
		</div>
		<form name="myForm" class="form-search" method="post" action="itemManager?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='itemManager?action=preSave'">添加</button>
				<span class="data_search">
					<select id="searchType" name="searchType" style="width: 80px;">
					<option value="name">姓名</option>
					<option value="userName" ${searchType eq "userName"?'selected':'' }>用户名</option>
					</select>
					&nbsp;<input id="s_itemManagerText" name="s_itemManagerText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_itemManagerText }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>编号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>电话</th>
					<th>项目类别</th>
					<th>用户名</th>
					<th>操作</th>
				</tr>
				<c:forEach varStatus="i" var="itemManager" items="${itemManagerList }">
					<tr>
						<td>${i.count+(page-1)*pageSize }</td>
						<td>${itemManager.name }</td>
						<td>${itemManager.sex }</td>
						<td>${itemManager.tel }</td>
						<td>${itemManager.itemTypeName==null?"无":itemManager.itemTypeName }</td>
						<td>${itemManager.userName }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='itemManager?action=preSave&itemManagerId=${itemManager.itemManagerId }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="itemManagerDelete(${itemManager.itemManagerId})">删除</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
		<div class="pagination pagination-centered">
			<ul>
				${pageCode }
			</ul>
		</div>
</div>