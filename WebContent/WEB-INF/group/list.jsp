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
	});

</script>
</head>
<body>
	<div class="maincontainer">
        	<div id="inquery_div">
    		<form action="/ModelWorker/group/list" method="post">
    		<table id="inquery_table">
	    		<tr>
	    			<td>查询用户</td>
		    		<td>
		    			<select class="selectbox" name="method">
		        				<option class="default" value="2">---请选择查询方式---</option>
		        				<option ${method eq 0?"selected='selected'":""} value="0">按集体用户账号</option>
		        				<option ${method eq 1?"selected='selected'":""} value="1">按集体名</option>
	        			</select>
		    		</td>  			
		    		<td>
		    			<input class="inputbox" type="text" name="inqueryInfo" value="${inqueryInfo}"/>
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
                    <th>序号</th><th>集体名称</th><th>隶属市州产业</th><th>负责人姓名</th><th>电话号码</th><th>地址</th>
                    <th>账号</th><th>操作</th>
                </tr>
                <c:forEach items="${groupListPart }"  var="group" varStatus="status">
            	<tr>
	                <td>${status.index+1 }</td>
	                <td>${empty group.name?" ":group.name }</td>
	                <td>${empty group.belong?" ":group.belong }</td>
	                <td>${empty group.managerName?" ":group.managerName }</td>
	                <td>${empty group.phone?" ":group.phone }</td>
	                <td>${empty group.address?" ":group.address }</td>
	                <td>${empty group.groupname?" ":group.groupname }</td>
	                <td>
	                 	<a href="/ModelWorker/group/toSave?id=${group.id }&page=${page}">修改</a>
	                 	<a href="/ModelWorker/group/delete?id=${group.id }&size=${size}&page=${page}">删除</a>
	                </td>
	                
                </tr>              	
                </c:forEach>
                
          	 </table>
          </div>
          <div class="toolbg">
            	<div class="pagination">
            		<span>&nbsp;第${page} /
            			<fmt:formatNumber type="number" value="${size eq 0?1: Math.ceil(size/10)}" maxFractionDigits="0"/>页&nbsp;
            		</span>
                	<a class="previouspage" href="/ModelWorker/group/list?page=${page-1}&method=${method}&inqueryInfo=${inqueryInfo}">上一页</a>&nbsp;
                	<a class="nextpage" href="/ModelWorker/group/list?page=${page+1}&method=${method}&inqueryInfo=${inqueryInfo}">下一页</a>
                	<span>共&nbsp;${size}&nbsp;条</span>
                </div>
            </div> 
	  </div>
</body>
</html>