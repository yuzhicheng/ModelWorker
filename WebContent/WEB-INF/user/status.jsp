<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>状态更改</title>
</head>
<style type="text/css">
	body{
		padding-left:10px;
		padding-top:10px;
		background-color:#f5f5f5;
		line-height:35px;
	}
	input[type="button"]{
		margin-top:5px;
		margin-bottom: 5px;
	
	}
</style>
<body>
	<ul>
			
				<div style="font-size:16px">当前状态为：<span id="nowStatus">${statusName }</span></div>
				<div style="font-size:15px;color:red;padding-left:10px">注：只有在申请期间可以进行申请，只有在审核期间才能进行审核，当审核期结束后，将对于所有没有进行操作的审核，认定为审核失败 </div>
			
		<li>当前状态更改：</li>
			<div style="padding-left:20px;">
				<input type="button" value="${status[0] }" style="width:111px;height:39px;" onclick="setStatus(0);"/><br/>
				<input type="button" value="${status[1] }" style="width:111px;height:39px;" onclick="setStatus(1);"/><br/>
				<input type="button" value="${status[2] }" style="width:111px;height:39px;" onclick="setStatus(2);"/><br/>
				<input type="button" value="${status[3] }" style="width:111px;height:39px;" onclick="setStatus(3);"/><br/>
			</div>
			<div id="prompt" style="font-size:15px;color:blue"></div>
			<div style="height:1px;width:1000px; background:black; margin-top:10px;margin-bottom:10px;"></div>
		<li>清空申请数据库</li>
			<div style="padding-left:10px;font-size:15px;color:red">请在审核结束后合适的时间后进行此操作，保证在此区间内，个人可以通过信息查询查询到审核是否通过</div>
			<div style="padding-left:20px;">
				<input type="button" value="清空申请信息" style="width:111px;height:39px;" onclick="clearDatabase();"/>
			<div id="clear_prompt" style="font-size:15px;color:blue"></div>
			</div>
			
	</ul>
	
</body>
	<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>
	<script type="text/javascript">
	 function setStatus(type){
		 $.post("/ModelWorker/user/setStatus",{type:type});
		 $("#prompt").text("修改成功");
		 var nowStatus;
		 switch(type){
		 	case 0: nowStatus = "${status[0]}";break;
			case 1: nowStatus = "${status[1]}";break;
			case 2: nowStatus = "${status[2]}";break;
			case 3: nowStatus = "${status[3]}";break;
		 }
		 $("#nowStatus").text(nowStatus);
	 }
	function clearDatabase(){
		$.post("/ModelWorker/user/clearDatabase");
		$("#clear_prompt").text("清除成功");
	}
	
	</script>
</html>