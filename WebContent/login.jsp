<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请登录</title>
<link href="static/css/login.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .container {
             background-color: #962e09;
             height: 100%;
             width:100%;
         }
        #main {
            background-image: url(static/image/login/loginbg.jpg);
            position:relative;
            width: 1024px;
            height: 635px;
            margin: 0 auto;
        }
        .form_frame{
            width:300px;
            position:relative;
            left:384px;
            top:252px;

        }
        .form_frame li{
            margin:0 0 10px;
            font-size:1.3em;
            font-weight: bold;
        }
        .form_frame li span {
            margin-left: 18px;
        }

        ul{
            list-style: none;
        }

        .input_area{
            background-color: #FFF;
            border:1px solid #CCC;
            padding: 3px;
            border-radius: 3px;
        }
        #sub{
            margin-left: 32px;
            padding-left:40px ;
            margin-top: -4px;
        }
        .subbut {
            margin-left: -15px;
            margin-right:10px;
            padding-top:7px;
            width:70px;
            background:transparent url("static/image/login/commit.png") no-repeat scroll center top;
            border-radius: 4px;
        }
        .canbut{
            margin-right:10px;
            padding-top:7px;
            width:70px;
            background:transparent url("static/image/login/cancel.png") no-repeat scroll center top;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div id="main">
            <div class="form_frame">
            <div style="height:40px; margin-left:90px; line-height:40px; color:red;" id="prompt"></div>
                
               <form action="" method=post>
                    <ul>
	                    <li>
	                        <img src="static/image/login/arr.gif" />
	                        用户名：<input class="input_area" type="text" name="username" />
	                    </li>
	                    <li>
	                        <img src="static/image/login/arr.gif" />
	                        密<span></span>码：<input class="input_area" type="password" name="password" />
	                    </li>
	                    <li>
	                    	<div style="display:inline">
	                    	<img src="static/image/login/arr.gif"/>
	                    	验证码：<input class="input_area" type="text" name="identify" style="width:88px;height: 7px;padding-top: 17px;"/>
	                    	<img  id="identify"src="/ModelWorker/user/identify"   alt="看不清？点击更换"  style="height:26px; margin-top:0px;cursor: pointer; " onclick="this.src=this.src+'?'"/>
	                    	</div>
	                    </li>
	                    
	                    
	                    <li id="sub" style="margin-top:2px">
	                        <input class="subbut" type="button" value="" onclick="sendMessage();" />
	                        <input class="canbut" type="reset" value=""  style="margin-left:20px"/>
	                    </li>
                    </ul>
               	</form>
                    
                
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="static/js/common/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(window).keydown(function(event){
			if((event.keyCode||event.charCode)  == 13){
				sendMessage();
			}
		});
	});
	function sendMessage(){
		var username_val = $("input[name='username']").val();
		var password_val = $("input[name='password']").val();
		var identify_val = $("input[name='identify']").val();
		$.post("/ModelWorker/user/doLogin",{username:username_val,password:password_val,identify:identify_val},function (data){
			var json = jQuery.parseJSON(data);
			if(json.message == "pass"){
				document.location.href="/ModelWorker/Main_Frame.jsp";
			}else{
				$("#prompt").text(json.message);
				$("#identify").attr("src",$("#identify").attr("src") + "?");
			}
		});
	}


</script>
</html>