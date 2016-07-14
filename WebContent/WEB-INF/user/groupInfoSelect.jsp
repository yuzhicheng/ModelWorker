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
textarea {
	width: 85%;
	  resize: none;
}	
</style>
</head>
<body>
<div id="all">
	<table>
		<tr>
			<th>集体称号:</th>
			<td><input type="text" value="${group.groupname }" readonly="readonly"></td>			
			<th>荣誉称号:</th>
			<c:if test="${groupApply.title eq 0}"><td><input type="text" value="全国五一劳动奖章单位" readonly="readonly"></td></c:if>
			<c:if test="${groupApply.title eq 1}"><td><input type="text" value="四川省五一劳动奖章单位" readonly="readonly"></td></c:if>
			<c:if test="${groupApply.title eq 2}"><td><input type="text" value="全国工人先锋号" readonly="readonly"></td></c:if>
			<c:if test="${groupApply.title eq 3}"><td><input type="text" value="四川省工人先锋号" readonly="readonly"></td></c:if>
			<c:if test="${groupApply.title eq 4}"><td><input type="text" value="企业工人先锋号" readonly="readonly"></td></c:if>
			<c:if test="${groupApply.title eq 5}"><td><input type="text" value="全国先进生产单位" readonly="readonly"></td></c:if>
			<c:if test="${groupApply.title eq 6}"><td><input type="text" value="省先进生产单位" readonly="readonly"></td></c:if>	
		</tr>
		<tr>
			<th>地址:</th>
			<td><input type="text" value="${group.address }" readonly="readonly"></td>	
			<th>隶属市州产业：</th>
			<td><input type="text" value="${group.belong }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>负责人姓名:</th>
			<td><input type="text" value="${group.managerName }" readonly="readonly"></td>
			<th>负责人电话:</th>
			<td><input type="text" value="${group.phone }" readonly="readonly"></td>
		</tr>
		<tr>
			<th>申请日期:</th>
			<td><input type="text" value="${groupApply.year }" readonly="readonly"></td>
			<th>审核结果:</th>
			<c:if test="${groupApply.result eq 0}"><td><input type="text" value="新上报" readonly="readonly"></td></c:if>
			<c:if test="${groupApply.result eq 1}"><td><input type="text" value="已认定" readonly="readonly"></td></c:if>
			<c:if test="${groupApply.result eq 2}"><td><input type="text" value="取消称号" readonly="readonly"></td></c:if>					
		</tr>
		<tr>
			<th>突出事迹:</th>
			<td colspan="3"><textarea rows="5" cols="10" readonly="readonly">${groupApply.achievement }</textarea></td>
		</tr>		
	</table>
</div>
</body>
</html>