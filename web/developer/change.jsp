<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
function checkForm(){
	var oldPassword=document.getElementById("oldPassword").value;
	var newPassword=document.getElementById("newPassword").value;
	var rPassword=document.getElementById("rPassword").value;
	if(oldPassword==""||newPassword==""||rPassword==""){
		document.getElementById("error").innerHTML="信息填写不完整！";
		return false;
	} else if(newPassword!=rPassword){
		document.getElementById("error").innerHTML="密码填写不一致！";
		return false;
	}
	return true;
}
	
	$(document).ready(function(){
		$("ul li:eq(5)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
			修改个人信息
		</div>
		<form action="developer?action=change" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="developerId" name="developerId" value="${developer.developerId }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>用户名</td>
							<td><input type="text" id="userName"  name="userName" value="${userName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>名称</td>
							<td><input type="text" id="name"  name="name" value="${name }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td><font color="red">*</font>性别</td>
							<td><select id="sex"  name="sex" value="${sex }" style="margin-top:5px;height:30px; width:205px;" >
								<option value ="男" ${sex eq '男'?'selected="selected"':''}>男</option>
								<option value ="女" ${sex eq '女'?'selected="selected"':''}>女</option>
							</select></td>
						</tr>
						<tr>
							<td><font color="red">*</font>电话</td>
							<td><input type="text" id="tel"  name="tel" value="${tel }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="提交"/>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>