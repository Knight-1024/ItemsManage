<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var subNumber=document.getElementById("subNumber").value;
		var itemTypeId=document.getElementById("itemTypeId").value;
		var developerId=document.getElementById("developerId").value;
		var name=document.getElementById("name").value;
		var state=document.getElementById("state").value;
		if(subNumber==""||name==""||state==""||itemTypeId==""||developerId==""){
			document.getElementById("error").innerHTML="信息填写不完整！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(2)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${subproject.subprojectId!=null }">
				修改任务信息
			</c:when>
			<c:otherwise>
				添加任务
			</c:otherwise>
		</c:choose>
		</div>
		<form action="subproject?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="subprojectId" name="subprojectId" value="${subproject.subprojectId }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>编码：</td>
							<td><input type="text" id="subNumber"  name="subNumber" value="${subproject.subNumber }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>名称：</td>
							<td><input type="text" id="name"  name="name" value="${subproject.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>状态：</td>
							<td>
								<select id="state" name="state" style="width: 140px;">
									<option value="0" ${subproject.state eq "0"?'selected':'' }>未完成</option>
									<option value="1" ${subproject.state eq "1"?'selected':'' }>完成</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目类别：</td>
							<td>
								<select id="itemTypeId" name="itemTypeId" style="width: 140px;">
									<option value="">请选择项目类别</option>
									<c:forEach var="itemType" items="${itemTypeList }">
										<option value="${itemType.itemTypeId }" ${subproject.itemTypeId==itemType.itemTypeId?'selected':'' }>${itemType.itemTypeName }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目开发者：</td>
							<td><select id="developerId" name="developerId" style="width: 140px;">
								<option value="">请选择开发者</option>
								<c:forEach var="developer" items="${developerList }">
									<option value="${developer.developerId }" ${subproject.developerId==developer.developerId?'selected':'' }>${developer.name }</option>
								</c:forEach>
							</select></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='subproject'">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>