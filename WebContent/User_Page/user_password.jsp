<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
div{
	float: left;
  	background-color: rgb(179, 231, 225);
  	border-style: ridge;
  	border-radius: 13px;
  	margin-top: 15%;
  	margin-left: 35%;
}
</style>
</head>
<body>
<div>
	<form action="" method="post">
		<table style="text-align: right;line-height: 30px;">
			<tr></tr>
			<tr>
				<td>旧密码：</td>
				<td><input type="password"></td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td><input type="password"></td>
			</tr>
			<tr>
				<td>确认新密码：</td>
				<td><input type="password"></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: left;">
					<input type="submit">
					<input type="reset">
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>