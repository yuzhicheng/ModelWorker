<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/ModelWorker/static/css/reset.css" rel="stylesheet" type="text/css" />
<link href="/ModelWorker/static/css/list.css" rel="stylesheet" type="text/css" />
<title>Insert title here</title>
</head>
<body class="nested">
	<div>
	    <div id="div1" class="maincontainer">
			<div>
	   	  		<table class="list_table">
	            	<tr>
	                    <th>序号</th><th>姓名</th><th>性别</th><th>身份证号</th><th>民族<th>电话</th>
	                    <th>邮箱</th><th>所属公会</th><th>权限</th><th>账号</th><th>密码</th><th>操作</th>
	                </tr>
	                <c:forEach items="${userListPart }"  var="user" varStatus="status">
	            	<tr>
		                <td>${status.index+1 }</td>
		                <td>${empty user.name?" ":user.name }</td>
		             	<c:choose>
		             		<c:when test="${empty user.sex }">
		             			<td>&nbsp;</td>
		             		</c:when>
		                	<c:when test="${user.sex == 1}">
		                		<td>男</td>
		                	</c:when>
		                	<c:when test="${user.sex == 0 }">
		                		<td>女</td>
		                	</c:when>
		                </c:choose>
		                <td>${empty user.idCard?" ":user.idCard }</td>
		                <c:choose>
		             		<c:when test="${empty user.nation }">
		             			<td>&nbsp;</td>
		             		</c:when>
		                	<c:when test="${user.nation == 0}">
		                		<td>汉族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 1 }">
		                		<td>壮族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 2 }">
		                		<td>满族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 3 }">
		                		<td>回族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 4 }">
		                		<td>苗族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 5 }">
		                		<td>维吾尔族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 6 }">
		                		<td>土家族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 7 }">
		                		<td>彝族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 8 }">
		                		<td>蒙古族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 9 }">
		                		<td>藏族</td>
		                	</c:when>
		                	<c:when test="${user.nation == 10 }">
		                		<td>其他</td>
		                	</c:when>
		                </c:choose>
		                <td>${empty user.phone?" ":user.phone }</td>
		                <td>${empty user.email?" ":user.email }</td>
		                <c:choose>
		                	<c:when test="${empty user.unionName }">
		             			<td>&nbsp;</td>
		             		</c:when>
		                	<c:when test="${user.unionName == 0}">
		                		<td>省总工会</td>
		                	</c:when>
		                	<c:when test="${user.unionName == 1 }">
		                		<td>市州总工会</td>
		                	</c:when>
		                	<c:when test="${user.unionName == 2 }">
		                		<td>省产业局</td>
		                	</c:when>
		                	<c:when test="${user.unionName == 3 }">
		                		<td>企业集团工会</td>
		                	</c:when>
		                </c:choose>
		                <c:choose>
		                	<c:when test="${empty user.permission }">
		             			<td>&nbsp;</td>
		             		</c:when>
		                	<c:when test="${user.permission == 0}">
		                		<td>系统管理员权限</td>
		                	</c:when>
		                	<c:when test="${user.permission == 1 }">
		                		<td>录入权限</td>
		                	</c:when>
		                	<c:when test="${user.permission == 2 }">
		                		<td>信息查询权限</td>
		                	</c:when>
		                </c:choose>
		                <td>${empty user.username?" ":user.username }</td>
		                <td>${empty user.password?" ":user.password }</td>
		                <td>
		                 	<a href="/ModelWorker/user/toSave?id=${user.id }&page=${page}">修改</a>
		                 	<a class="delete" href="/ModelWorker/user/delete?id=${user.id }">删除</a>
		                </td>
	                </tr>              	
	                </c:forEach>
	               <!--   -->
	          	 </table>
	          </div>
	          <div class="toolbg"> 
            		<span>&nbsp;第${page} /
            			<fmt:formatNumber type="number" value="${size eq 0?1: Math.ceil(size/5)}" maxFractionDigits="0"/>页&nbsp;
            		</span>
                	<a class="previouspage" href="/ModelWorker/user/list2">上一页</a>&nbsp;
                	<a class="nextpage" href="/ModelWorker/user/list2">下一页</a>
                	<span>共&nbsp;<label id="size">${size}</label>&nbsp;条</span>	                
	          </div> 
	        </div>	    
        </div>
</body>
</html>