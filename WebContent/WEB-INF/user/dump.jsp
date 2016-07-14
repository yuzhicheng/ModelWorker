<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据备份</title>
<style type="text/css">
	body{
		padding-left:10px;
		padding-top:10px;
		background-color:#f5f5f5;
		line-height:35px;
	}

</style>
</head>
<body >
	<form action="">
		<ul>
			<li>数据字典备份</li>
			<div style="color:red;font-size:15px">将数据字典备份到配置文件中所指定的目录下</div>
			<input type="button" style="width:111px;height:39px;" value="备份数据字典" onclick="dumpDataDictionary();"/>
			<span id="prompt_dataDictionary" style="color:red"></span>
			<li>数据库备份</li>
			<div style="color:red;font-size:15px">将数据库备份到配置文件指定的目录下</div>
			<input type="button" style="width:111px;height:39px;" value="备份数据库" onclick="dumpDatabase();"/>
			<span id="prompt_database" style="color:red"></span>
		</ul>
	</form>

</body>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>
<script type="text/javascript">
	function dumpDataDictionary(){
		$.post("/ModelWorker/user/dumpDataDictionary.do",{},function(data){
			if(data == "true")
				$("#prompt_dataDictionary").text("备份成功");
			else
				$('#prompt_dataDictionary').text("备份失败，请检查您的配置是否有问题");
		});
	}
	
	function dumpDatabase(){
		$.post("/ModelWorker/user/dumpDatabase.do",{},function(data){
			if(data == "true")
				$("#prompt_database").text("备份成功");
			else
				$("#prompt_database").text("备份失败,请检查您的配置是否有问题");
		});
	}


</script>

</html>