<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/ModelWorker/static/js/common/fenye.js"></script>
<title>Insert title here</title>
<style type="text/css">
	td{
        border-color: gray;
        text-align: center;
        border:solid #d3d3d3;
		border-width:0 1px 1px 0;
		padding:2px;
	}
	table {
  		display: table;
  		border-collapse: separate;
  		border-spacing: 0px;
  		border-color: grey;
  		margin:auto;
	}
	.columnType1{
		border-width:1px 1px 1px 1px;
	}
	.columnType2{
		border-width:1px 1px 1px 0px;
	}
	.index{
		border-width:0px 1px 1px 1px;
	}
	.errorInfo{
		width: 200px;
	}
</style>
</head>
<body>
	<table id="senfe" class="errorListTable">
		<thead>
			<tr>
				<td class="columnType1">错误序号</td>
				<td class="columnType2">出错行数</td>
				<td class="columnType2">出错列数</td>
				<td class="columnType2">错误信息</td>
			</tr>
		</thead>
		<tbody id="group_one">
			<c:forEach items="${errorList}" var="error" varStatus="status">
            	<tr>
                  <td class="index">${status.index+1 }</td>
                  <td>${error.row }</td>
                  <td>${error.column }</td>
                  <td class="errorInfo">${error.info }</td>
                </tr>
        	</c:forEach>
        </tbody>
	</table>
	 <div class="bottom" style="text-align: center;">
            <a href="#" onclick="page.firstPage();">首 页</a>|
            <a href="#" onclick="page.prePage();">上一页</a>|
            <a href="#" onclick="page.nextPage();">下一页</a>|
            <a href="#" onclick="page.lastPage();">末 页</a>
            <span id="divFood"></span>|第<input id="pageno" value="1" style="width:20px"/>页|
            <a href="#" onclick="page.aimPage();">跳转</a>
            </div>
</body>
</html>