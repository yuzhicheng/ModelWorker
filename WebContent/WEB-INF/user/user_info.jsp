<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>
<style type="text/css">
body {
	padding: 0;
	margin: 0;
	width: 100%;
	height: 100%;
	position: absolute;
	background-color: rgba(224, 202, 154, 0.9);
}
div{
	
	margin-left: auto;
  	margin-right: auto;
  	width: 60%;
  	margin-top: 3%;
  	
}
table {
	 background-color: rgb(200, 165, 100);
	   width: 100%;
}
select {
	width: 35%;
}
.text_title{
	text-align: right;
}
.btn{
	width: 65px;
  	height: 30px;
  	margin-left: 40%;
} 
.addr_text{
	 width: 93%;
}
.gray_input{
	  color: #968E8E;
}
.mofify_message{
	color: red;
	text-align: center;
	height: 15px;
}

</style>
</head>
<body>
<div>
<c:if test="${groupinfo eq null }">
	<form action="/ModelWorker/User_Manage/modifyUserInfo" method="post">
		<table cellpadding="5" cellspacing="10">
			<tbody>
				<tr>
					<td class="text_title" colspan="4"><div class="mofify_message">${mofify_message}</div></td>
				</tr>
				<tr>
					<td class="text_title">姓名：</td><td><input type="text" readonly="true" class="gray_input" value="${userinfo.name }"></td>
					<td class="text_title">用户权限：</td><td><input type="text" readonly="true" class="gray_input" value="${userinfo.permission eq 0? '管理员': (userinfo.permission eq 1? '审核权限':'普通用户' ) }"></td>																		
				</tr>
				<tr>
					<td class="text_title">性别：</td><td><input type="text" readonly="true" class="gray_input" value="${userinfo.sex eq 0? '男': '女' }"></td>
					<td class="text_title">民族：</td>
					<td>
						<c:if test="${userinfo.nation eq 0 }">
							<input type="text" class="gray_input" value="汉族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 1 }">
							<input type="text" class="gray_input" value="壮族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 2 }">
							<input type="text" class="gray_input" value="满族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 3 }">
							<input type="text" class="gray_input" value="回族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 4 }">
							<input type="text" class="gray_input" value="苗族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 5 }">
							<input type="text" class="gray_input" value="维吾尔族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 6 }">
							<input type="text" class="gray_input" value="土家族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 7 }">
							<input type="text" class="gray_input" value="彝族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 8 }">
							<input type="text" class="gray_input" value="蒙古族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 9 }">
							<input type="text" class="gray_input" value="藏族" readonly="true">
						</c:if>
						<c:if test="${userinfo.nation eq 10 }">
							<input type="text" class="gray_input" value="其他" readonly="true">
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="text_title">身份证：</td><td><input type="text" readonly="true" class="gray_input" value="${userinfo.idCard}"></td>
					<td class="text_title">个人状态：</td>
					<td><select name="state" disabled="disabled" style="width:84%;">
							<option ${userinfo.personcondition eq 0?"selected=selected":""}>在职</option>
							<option ${userinfo.personcondition eq 1?"selected=selected":""}>退休</option>
							<option ${userinfo.personcondition eq 2?"selected=selected":""}>死亡</option>
						</select>						
					</td>	
				</tr>			
				<tr>
					<td class="text_title" >籍贯：</td><td ><input type="text" class="gray_input" value="${userinfo.birthPlace }" readonly="true"></td>
					<td class="text_title">隶属工会：</td>
					<td>
						<c:if test="${userinfo.unionName eq -1}">
							<input type="text" value="其他" name="unionName" onfocus="Myblur();">
						</c:if>
						<c:if test="${userinfo.unionName eq 0}">
							<input type="text" value="省总工会" name="unionName" onfocus="Myblur();">
						</c:if>
						<c:if test="${userinfo.unionName eq 1}">
							<input type="text" value="市州总工会" name="unionName" onfocus="Myblur();">
						</c:if>
						<c:if test="${userinfo.unionName eq 2}">
							<input type="text" value="省产业局" name="unionName" onfocus="Myblur();">
						</c:if>
						<c:if test="${userinfo.unionName eq 3}">
							<input type="text" value="企业集团公会" name="unionName" onfocus="Myblur();">
						</c:if>
					</td>
				</tr>			
				<tr>					
					<td class="text_title">电子邮箱：</td><td><input type="text" value="${userinfo.email }" name="email" onfocus="Myblur();"></td>								
					<td class="text_title">联系电话：</td><td><input type="text" value="${userinfo.phone }" name="phone" onfocus="Myblur();"></td>
				</tr>				
				<tr>				
					<td class="text_title">职位：</td><td><input type="text" value="${userinfo.post }" name="post" onfocus="Myblur();"></td>					
					<td class="text_title">学历：</td><td><input type="text" value="${userinfo.degree }" name="degree" onfocus="Myblur();"></td>
				</tr>				
								<tr>
					<td class="text_title">地址：</td><td  colspan="3"><input type="text" class="addr_text" value="${userinfo.address }" name="address" onfocus="Myblur();"></td>				
				</tr>				
				<tr>
					<td colspan="2"><input type="submit" value="确认" class="btn"></td>
					<td colspan="2"><input id="resetbtn"type="button" value="重置" class="btn"></td>
				</tr>
			</tbody>
		</table>
	</form>
</c:if>
<c:if test="${userinfo eq null }">
	<form action="/ModelWorker/User_Manage/modifyUserInfo" method="post">
		<table cellpadding="5" cellspacing="10">
			<tbody>
				<tr>
					<td class="text_title" colspan="4"><div class="mofify_message">${mofify_message}</div></td>
				</tr>
				<tr>
					<td class="text_title">集体称号：</td><td><input type="text" readonly="true" class="gray_input" value="${groupinfo.name }"></td>
				</tr>
				<tr>		
					<td class="text_title">隶属州市产业：</td>
					<td>					
						<input type="text" value="${groupinfo.belong }" name="belong" onfocus="Myblur();">
					</td>
				</tr>	
				<tr>
					<td class="text_title">负责人姓名：</td><td><input type="text" value="${groupinfo.managerName }" name="managerName" onfocus="Myblur();"></td>								
				</tr>						
				<tr>									
					<td class="text_title">负责人电话：</td><td><input type="text" value="${groupinfo.phone }" name="phone" onfocus="Myblur();"></td>
				</tr>				
				<tr>
					<td class="text_title">地址：</td><td  colspan="3"><input type="text" style="width: 92%;" class="addr_text" value="${groupinfo.address }" name="address" onfocus="Myblur();"></td>				
				</tr>				
				<tr>
					<td><input type="submit" value="确认" class="btn"></td>
					<td><input id="groupbtn"type="button" value="重置" class="btn"></td>
				</tr>
			</tbody>
		</table>
	</form>
</c:if>
</div>
</body>
<script type="text/javascript">
	function Myblur(){
		$(".mofify_message").text("");
	}
	
	$("#resetbtn").click(function(){
		$("input[name='managerName']").val("");
		$("input[name='phone']").val("");
		$("input[name='post']").val("");
		$("input[name='degree']").val("");
		$("input[name='unionName']").val("");
		$("input[name='address']").val("");
	});
	
	$("#groupbtn").click(function(){
		$("input[name='managerName']").val("");
		$("input[name='phone']").val("");
		$("input[name='belong']").val("");
		$("input[name='address']").val("");
	});
</script>
</html>