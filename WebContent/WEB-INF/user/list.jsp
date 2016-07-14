<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/ModelWorker/static/css/reset.css" rel="stylesheet" type="text/css" />
<link href="/ModelWorker/static/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		delete_json();
		
		$(".nextpage").click(function(){
			var page = ${page};
			var totalPage = ${size/10};
			totalPage = (totalPage === 0 ? 1 :Math.ceil(totalPage));
			if(page==totalPage)
				return false;
		});
		
		$(".previouspage").click(function(){
			var page = ${page};
			if(page==1)
				return false;
		});
		
		$(".resetbtn").click(function(){
			$(".default").attr("selected","selected");
			$(".inputbox").attr("value","");
			return false; 
		});	
		
		if('${method }' == 3 ){
			$(".turnon").removeClass("turnon").hide().siblings("select").addClass("turnon").show();
		} else {
			$(".turnon").show().siblings("select").hide();
		}
		$("#inquery_method").blur(function(){
			var inqueryMethod = $("#inquery_method option");
			var inqueryIndex;
			for(var i = 0; i < inqueryMethod.length; i++){
				if(inqueryMethod[i].selected == true){
					inqueryIndex = i;
				}
			}
			if(inqueryIndex == 4){
				$(".turnon").removeClass("turnon").hide().siblings("select").addClass("turnon");			
			} else {
				$(".turnon").removeClass("turnon").hide();
				$(".inputbox").addClass("turnon");				
			}
			$(".turnon").show();
			
		});	
		
		
	});
 	function delete_json(){
		var page = ${page};
		var size = ${size};

		$(".delete").click(function(){
			if(10*(page-1)+1 == size){

				this.href = this.href +'&page=${page}&size='+size;
				
				return true;   
				
			} else {

				url = this.href;
				args = {};
				$.get(url,args);
				size -= 1;
				$(this).parents("tr").empty();
				$("#size").text(size);
				return false;
			}
			
		}); 
		
	}
</script>
</head>
<body class="nested">
	<div>
	    <div id="div1" class="maincontainer">
	    	<div id="inquery_div">
	    		<form action="/ModelWorker/user/list" method="post">
	    		<table id="inquery_table">
		    		<tr>
		    			<td>查询用户</td>
			    		<td>
			    			<select id="inquery_method" class="selectbox" name="method">
			        				<option class="default" value="4">---请选择查询方式---</option>
			        				<option ${method eq 0?"selected='selected'":""} value="0">按用户姓名</option>
			        				<option ${method eq 1?"selected='selected'":""} value="1">按用户身份证</option>
			        				<option ${method eq 2?"selected='selected'":""} value="2">按用户账号</option>
			        				<option ${method eq 3?"selected='selected'":""} value="3">按用户权限</option>
		        			</select>
			    		</td>  			
			    		<td>
			    			<input class="inputbox turnon" type="text" name="inqueryInfo" value="${inqueryInfo}"/>
			    			<select class="selectbox " name="permission">
			        				<option value="3">---请选择权限---</option>
			        				<option ${permission eq 0?"selected='selected'":""} value="0">系统管理员权限</option>
			        				<option ${permission eq 1?"selected='selected'":""} value="1">审核权限</option>
			        				<option ${permission eq 2?"selected='selected'":""} value="2">普通用户</option>
		        			</select>  		
			    			<input class="inquerybtn" type="submit" value="确认" />
			    			<input class="inquerybtn resetbtn" type="reset" value="重置" />
			    		</td>
		    		</tr>
		    		</table>    			    	
	    		</form>
	    	</div>
			<div>
	   	  		<table class="list_table">
	            	<tr>
	                    <th>序号</th><th>姓名</th><th>性别</th><th>身份证号</th><th>民族</th><th>籍贯</th><th>电话</th>
	                    <th>邮箱</th><th>所属公会</th><th>权限</th><th>账号</th><th>操作</th>
	                </tr>
	                <c:forEach items="${userListPart }"  var="user" varStatus="status">
	            	<tr>
		                <td>${status.index+1 }</td>
		                <td>${empty user.name?" ":user.name }</td>
		                <td>${empty user.sex?" ":sexList[user.sex]}</td>
		                <td>${empty user.idCard?" ":user.idCard }</td>
		                <td>${empty user.nation?" ":nationList[user.nation]}</td>
		                <td>${empty user.birthPlace?" ":user.birthPlace}</td>
		                <td>${empty user.phone?" ":user.phone }</td>
		                <td>${empty user.email?" ":user.email }</td>
		                <td>${empty user.unionName?" ":unionList[user.unionName] }</td>
		                <c:choose>
		                	<c:when test="${empty user.permission }">
		             			<td>&nbsp;</td>
		             		</c:when>
		                	<c:when test="${user.permission == 0}">
		                		<td>系统管理员权限</td>
		                	</c:when>
		                	<c:when test="${user.permission == 1 }">
		                		<td>审核权限</td>
		                	</c:when>
		                	<c:when test="${user.permission == 2 }">
		                		<td>普通用户</td>
		                	</c:when>
		                </c:choose>
		                <td>${empty user.username?" ":user.username }</td>
		                <%-- <td>${empty user.password?" ":user.password }</td> --%>
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
	            	<div class="pagination">
	            		<span>&nbsp;第${page} /
	            			<fmt:formatNumber type="number" value="${size eq 0?1: Math.ceil(size/10)}" maxFractionDigits="0"/>页&nbsp;
	            		</span>
	                	<a class="previouspage" href="/ModelWorker/user/list?page=${page-1}&method=${method}&inqueryInfo=${inqueryInfo}&permission=${permission}">上一页</a>&nbsp;
	                	<a class="nextpage" href="/ModelWorker/user/list?page=${page+1}&method=${method}&inqueryInfo=${inqueryInfo}&permission=${permission}">下一页</a>
	                	<span>共&nbsp;<label id="size">${size}</label>&nbsp;条</span>
	                </div>
	            </div> 
	        </div>	    
        </div>
</body>
</html>