<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
body{
	width: 100%;
	height: 100%;
	text-align: center;
	padding: 0;
	margin: 0;
	position: absolute;
	background-color: rgba(224, 202, 154, 0.9);
}
.all{
	float: left;
  	 background-color: rgb(200, 165, 100);
  	border-style: ridge;
  	border-radius: 13px;
  	margin-top: 8%;
  	margin-left: 35%;
}
.span_info{
	text-align: center;
	color: red;
	margin-top: 3%;
}
#prompt{
	display: block;
 	height: 20px;
}
.btn{
	text-align: center;
	margin-left: auto;
	margin-right: auto;
}
#tr1,#tr2,#tr3{
	text-align: left;
}
#tr4{
	text-align: center;
}
</style>
</head>
<body>
<div class="all">
	<div class="span_info"><span id="prompt">&nbsp;</span></div>
	<form action="" method="post">
		<table style="text-align: right;line-height: 30px;" cellspacing="10">
			<tr>
				<td id="tr1">旧密码：</td>
				<td><input type="password" name="old_password" onblur="blur1();"  onfocus="focus1();"></td>
			</tr>
			<tr>
				<td id="tr2">新密码：</td>
				<td><input type="password" name="new_password1"></td>
			</tr>
			<tr>
				<td id="tr3">确认密码：</td>
				<td><input type="password" name="new_password2"></td>
			</tr>
			<tr>
				<td style="text-align: center;" class="btn">
					<input type="button" value="确认" onclick="send_message();" />
				</td>
				<td>
					<input type="reset">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>
<script type="text/javascript">
	function send_message(){
		var old_password = $("input[name='old_password']").val();		
		var new_password1=$("input[name='new_password1']").val();		
		var new_password2=$("input[name='new_password2']").val();
		if(new_password1!=new_password2){
			$("#prompt").text("两次密码不一致！");
		}else{
			
			$.post("/ModelWorker/User_Manage/Modify_Password",
					{newpassword1:new_password1,oldpassword:old_password},function(data){
						if(data=="true"){
							$("#prompt").text("密码修改成功 ");
							$("input[name='old_password']").val("");
							$("input[name='new_password1']").val("");
							$("input[name='new_password2']").val("");	
						}else if (date==null) {
							alert("用户名currentuser丢失！ ");
						}else {
							$("#prompt").text("您输入的密码有误！");
						}
												
					});
		}
	}
	function blur1(){
		var old_password = $("input[name='old_password']").val();	

		$.post("/ModelWorker/User_Manage/password",{oldpassword:old_password},function(data){
			if(data == "false"){
				$("#prompt").text("您输入的密码有误！ ");
			}
		});
	}
	function focus1(){
		$("#prompt").text("");
	}
</script>
</html>