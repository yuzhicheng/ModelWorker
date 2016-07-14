<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".submitbtn").click(function(){
			var titleOption = $("#title option");
			var selectedIndex;
			for(var i = 0; i < titleOption.length; i++){
				if(titleOption[i].selected == true) {
					selectedIndex = i;
				}
			}
			if(selectedIndex == 0) {
				alert("请选择劳模称号");
				return false;
			}
			
			var documentNo = $("input[name='documentNO']").val();
			
			if(documentNo != null){
				if(isNaN(documentNo)){
					alert('文件编号应由数字组成');
					return false;
				}
			}
		});			
		
	});
</script>
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
	h2{
		margin-left:235px;
	}
	.formsubmitdiv{
		margin-left:85px;
	}
	.submitbtn{
		width:90px;
		height:38px;
		margin:10px 280px 6px;
	}
	.inputtable{
		margin-left:90px;
	}
	td{
		margin:10px;
		padding:8px ;
		height:30px;
		font-size:1.2em;
		color:black;
	}
	.leftlabel{
		width:125px;
		
	}
	.inputbox{
		width:230px;
		height:30px;
		margin-left:0;
		margin-right:25px;
	}
	textarea{
		resize:none;
	}
	
</style>
</head>
<body>
    <div class="maincontainer">
    	<h2>荣誉证书情况</h2>
		<div class="fmcontainer">		
		<form action="/ModelWorker/personapply/${empty document?'saveDocument':'editDocument'}" method="post">
			<input type="hidden" name="id" value="${document.id }"/>
			<input type="hidden" name="username" value="${empty document?username:document.username }"></input>
        	<table class="inputtable" >
        		<tr>
        			<td class="leftlabel">劳模称号:</td>
        			<td>
        				<select id="title" class="inputbox" name="title">
	        				<option value="-1">--------请选择--------</option>
	        				<option ${document.title eq 0?"selected='selected'":"" } value="0">全国劳模</option>
	        				<option ${document.title eq 1?"selected='selected'":"" } value="1">四川省劳模全国五一劳动奖章 </option>
	        				<option ${document.title eq 2?"selected='selected'":"" } value="2">四川省五一劳动奖章</option>
	        				<option ${document.title eq 3?"selected='selected'":"" } value="3">其他荣誉称号</option>
        				</select>
        			</td>
        		</tr>
        		<tr>
        			<td class="leftlabel">证书内容:</td>
        			<td ><input class="inputbox" type="text" name="content" value="${document.content}"/></td>
        		</tr>
        		<tr>
            		<td class="leftlabel">发文单位:</td>
        			<td ><input class="inputbox" type="text" name="company" value="${document.company}"/></td>
                </tr>
                <tr>
            		<td class="leftlabel">证书授予时间:</td>
        			<td ><input class="inputbox" type="text" name="time1" value="${document.time1}"/></td>
                </tr>
                <tr>
            		<td class="leftlabel">文件名:</td>
        			<td ><input class="inputbox" type="text" name="documentname" value="${document.documentname}"/></td>
                </tr>
                <tr>
            		<td class="leftlabel">文件编号:</td>
        			<td ><input id="documentNO" class="inputbox" type="text" name="documentNO" value="${document.documentNO}"/></td>
                </tr>
                <tr>
            		<td class="leftlabel">发文时间:</td>
        			<td ><input  class="inputbox" type="text" name="time2" value="${document.time2}"/></td>
                </tr>
            </table>
            <div class="fmsubmitdiv">
            	<input type="submit" class="submitbtn" value="提交" />
                <!-- <input type="reset" name="cancel" class="btn" value="取 消" /> -->
            </div>
            </form>
        </div>
    </div>
</body>
</html>