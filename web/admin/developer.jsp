<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

$(document).ready(function(){
	$("ul li:eq(2)").addClass("active");
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
			null,
			null,
			{ "asSorting": [ ] },
			{ "asSorting": [ ] }
		]
	});
});

window.onload = function(){ 
	$("#DataTables_Table_0_wrapper .row-fluid").remove();
};
	function developerDelete(developerId) {
		if(confirm("您确定要删除这个开发者吗？")) {
			window.location="developer?action=delete&developerId="+developerId;
		}
	}
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
			开发者管理
		</div>
		<form name="myForm" class="form-search" method="post" action="developer?action=search" style="padding-bottom: 0px">
				<button class="btn btn-success" type="button" style="margin-right: 50px;" onclick="javascript:window.location='developer?action=preSave'">添加</button>
		</form>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<th>ID</th>
					<th>用户名</th>
					<th>姓名</th>
					<th>性别</th>
					<th>项目管理者</th>
					<th>电话</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="developer" items="${developerList }">
					<tr>
						<%-- <td>${i.count+(page-1)*pageSize }</td> --%>
						<td>${developer.developerId }</td>
						<td>${developer.userName }</td>
						<td>${developer.name}</td>
						<td>${developer.sex}</td>
						<td>${developer.itemManagerName}</td>
						<td>${developer.tel }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='developer?action=preSave&developerId=${developer.developerId }'">修改</button>&nbsp;
							<button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='developer?action=reset&developerId=${developer.developerId }'">重置密码</button>
							<button class="btn btn-mini btn-danger" type="button" onclick="developerDelete(${developer.developerId })">删除</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
</div>