<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据还原</title>
</head>
<style type="text/css">
	body{
		padding-left:10px;
		padding-top:10px;
		background-color:#f5f5f5;
		line-height:35px;
	}

</style>
<body>
	<form action="">
		<ul>
			<li>数据字典恢复</li>
			<div style="color:red;font-size:15px">从指定备份路径中恢复数据字典<br/>
			请注意，当前操作将删除从备份之日起已更改的数据字典</div>
			<input type="button" style="width:111px;height:39px;" value="恢复数据字典" onclick="recoverDataDictionary();"/>
			<span id="prompt_dataDictionary" style="color:red"></span>
			<li>数据库恢复</li>
			<div style="color:red;font-size:15px">从指定文件中恢复数据库<br/>
			请注意，当前操作将删除从备份之日起已更改的数据库内容</div>
			<input type="button" style="width:111px;height:39px;" value="恢复数据库" onclick="recoverDatabase();"/>
			<span id="prompt_database" style="color:red"></span>
		</ul>
	</form>

</body>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>
<script type="text/javascript">
	function recoverDataDictionary(){
		$.post("/ModelWorker/user/recoverDataDictionary.do",{},function(data){
			if(data == "true")
				$("#prompt_dataDictionary").text("恢复成功");
			else
				$('#prompt_dataDictionary').text("恢复失败，请检查是否备份过数据字典文件");
		});
	}
	
	function recoverDatabase(){
		$.post("/ModelWorker/user/recoverDatabase.do",{},function(data){
			if(data == "true")
				$("#prompt_database").text("恢复成功");
			else
				$("#prompt_database").text("恢复失败，请检查是否备份过数据库文件");
		});
	}
	



</script>
</html>