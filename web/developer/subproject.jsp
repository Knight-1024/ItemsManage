<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">

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
			我的完成记录
		</div>
		<div>
			<table class="table table-striped table-bordered table-hover datatable">
				<thead>
				<tr>
					<th>编码</th>
					<th>名称</th>
					<th>归属</th>
					<th>状态</th>
					<th>开发者</th>
					<th>开发者电话</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  varStatus="i" var="subproject" items="${subprojectList }">
					<tr>
						<td>${subproject.subNumber }</td>
						<td>${subproject.name }</td>
						<td>${subproject.itemTypeName==null?"无":subproject.itemTypeName }</td>
						<td>${subproject.state eq "1"?"完成":"未完成" }</td>
						<td>${subproject.developerName }</td>
						<td>${subproject.tel }</td>
						<td><button class="btn btn-mini btn-info" type="button" onclick="javascript:window.location='developer?action=submit&subprojectId=${subproject.subprojectId }'">取消完成</button>&nbsp;
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
		<div align="center"><font color="red">${error }</font></div>
</div>