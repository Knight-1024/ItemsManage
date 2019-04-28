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
	function subprojectDelete(subprojectId) {
		if(confirm("您确定要删除这个任务吗？")) {
			window.location="subproject?action=delete&subprojectId="+subprojectId;
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
			任务管理
		</div>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
					<tr>
					<!-- <th>编号</th> -->
					<th>编码</th>
					<th>名称</th>
					<th>状态</th>
					<th>归属</th>
					<th>开发者</th>
					<th>开发者电话</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="subproject" items="${subprojectList }">
					<tr>
						<%-- <td>${i.count+(page-1)*pageSize }</td> --%>
						<td>${subproject.userName }</td>
						<td>${subproject.name }</td>
						<td>${subproject.state }</td>
						<td>${subproject.itemTypeName==null?"无":subproject.itemTypeName }</td>
						<td>${subproject.developerName }</td>
						<td>${subproject.tel }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='developer?action=preDraw&subprojectId=${subproject.subprojectId }'">领取</button>&nbsp;
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
</div>