<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
body {
	width: 100%;
	height: 100%;
	position: absolute;
	padding: 0;
	margin: 0;
	text-align: center;
	background-color: rgba(224, 202, 154, 0.9);
}
#all{
	width: 80%;
	height: 80%;
	  margin: auto;
}
table {
  	margin: auto;
  	border-collapse: collapse;   	
    line-height:35px;
    border: solid #D2D2D2;
  	border-width: 0 1px 1px 0;
  	width: 100%;
  	text-align: left;
  	margin-top: 10%;
}
th {
	text-align: right;
}



			
</style>
</head>
<body>
<div id="all">
	<table>
		<tr>
			<th>姓名:</th>
			<td><input type="text" value="${user.name }" readonly="readonly"></td>	
			<th>身份证:</th>
			<td><input type="text" value="${user.idCard }" readonly="readonly"></td>
			<th>荣誉称号:</th>
			<c:if test="${advancePerson.title eq 0}"><td><input type="text" value="全国劳模" readonly="readonly"></td></c:if>
			<c:if test="${advancePerson.title eq 1}"><td><input type="text" value="四川省劳模全国五一劳动奖章" readonly="readonly"></td></c:if>
			<c:if test="${advancePerson.title eq 2}"><td><input type="text" value="四川省五一劳动奖章" readonly="readonly"></td></c:if>
			<c:if test="${advancePerson.title eq 3}"><td><input type="text" value="其他荣誉称号" readonly="readonly"></td></c:if>
			
		</tr>
		<tr>
			<th>性别:</th>
			<td><input type="text" value="${user.sex eq 0? '男':'女' }" readonly="readonly"></td>
			<th>劳模状态:</th>
			<c:if test="${advancePerson.state eq 0}"><td><input type="text" value="新上报" readonly="readonly"></td></c:if>
			<c:if test="${advancePerson.state eq 1}"><td><input type="text" value="已认定" readonly="readonly"></td></c:if>
			<c:if test="${advancePerson.state eq 2}"><td><input type="text" value="取消称号" readonly="readonly"></td></c:if>
			<th>申请日期:</th>
			<td><input type="text" value="${advancePerson.createDate }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>民族:</th>
				<c:if test="${user.nation eq 0}"><td><input type="text" value="汉族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 1}"><td><input type="text" value="壮族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 2}"><td><input type="text" value="满族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 3}"><td><input type="text" value="回族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 4}"><td><input type="text" value="苗族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 5}"><td><input type="text" value="维吾尔族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 6}"><td><input type="text" value="土家族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 7}"><td><input type="text" value="彝族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 8}"><td><input type="text" value="蒙古族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 9}"><td><input type="text" value="藏族" readonly="readonly"></td></c:if>
				<c:if test="${user.nation eq 10}"><td><input type="text" value="其他" readonly="readonly"></td></c:if>
			<th>隶属工会:</th>
			<c:if test="${user.unionName eq 0 }"><td><input type="text" value="省总工会" readonly="readonly"></td></c:if>
			<c:if test="${user.unionName eq 1 }"><td><input type="text" value="市州总工会" readonly="readonly"></td></c:if>
			<c:if test="${user.unionName eq 2 }"><td><input type="text" value="省产业局"></td></c:if>
			<c:if test="${user.unionName eq 3 }"><td><input type="text" value="企业集团公会" readonly="readonly"></td></c:if>		
			<th>享受待遇</th>
			<td>
				<c:if test="${advancePerson.treatment eq 0}"><input type="text" value="享受全国劳动模范待遇" readonly="readonly"></c:if>
				<c:if test="${advancePerson.treatment eq 1}"><input type="text" value="享受省、部级劳动模范待遇" readonly="readonly"></c:if>
				<c:if test="${advancePerson.treatment eq 2}"><input type="text" value="不能享受全国劳动模范待遇" readonly="readonly"></c:if>
				<c:if test="${advancePerson.treatment eq 3}"><input type="text" value="不能享受省、部级劳动模范待遇" readonly="readonly"></c:if>
			</td>
		</tr>
		<tr>
			<th>籍贯:</th>
			<td><input type="text" value="${user.birthPlace }" readonly="readonly"></td>
			<th>文化程度:</th>
			<td><input type="text" value="${user.degree }" readonly="readonly"></td>
			<th>评定年份</th>
			<td><input type="text" value="${advancePerson.year }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>权限:</th>
			<c:if test="${user.permission eq 0}"><td><input type="text" value="普通用户" readonly="readonly"></td></c:if>
			<c:if test="${user.permission eq 1}"><td><input type="text" value="有审核权限用户" readonly="readonly"></td></c:if>
			<c:if test="${user.permission eq 2}"><td><input type="text" value="管理员" readonly="readonly"></td></c:if>		
			<th>电子邮箱:</th>
			<td><input type="text" value="${user.email }" readonly="readonly"></td>
			<th>身体状况:</th>
			<td><input type="text" value="${advancePerson.bodycondition }" readonly="readonly"></td>
			
		</tr>
		<tr>
			<th>职位:</th>
			<td><input type="text" value="${user.post }" readonly="readonly"></td>
			<th>联系电话:</th>
			<td><input type="text" value="${user.phone }" readonly="readonly"></td>
			<th>家庭状况:</th>
			<td><input type="text" value="${advancePerson.difficulty }" readonly="readonly"></td>
			
		</tr>
		<tr>
			<th>状态:</th>
			<c:if test="${user.personcondition eq 0}"><td><input type="text" value="在职" readonly="readonly"></td></c:if>
			<c:if test="${user.personcondition eq 1}"><td><input type="text" value="退休" readonly="readonly"></td></c:if>
			<c:if test="${user.personcondition eq 2}"><td><input type="text" value="死亡" readonly="readonly"></td></c:if>
			<th>地址:</th>
			<td><input type="text" value="${user.address }" readonly="readonly"></td>
			<th>就业情况:</th>
			<td><input type="text" value="${advancePerson.workcondition }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>突出事迹:</th>
			<td colspan="5"><input type="text" value="${advancePerson.achievement }" readonly="readonly"></td>		
		</tr>
	</table>
</div>
</body>
</html>