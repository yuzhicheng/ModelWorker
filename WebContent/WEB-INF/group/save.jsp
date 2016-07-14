<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="/ModelWorker/static/css/reset.css" />
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.js"></script>
<style type="text/css">
	body{
		padding-left:10px;
		padding-top:10px;
		background-color:#f5f5f5;
		line-height:35px;
	}
	
	#tabs_div{
		width:464px;
		margin: 30px auto 0;
	}
	
	.tabs_header{
		display: block;
		margin:10px auto;
		
	}
	.tabs_header li{
		border: 1px solid #ccc;
		border-radius:4px 4px 4px 4px;
		cursor:pointer;
		display:inline;
		padding:10px 15px;
		margin:0 20px;
	}
	
	.on{
		background-color:#69B1F0;
	}
	
	.inputtable{
		color:black;
		line-height:30px;
		font-family:"lucida"," Grande","Verdana";
	}
	.inputtable td{
		margin:10px;
		padding:8px ;
		width:120px;
		height:30px;
		font-size:1.3em;
		color:black;
	}
	.fmcontainer{
		width:800px;
		margin:30px auto ;
	}
	.leftlabel{
		padding-right:10px;
		
	}
	.blank{
		margin-left:36px;
	}
	.inputbox{
		width:196px;
		height:25px;
		margin-left:0;
		margin-right:25px;
	}
	.fmsubmitbtn{
		margin:33px 210px;
	}
	.btn{
		width:90px;
		height:38px;
		margin:0 29px;
	}
	#show_info{
		color:red;
		line-height:20px;
		margin-left:267px;
	}
	#gn_span,#ad_span{
		margin-left:36px;
	}
</style>
<script type="text/javascript">
	
	function check(){
		$("#groupname").blur(function(){
			var state = true;
			var groupname = $.trim($("#groupname").attr("value"));			
			var url = "/ModelWorker/group/checkGroupnameDup";
			var args = {'groupname':groupname};
			$.get(url,args,function(data){
				if(data == "true"){
					state = false;
					$("#show_info").text("已存在该集体账号");
				} else {
					state = true;
					$("#show_info").text(" ");
				}
			});					
		});
		
		$("#group_sub_btn").click(function(){
			if(state){
				return true;
			} else {
				return false;
			}
		});
		
	} 
	
	$(document).ready(function(){
		
		$("#group_sub_btn").click(function(){	
			var groupname = $.trim($("#groupname").attr("value"));			
			var regxGname = /^[a-zA-Z0-9_-]{3,16}$/
			if(!regxGname.test(groupname)){
				$("#show_info").text("请输入字母数字下划线组成的3到16位集体账号");
				return false;
			}
			
			if("${empty group}"=='true'){
				var password = $.trim($("#password").attr("value"));
				var regxPwd = /^[a-zA-Z0-9_-]{6,18}$/;
				if(!regxPwd.test(password)){
					$("#show_info").text("密码不符合要求,请输入由数字字母组成的6到18位密码");
					return false;
				}
			}
			
				
			var name = $.trim($("#name").attr("value"));
			if(name.length == 0){
				$("#show_info").text("集体名不能为空");
				return false;
			}
			
			var belong = $.trim($("#belong").attr("value"));
			if(belong.length == 0){
				$("#show_info").text("隶属市州产业不能为空");
				return false;
			}
			
			var managerName = $.trim($("#managerName").attr("value"));
			if(managerName.length == 0){
				$("#show_info").text("负责人不能为空");
				return false;
			}
			
			var phone = $.trim($("#phone").attr("value"));
			if(phone.length == 0){
				$("#show_info").text("电话号码不能为空");
				return false;
			}
					
		});
		
		check();
	});
</script>

</head>
<body>
	<div class="maincontainer">
		<div class="fmcontainer">
		<c:set value="edit?page=${page}" var="edit" ></c:set>
		<form action="/ModelWorker/group/${empty group?'save':edit} " method="post">
			<input type="hidden" name="id" value="${group.id }"></input>
			<span id="show_info">&nbsp;</span>
        	<table class="inputtable" >
        		<tr>
        			<td class="leftlabel" >账<span id="gn_span"></span>号:</td>
            		<td><input id="groupname" class="inputbox" name="groupname" type="text" value="${group.groupname }"/></td>
            		<td class="leftlabel" >用户密码:</td>
            		<td><input id="password" class="inputbox" name="password" type="text"/></td>
        		</tr>
        		<tr>
        			<td class="leftlabel">集体名称:</td>
            		<td><input id="name" class="inputbox" name="name" type="text" value="${group.name }"/></td>
        			<td class="leftlabel">隶属市州产业:</td>
            		<td><input id="belong" class="inputbox" name="belong" type="text" value="${group.belong }"/></td>
        		</tr>
               <tr>
                    <td class="leftlabel">负责人姓名:</td>
                    <td><input id="managerName" class="inputbox" value="${group.managerName }" name="managerName" type="text" /></td>                   
                	<td class="leftlabel">电话号码:</td>
                    <td><input id="phone" class="inputbox" value="${group.phone }" name="phone" type="text" /></td>
                </tr>
                <tr>
                	 <td class="leftlabel">地<span id="ad_span"></span>址:</td>
                    <td><input id="address" class="inputbox" value="${group.address }" name="address" type="text" /></td>
                </tr>
            </table>
            <div class="fmsubmitbtn">
            	<input id="group_sub_btn" type="submit" class="btn" value="提 交" />
                <input type="reset" name="cancel" class="btn" value="取 消" />
            </div>
            </form>
        </div>
	</div>
</body>
</html>