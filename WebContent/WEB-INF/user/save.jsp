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
		width:100px;
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
	}
	.fmsubmitbtn{
		margin:27px 172px;
	}
	.btn{
		width:90px;
		height:38px;
		margin:0 29px;
	}
	#show_info{
		color:red;
		line-height:20px;
		margin-left:158px;
	}
</style>
<script type="text/javascript">		
	 function check(){
		var usernameState = true;
		var idCardState = true;
		
		
		$("#username").blur(function(){
			var username = $.trim($("#username").attr("value"));
			var url = "/ModelWorker/user/checkUsernameDup";
			var args = {'username':username};
			$.get(url,args,function(data){
				if(data == "true"){
					usernameState = false;
					$("#show_info").text("已存在该用户账号");
				} else {
					usernameState = true;
					$("#show_info").text(" ");
				}
			});						
		});
		
		$("#idCard").blur(function(){
			var idCard = $.trim($("#idCard").attr("value"));
			var url = "/ModelWorker/user/checkIdCardDup";		
			var args = {'idCard':idCard};
			$.get(url,args,function(data){
				if(data == "true"){
					idCardState = false;
					$("#show_info").text("身份证号已存在");
				} else {
					idCardState = true;
					$("#show_info").text(" ");
				}
			});				
		});	
		
		$("#user_sub_btn").click(function(){
			if(usernameState && idCardState) {
				return true;
			} else {
				return false;
			}
		});
	} 

	$(document).ready(function(){
		$("#user_sub_btn").click(function(){	
			var username = $.trim($("#username").attr("value"));
			var regxUname = /^[a-zA-Z0-9_-]{3,16}$/
			if(!regxUname.test(username)){
				$("#show_info").text("请输入字母数字下划线组成的3到16位用户名");
				return false;
			}
			if("${empty user}"=='true'){
				var password = $.trim($("#password").attr("value"));
				var regxPwd = /^[a-zA-Z0-9_-]{6,18}$/;
				if(!regxPwd.test(password)){
					$("#show_info").text("密码不符合要求,请输入由数字字母组成的6到18位密码");
					return false;
				}
			}
			
				
			var permSelect = $("#permission option");
			var permIndex;
			for(var i = 0; i < permSelect.length; i++){
				if(permSelect[i].selected == true){
					permIndex = i;
				}
			}
			if(permIndex == 0){
				$("#show_info").text("请选择权限");
				return false;
			}
				
			var name = $.trim($("#name").attr("value"));
			if(name.length == 0){
				$("#show_info").text("用户姓名不能为空");
				return false;
			}
			
			var natSelect = $("#nation option");
			var natIndex;
			for(var i = 0; i < natSelect.length; i++){
				if(natSelect[i].selected == true){
					natIndex = i;
				}
			}
			if(natIndex == 0){
				$("#show_info").text("请选择民族");
				return false;
			}
			
			var birthPlace = $.trim($("input[name='birthPlace']").attr("value"));
			if(birthPlace.length == 0){
				$("#show_info").text("籍贯不能为空");
				return false;
			}
			
			var unionSelect = $("#union option");
			var unionIndex;
			for(var i = 0; i < unionSelect.length; i++){
				if(unionSelect[i].selected == true){
					unionIndex = i;
				}
			}
			if(unionIndex == 0){
				$("#show_info").text("请选择所属工会");
				return false;
			}
			
			
			var idCard = $.trim($("#idCard").attr("value"));
			if(idCard.length != 18) {
				alert('1111111111111111111111111');
				$('#show_info').text('请输入正确的身份证号码');
				return false;
			} 
			/* if(!IdentityCodeValid(idCard)){
				$('#show_info').text('请输入正确的身份证号码');
				return false;
			}; */
				
			var phone = $.trim($("#phone").attr("value"));
			if(phone.length == 0){
				$("#show_info").text("电话号码不能为空");
				return false;
			}
			
			var email = $.trim($("#email").attr("value"));
			var regxEmail = /^([a-zA-Z0-9_]+)@([0-9a-zA-Z]+)\.([a-z]{2,6})$/;			
			if(!regxEmail.test(email)){
				$("#show_info").text("请输入正确的邮箱地址");
				return false;
			}			
		});
		
		check();
		
	});
	
	//身份证号合法性验证 
	//支持15位和18位身份证号
	//支持地址编码、出生日期、校验位验证
    /* function IdentityCodeValid(code) { 
        var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",
        		23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",
        		37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",
        		50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",
        		63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
        var tip = "";
        var pass= true;
        
        if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
            tip = "身份证号格式错误";
            pass = false;
        }
        
       else if(!city[code.substr(0,2)]){
            tip = "地址编码错误";
            pass = false;
        }
        else{
            //18位身份证需要验证最后一位校验位
            if(code.length == 18){
                code = code.split('');
                //∑(ai×Wi)(mod 11)
                //加权因子
                var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                //校验位
                var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                var sum = 0;
                var ai = 0;
                var wi = 0;
                for (var i = 0; i < 17; i++)
                {
                    ai = code[i];
                    wi = factor[i];
                    sum += ai * wi;
                }
                var last = parity[sum % 11];
                if(parity[sum % 11] != code[17]){
                    tip = "校验位错误";
                    pass =false;
                }
            }
        }
        if(!pass) alert(tip); 
        return pass;
    } */
	
</script>
</head>
<body>
    <div class="maincontainer">
		<div class="fmcontainer">
		<c:set value="edit?page=${page}" var="edit" ></c:set>
		<form action="/ModelWorker/user/${empty user?'save':edit} " method="post">
			<input type="hidden" name="id" value="${user.id }"></input>
			<span id="show_info">&nbsp;</span>
        	<table class="inputtable" >
        		<tr>
        			<td class="leftlabel" >用户账号:</td>
            		<td><input id="username" class="inputbox" name="username" type="text" value="${user.username }"/></td>
            		<td class="leftlabel" >用户密码:</td>
            		<td><input id="password" class="inputbox" name="password" type="text"/></td>
        		</tr>
        		<tr>
        			<td class="leftlabel">用户权限:</td>
        			<td>
        				<select id="permission" class="inputbox" name="permission">
	        				<option value="3">---请选择---</option>
	        				<option ${user.permission eq 0?"selected='selected'":"" } value="0">系统管理员权限</option>
	        				<option ${user.permission eq 1?"selected='selected'":"" } value="1">审核权限</option>
	        				<option ${user.permission eq 2?"selected='selected'":"" } value="2">普通用户</option>
        				</select>
        			</td>
        			<td class="leftlabel">姓<span class="blank"></span>名:</td>
            		<td><input id="name" class="inputbox" name="name" type="text" value="${user.name }"/></td>
        		</tr>
            	<tr>          		 
            		<td  class="leftlabel">民<span class="blank"></span>族:</td> 
                    <td>
                    	<select id="nation" class="inputbox" name="nation"> 
	        				<option value="">---请选择---</option>
	        				<c:forEach items="${nationList}" var="nation" varStatus="status"> 
	        					<option ${user.nation eq status.index?"selected='selected'":"" }value="${status.index}">${nation}</option>
	        				</c:forEach>
	        				<%-- <option ${user.nation eq 0?"selected='selected'":"" } value="0">汉族</option>
	        				<option ${user.nation eq 1?"selected='selected'":"" } value="1">壮族</option>
	        				<option ${user.nation eq 2?"selected='selected'":"" } value="2">满族</option>
	        				<option ${user.nation eq 3?"selected='selected'":"" } value="3">回族</option>
	        				<option ${user.nation eq 4?"selected='selected'":"" } value="4">苗族</option>
	        				<option ${user.nation eq 5?"selected='selected'":"" } value="5">维吾尔族</option>
	        				<option ${user.nation eq 6?"selected='selected'":"" } value="6">土家族</option>
	        				<option ${user.nation eq 7?"selected='selected'":"" } value="7">彝族</option>
	        				<option ${user.nation eq 8?"selected='selected'":"" } value="8">蒙古族</option>
	        				<option ${user.nation eq 9?"selected='selected'":"" } value="9">藏族</option>
	        				<option ${user.nation eq 10?"selected='selected'":"" } value="10">其他</option> --%>
        				</select>
                    </td>
                    <td class="leftlabel">性<span class="blank"></span>别:</td> 
                    <td>
                    	<c:forEach items="${sexList}" var="sex" varStatus="status"> 
        					<input type="radio"	name="sex" ${empty user || user.sex eq status.index? "checked='checked'":"" } value="${status.index }" /><label>${sex }</label>
        				</c:forEach>

                    </td>
               </tr>
               <tr>
               		<td class="leftlabel">所属公会:</td>
            		<td>
        				<select id="union" class="inputbox" name="unionName"> 
	        				<option value="">---请选择---</option>
	        				<c:forEach items="${unionList}" var="union" varStatus="status"> 
	        					<option ${user.nation eq status.index?"selected='selected'":"" }value="${status.index}">${union}</option>
	        				</c:forEach>
	        				<%-- <option ${user.unionName eq 0?"selected='selected'":"" } value="0">省总工会</option>
	        				<option ${user.unionName eq 1?"selected='selected'":"" } value="1">市州总工会</option>
	        				<option ${user.unionName eq 2?"selected='selected'":"" } value="2">省产业局</option>
	        				<option ${user.unionName eq 3?"selected='selected'":"" } value="3">企业集团工会</option> --%>
        				</select>
        			</td>
                    <td class="leftlabel">身份证号:</td>
                    <td><input id="idCard" class="inputbox" value="${user.idCard }" name="idCard" type="text" /></td>                   
                </tr>
                <tr>
                    <td class="leftlabel">电话号码:</td>
                    <td><input id="phone" class="inputbox" value="${user.phone }" name="phone" type="text" /></td>
                    <td class="leftlabel">邮<span class="blank"></span>箱:</td>
                    <td><input id="email" class="inputbox" value="${user.email }" name="email" type="text" /></td>
                </tr>
                <tr>
                	<td class="leftlabel">籍<span class="blank"></span>贯:</td>
                	<td><input class="inputbox" value="${user.birthPlace }" name="birthPlace" type="text" /></td>
                </tr>
            </table>
            <div class="fmsubmitbtn">
            	<input id="user_sub_btn" type="submit" class="btn" value="提 交" />
                <input type="reset" name="cancel" class="btn" value="取 消" />
            </div>
            </form>
        </div>
    </div>
</body>
</html>