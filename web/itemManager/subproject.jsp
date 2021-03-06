<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(document).ready(function(){
	$("ul li:eq(1)").addClass("active");
	$('.datatable').dataTable( {        				
		 "oLanguage": {
				"sUrl": "/ItemManage/media/zh_CN.json"
		 },
		"bLengthChange": false, //改变每页显示数据数量
		"bFilter": false, //过滤功能
		"aoColumns": [
			null,
			null,
			null,
			{ "asSorting": [ ] },
			null,
			{ "asSorting": [ ] },
		]
	});
});

window.onload = function(){ 
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
};
</script>
<style type="text/css">
	.span6 {
		width:0px;
		height: 0px;
		padding-top: 0px;
		padding-bottom: 0px;
		margin-top: 0px;
		margin-bottom: 0px;
	}

</style>
<div class="data_list">
		<div class="data_list_title">
			任务管理
		</div>
		<form name="myForm" class="form-search" method="post" action="subproject?action=search" style="padding-bottom: 0px">
				<span class="data_search">
					<span><font style="font-family: '黑体'; font-style: 'bold'; font-size: 20px" color="blue">${itemTypeName }&nbsp;&nbsp;</font></span>
					<select id="searchType" name="searchType" style="width: 80px;">
					<option value="name">名称</option>
					<option value="number" ${searchType eq "number"?'selected':'' }>编码</option>
					</select>
					&nbsp;<input id="s_subprojectText" name="s_subprojectText" type="text"  style="width:120px;height: 30px;" class="input-medium search-query" value="${s_subprojectText }">
					&nbsp;<button type="submit" class="btn btn-info" onkeydown="if(event.keyCode==13) myForm.submit()">搜索</button>
				</span>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<th>编码</th>
					<th>名称</th>
					<th>状态</th>
					<th>项目类别</th>
					<th>开发者</th>
					<th>开发者电话</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="subproject" items="${subprojectList }">
					<tr>
						<%-- <td>${i.count+(page-1)*pageSize }</td> --%>
						<td>${subproject.subNumber }</td>
						<td>${subproject.name }</td>
						<td>${subproject.state eq "1"?"完成":"未完成"}</td>
						<td>${subproject.itemTypeName==null?"无":subproject.itemTypeName }</td>
						<td>${subproject.developerName }</td>
						<td>${subproject.tel }</td>
						<td>
							<button class="btn btn-mini btn-success" type="button" onclick="javascript:window.location='record?action=preSave&subprojectNumber=${subproject.subNumber }'">提交记录</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
</div>