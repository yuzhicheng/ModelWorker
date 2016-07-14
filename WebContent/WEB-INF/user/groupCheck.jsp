<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.greentiansha.name/tags" prefix="taglib"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/common/common.js"></script>
<style type="text/css">
   body {
	margin:0;
	padding:0;
	font-size:12px;
	background:#fff none repeat scroll 0 0 ;
   }
    body, h1, h2, h3, h4, h5, h6, ul, ol, li, form, p, dl, dt, dd, legend, table, th, td, fieldset {
    margin: 0;
    padding: 0;
   }
   .infotable{
		color:black;
		line-height:30px;
		font-family:"lucida"," Grande","Verdana";
	}
	
	.inputtable td{
		margin:10px;
		padding:8px ;
		width:100px;
		height:30px;
		font-size:1.3em;
		color:black;
	}
	
   .columnconn {
	background-color:#f5f5f5;
	height:35px;
	line-height:35px;
	border-bottom:1px solid #d7d7d7;
   }
   .rowhover{
	background-color:#E3E6EB;
   }
   .list_table{
	border-collapse: collapse;
    width: 98%;
	border-spacing: 0;
    clear: both;
	border:#D2D2D2 solid;
	border-width:1px 0 0 1px;
	text-align:center;
   }
   
   .list_table th {
    background: none repeat scroll 0 0 #EEEEEE;
    border: 1px solid #D2D2D2;
    height: 28px;
    overflow: hidden;
	text-align:center;
	font-weight: bold;
}
   .list_table th,.list_table td{
	padding: 5px 10px;
    word-wrap: break-word;
	border:solid #D2D2D2;
	border-width:0 1px 1px 0;
	vertical-align:middle;
   }
   .list_table .checkbox_first{
	text-align:center;
   }
   
   .opacity
			{
				position:absolute;
				top:0px;
				left:0px;
				z-index:10;
				width:100%;
				height:100%;
				opacity:0.2;
				background-color:black;
				display:none;
			}
	.text-table
			{
				width:600px;
				height:400px;
				border:1px solid #ccc; 
				margin:15 auto;
				background-color:white;
				clear:both;
			}
</style>
 <script type="text/javascript">
 function agress(target){
		$.post("/ModelWorker/group/groupcheckpass?groupname="+$(target).parent().siblings('.item1').text(),{});
		console.info("/ModelWorker/user/checkpass?groupname="+$(target).parent().siblings('.item1').text());

		
	}
	function refuse(target){
		$.post("/ModelWorker/user/checkrefuse?groupname="+$(target).parent().siblings('.item1').text(),{});
	}

 </script>
</head>
<body class="nested">
    <div class="maincontainer">
    	<div class="columnconn"><span class="columntitle">集体审核列表</span></div>	
   	         <taglib:table items="${groupApplyList}" display_num="11" flush="true" uri="/ModelWorker/group/groupcheckpage">
				<taglib:column property="name" label="集体名" />
				<taglib:column property="groupname" label="登录号" hidden="true"/>
				<taglib:column property="belong" label="所属产业"/>
				<taglib:column property="title" label="申请先进称号" dictionary="${groupTitle}" width="200"/>
				<taglib:column property="achievement" label="先进事迹"  />
				<taglib:clickColumn label="操作" value="通过" onclick="agress(this);"/>
				<taglib:clickColumn label="操作" value="拒绝" onclick="refuse(this);"/>
			</taglib:table>
            <label id="lab"></label> 
        </div>
		<%-- <div  class="opacity">
		</div>
		<div class="text-table">
			<table class="inputtable">
				<tr>
					<td class="infotable">集体名：${groupapply.name}</td>
					<td class="infotable">所属产业：${groupapply.belong}</td>
				</tr>
				<tr>
					<td class="infotable">地址：${groupapply.address}</td>
					<td class="infotable">申请称号：${groupTitle[groupapply.title]}</td>
				</tr>
				<tr>
					<td class="infotable">负责人姓名：${groupapply.manager}</td>
					<td class="infotable">联系电话：${groupapply.phone}</td>
				</tr>
				<tr>
					<td class="infotable">突出事迹：${groupapply.achievement}</td>
				</tr>				
			</table>
		</div> --%>
</body>
</html>