<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/ModelWorker/static/css/reset.css" />
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
		height:30px;
		width:80px;
		margin-left:20px;
		font-size:0.8em;
	}
	.leftlabel{
		font-size:0.9em;
		font-weight:bold;
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
		margin-left:192px;
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
            		<td class="leftlabel">姓<span class="leftspan"></span>名:</td>
	                <td>${empty user.name?" ":user.name }</td>
	                <td class="leftlabel">性<span class="rightspan"></span>别:</td>
	                <td>${empty user.sex?" ":sexList[user.sex] }</td>
	            </tr>
	            <tr>
	              	<td class="leftlabel">身份证:</td>
	                <td>${empty user.idCard?" ":user.idCard }</td>
	                <td class="leftlabel">民<span class="rightspan"></span>族:</td>
	                <td>${empty user.nation?" ":nationList[user.nation] }</td>
	             </tr>
	             <tr>
	                <td class="leftlabel">电<span class="leftspan"></span>话:</td>
	                <td>${empty user.phone?" ":user.phone }</td>
	                <td class="leftlabel">邮<span class="rightspan"></span>箱:</td>
	                <td>${empty user.email?" ":user.email }</td>
	             </tr>
	             <tr>
	                <td class="leftlabel">学<span class="leftspan"></span>历:</td>
	                <td>${empty user.degree?" ":user.degree }</td>
	                <td class="leftlabel">所属公会:</td>
	                <td>${empty user.unionName?" ":unionList[user.unionName] }</td>
	            </tr>
	            <tr>
	                <td class="leftlabel">职<span class="leftspan"></span>位:</td>
	                <td>${empty user.post?" ":user.post }</td>
                </tr>              	              
          	 </table>
 
          	 <a href="/ModelWorker/personapply/applyform?username=${user.username }">下一步</a>
          </div>
         
        </div>
</body>
</html>