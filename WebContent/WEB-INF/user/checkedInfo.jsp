<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
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
   
   .table-header{
				list-style:none;
				margin-top:10px;
				margin-left:10px;
				padding:0;
			}
			
   .table-con{
				border:1px solid #ccc;
				width:100%;
				height:100%;
				margin-top:5px;
				display:none;
			}
			
	.table-header li.on{
				background-color:#aad;
			}
			
	.table-header li{
				border:1px solid #ccc;
				display:inline;
				padding:5px 10px;
				cursor:pointer;
			}
</style>
<script type="text/javascript">
		$(document).ready(function()
		{
		
			if('${sign}'!=1)
				{
				$('.table-con:first').show();
				$('.table-header li:first').addClass("on");
				}
			else
				{
					$('.table-header li:first').siblings('li').addClass("on");
					$('.table-con:first').siblings('div').show();
				}
			
			$('.table-header li').click(function()
			{
				$(this).addClass("on").siblings('.on').removeClass("on");
				var hh=$(this).attr("hh");
				$(hh).show().siblings('div').hide();
				
			}
			);
		}
		);
</script>

</head>

<body class="nested">
    <div class="maincontainer">
   				 <ul class="table-header">
					<li hh="#no1">已审核个人</li>
					<li hh="#no2">已审核集体</li>
				</ul>
    <div id="no1" class ="table-con">
    	<div class="columnconn"><span class="columntitle">已审核个人列表</span></div>	
   	         <table class="list_table" width="98%">
            	<tr>
                	<th width="40px"><input type="checkbox" name="checkedAll" alt="全选/取消" onclick="doCheckedAll(this);" /></th>
                    <th width="50px">序号</th><th>姓名</th><th>登录号</th><th>身份证号</th><th>性别</th><th>民族</th><th>申请劳模称号</th><th>评定结果</th><th>操作</th>
                </tr>
                <c:forEach items="${checkedList }" var="checkedapply" varStatus="status">
            	<tr>
            	  <td class="checkbox_first"><input type="checkbox" name="check1" class="rowchecker" /></td>
                  <td>${status.index+1 }</td>
                  <td>${checkedapply.name}</td>
                  <td>${checkedapply.username}</td>
                  <td>${checkedapply.idCard}</td>
                  <td>${personsex[checkedapply.sex]}</td>
                  <td>${personnation[checkedapply.nation]}</td>
                  <td>${persontitle[checkedapply.title]}</td>
                  <td>${result[checkedapply.result]}</td>
                  <td>
                    <a href="/ModelWorker/user/checkcancel?username=${checkedapply.username }">撤销</a>              	
                  </td>
                </tr>
                </c:forEach>
            </table>
            <label id="lab"></label> 
        </div>
        <div id="no2" class ="table-con">
        	<div class="columnconn"><span class="columntitle">已审核集体列表</span></div>	
   	         <table class="list_table" width="98%">
            	<tr>
                	<th width="40px"><input type="checkbox" name="checkedAll" alt="全选/取消" onclick="doCheckedAll(this);" /></th>
                    <th width="50px">序号</th><th>集体名称</th><th>隶属产业</th><th>申请先进称号</th><th>认定结果</th><th>操作</th>
                </tr>
                <c:forEach items="${groupCheckedList}" var="groupcheckedapply" varStatus="status">
            	<tr>
            	  <td class="checkbox_first"><input type="checkbox" name="check1" class="rowchecker" /></td>
                  <td>${status.index+1 }</td>
                  <td>${groupcheckedapply.name}</td>
                  <td>${groupcheckedapply.belong}</td>
                  <td>${grouptitle[groupcheckedapply.title]}</td>
                  <td>${result[groupcheckedapply.result]}</td>
                  <td>
                    <a href="/ModelWorker/group/checkcancel?groupname=${groupcheckedapply.groupname }">撤销</a>              	
                  </td>
                </tr>
                </c:forEach>
            </table>
            <label id="lab"></label> 
        </div>
        </div>
</body>
</html>
