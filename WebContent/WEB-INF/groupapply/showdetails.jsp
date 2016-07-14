<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	body{
		background-color:#f5f5f5;
	}
	.maincontainer{
		width:700px;
		height:450px;
		margin: 0 auto;
		font-family:"lucida"," Grande","Verdana";
	}
	#remind span{
		font-size:1.2em;
		color:red;
	}
	table{
		width:600px;
		height:250px;
		margin-top:30px;
		margin-left:30px;
		font-size:1.4em;
		color:black;
	}
	td{
		height:25px;
		width:80px;
		margin-left:10px;
		font-size:0.8em;
	}
	.leftlabel{
		font-size:0.9em;
		font-weight:bold;
		width:70px;
	}
	.rightlabel{
		font-size:0.9em;
		font-weight:bold;
		width:55px;
	}
	.leftspan{
		margin-left:18px;
	}
	.rightspan{
		margin-left:36px;
	}
	a{
		color:blue;
		font-size:1.2em;
		margin-left:250px;
	}
	#remind{
		margin:40px 60px 0 ;
	}
</style>
</head>
<body class="nested">
    <div class="maincontainer">
		<div>
			<div id="remind">
				<span>请确认您的基本信息，基本信息可以在用户管理中进行修改</span>
   	  		</div>
   	  		<table class="list_table"> 	  			
            	<tr>
            		<td class="leftlabel">集体名称:</td>
	                <td>${empty group.name?" ":group.name }</td>
	                <td class="rightlabel">地<span class="rightspan"></span>址:</td>
	             	<td>${empty group.address?" ":group.address }</td>
	            </tr>
	            <tr>
	            	<td class="leftlabel">负责人姓名:</td>
	            	<td>${empty group.managerName?" ":group.managerName }</td>
	                <td class="rightlabel">电<span class="rightspan"></span>话:</td>
	                <td>${empty group.phone?" ":group.phone }</td>
	               
	            </tr>
	            <tr>
	               <td class="leftlabel">隶属市州产业:</td>
	               <td>${empty group.belong?" ":group.belong }</td>
                </tr>              	              
          	 </table>
          	 <a href="/ModelWorker/groupapply/applyform?groupname=${group.groupname }">下一步</a>
          </div>
         
        </div>
</body>
</html>