<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function subprojectDelete(subprojectId) {
		if(confirm("您确定要删除这个任务吗？")) {
			window.location="subproject?action=delete&subprojectId="+subprojectId;
		}
	}
</script>
<div class="data_list">
		<div class="data_list_title">
			任务管理
		</div>
		<form name="myForm" class="form-search" method="post" action="subproject?action=search">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='subproject?action=preSave'">添加</button>
				<span class="data_search">
					<select id="buildToSelect" name="buildToSelect" style="width: 110px;">
					<option value="">全部项目类型</option>
					<c:forEach var="itemType" items="${itemTypeList }">
						<option value="${itemType.itemTypeId }" ${buildToSelect==itemType.itemTypeId?'selected':'' }>${itemType.itemTypeName }</option>
					</c:forEach>
					</select>
					<select id="searchType" name="searchType" style="width: 80px;">
					<option value="name">名称</option>
					<option value="number" ${searchType eq "number"?'selected':'' }>编码</option>
					<option value="developer" ${searchType eq "developer"?'selected':'' }>开发者</option>
					</select>
					&nbsp;<input id="s_subprojectText" name="s_subprojectText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_subprojectText }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-hover table-striped table-bordered">
				<tr>
					<th>编号</th>
					<th>编码</th>
					<th>项目名称</th>
					<th>状态</th>
					<th>项目类型</th>
					<th>开发者</th>
					<th>开发者电话</th>
					<th width="15%">操作</th>
				</tr>
				<c:forEach  varStatus="i" var="subproject" items="${subprojectList }">
					<tr>
						<td>${i.count+(page-1)*pageSize }</td>
						<td>${subproject.subNumber }</td>
						<td>${subproject.name }</td>
						<td>${subproject.state }</td>
						<td>${subproject.itemTypeName==null?"无":subproject.itemTypeName }</td>
						<td>${subproject.developerName }</td>
						<td>${subproject.tel }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='subproject?action=preSave&subprojectId=${subproject.subprojectId }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='subproject?action=record&subprojectId=${subproject.subprojectId }'">提交记录</button>&nbsp;
							<button class="btn btn-mini btn-danger" type="button" onclick="subprojectDelete(${subproject.subprojectId })">删除</button></td>
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