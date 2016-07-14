<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td
	{
		border: 1px solid;
        border-color: gray;
        text-align: center;
        border-width:0 1px 1px 0;
	}
	table {
  		display: table;
  		border-collapse: separate;
  		border-spacing: 0px;
  		border-color: grey;
  		margin:50px auto;
	}
	.columnType1{
		border-width:1px 1px 1px 1px;
	}
	.columnType2{
		border-width:1px 1px 1px 0px;
	}
	.columnType3{
		border-width:0px 1px 1px 1px;
	}
	.left
	{
		width: 120px;
		background-color: rgba(255, 83, 27, 0.9);
	}
	.right
	{
		width: 200px;
	}
	textarea
	{	
		margin: 5px;
		width: 96%;
		height: 120px;
	}
	.button
	{
		border:none;
	}
	input
	{
		width:100px;
		height:70px;
	}
</style>
</head>
<body style="background-color: ghostwhite;">
	<table>
		<tr>
			<td class="left columnType1">姓名</td>
			<td class="right columnType2">${user.name}</td>
			<td class="left columnType2">身份证</td>
			<td class="right columnType2" id="idCard">${user.idCard}</td>
		</tr>
		<tr>
			<td class="left columnType3">当前状态</td>
			<td class="right">${personcondition }</td>
			<td class="left">变更状态</td>
			<td class="right">
				<select id="status">
                <option value="4">---请选择---</option>
                <option  value="0">在职</option>
                <option  value="1">退休</option>
                <option  value="2">死亡</option>
            </select>
			</td>
		</tr>
		<tr>
			<td class="cause columnType3">变更原因</td>
			<td colspan="3"><textarea name="remark" cols="60" rows="8" style="background-color: ghostwhite;"></textarea></td>
		</tr>
	</table>
	<table>
		<tr>
			<td class="button"><input type="button" style="height:39px;margin-right:50px;" value="提交" onclick="changeStatus()"></input></td>
			<td class="button"><input type="button" style="height:39px;margin-letf:50px;" value="取消" onclick="window.location.href='/ModelWorker/user/personinfo'"></input></td>
		</tr>	
	</table>
</body>
<script src="/ModelWorker/static/js/common/jquery-2.1.4.js"></script>
<script type="text/javascript">
	function changeStatus()
	{
		 var status=parseInt($("#status option:selected").attr("value"));
		 if(status==4){
			 alert("请选择更改的状态");
		 }
		 else{
			 location.href="/ModelWorker/StatusManage/Change?status="+status+"&cause="+$("textarea").val()+"&idCard="+$("#idCard").text();
		 }
	}
</script>
</html>