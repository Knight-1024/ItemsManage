<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var userName=document.getElementById("userName").value;
		var itemManaerId=document.getElementById("itemManaerId").value;
		var developerName=document.getElementById("developerName").value;
		var name=document.getElementById("name").value;
		var sex=document.getElementById("sex").value;
		var tel=document.getElementById("tel").value;
		if(userName==""||name==""||sex==""||tel==""||itemManaerId==""||developerName==""){
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
			<c:when test="${developer.developerId!=null }">
				修改开发者信息
			</c:when>
			<c:otherwise>
				添加开发者
			</c:otherwise>
		</c:choose>
		</div>
		<form action="developer?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="developerId" name="developerId" value="${developer.developerId }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>用户名：</td>
							<td><input type="text" id="userName"  name="userName" value="${developer.userName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>姓名：</td>
							<td><input type="text" id="name"  name="name" value="${developer.name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>性别：</td>
							<td>
								<select id="sex" name="sex" style="width: 90px;">
									<option value="男" ${developer.sex eq "男"?'selected':'' }>男</option>
									<option value="女" ${developer.sex eq "女"?'selected':'' }>女</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>项目管理者：</td>
							<td>
								<select id="itemManaerId" name="itemManaerId" style="width: 90px;">
									<option value="">请选择...</option>
									<c:forEach var="itemManaer" items="${itemManagerList }">
										<option value="${itemManaer.itemManagerId }" ${developer.itemManaerId==itemManaer.itemManagerId?'selected':'' }>${itemManaer.name }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td><font color="red">*</font>电话：</td>
							<td><input type="text" id="tel"  name="tel" value="${developer.tel }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:window.location='developer?action=list'">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>