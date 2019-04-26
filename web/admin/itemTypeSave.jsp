<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	function checkForm(){
		var itemTypeName=document.getElementById("itemTypeName").value;
		if(itemTypeName==null||itemTypeName==""){
			document.getElementById("error").innerHTML="名称不能为空！";
			return false;
		}
		return true;
	}
	
	$(document).ready(function(){
		$("ul li:eq(3)").addClass("active");
	});
</script>
<div class="data_list">
		<div class="data_list_title">
		<c:choose>
			<c:when test="${itemType.itemTypeId!=null }">
				修改项目类别
			</c:when>
			<c:otherwise>
				添加项目类别
			</c:otherwise>
		</c:choose>
		</div>
		<form action="itemType?action=save" method="post" onsubmit="return checkForm()">
			<div class="data_form" >
				<input type="hidden" id="itemTypeId" name="itemTypeId" value="${itemType.itemTypeId }"/>
					<table align="center">
						<tr>
							<td><font color="red">*</font>名称：</td>
							<td><input type="text" id="itemTypeName"  name="itemTypeName" value="${itemType.itemTypeName }"  style="margin-top:5px;height:30px;" /></td>
						</tr>
						<tr>
							<td>&nbsp;简介：</td>
							<td><textarea id="detail" name="detail" rows="10">${itemType.detail }</textarea></td>
						</tr>
					</table>
					<div align="center">
						<input type="submit" class="btn btn-primary" value="保存"/>
						&nbsp;<button class="btn btn-primary" type="button" onclick="javascript:history.back()">返回</button>
					</div>
					<div align="center">
						<font id="error" color="red">${error }</font>
					</div>
			</div>
		</form>
</div>