<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/ModelWorker/static/css/reset.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.js"></script>
<script type="text/javascript" >
	$(document).ready(function(){
	    selectListTab();
		selectInqueryMethod();
		
	});
	
	function selectListTab(){
		$('div.tabs-con:first').show();
		
	    $('.tabs-header li').click(function(){
	        $(this).addClass('on').siblings('.on').removeClass('on');
	        var target = $(this).attr('target');
	        $(target).show().siblings('.tabs-con').hide();
	    });
	}
	function selectInqueryMethod(){
		$("#per_select").hide();
		$("select").blur(function(){
			var value = $("select option:selected").val();
			if (value == 3)
				$("#inquery_input").hide().siblings("#per_select").show();
			else
				$("#per_select").hide().siblings("#inquery_input").show();
		});
	}
	function personBtnClick(){
		var method = $("#inquery_method option:selected").val();
		var inqueryInfo;
		if(method == -1) {
		} else if(method == 3) {
			inqueryInfo = $("#per_select option:selected").val();
		} else {
			inqueryInfo = $("#inquery_input").val();
		}
		var url = "/ModelWorker/user/list2";
		var args = {'method':method,'inqueryInfo':inqueryInfo};
		$("#person_list").load(url,args);
	}
	function groupBtnClick(){
		var method = $("select:visible option:selected").val();
		var inqueryInfo = $("input:visible").val();
		
		var url = "/ModelWorker/group/list2";
		var args = {'method':method,'inqueryInfo':inqueryInfo};
		$("#group_list").load(url,args);
	}
</script>
<style type="text/css">
	 body{
	 	background-color:#f5f5f5;
	 }
	 .tabs-header {
          list-style: none;
          padding: 0;
          margin:0;
      }

      .tabs-header li {
          border: 1px solid #ccc;
          border-radius: 4px 4px 0 0;
          cursor: pointer;
          display: inline;
          padding: 10px 15px;
      }
      .tabs-header li.on {
          background-color: #e3e3e3;
      }

      .tabs-con {
          width: 400px;
          height: 100px;
          border:1px solid #ccc;
          margin-top:10px;
          display: none;
      }
      .maincontainer{
      		width:1100px;
      		height:480px;
      }
      #list_select{
      		margin-top:20px;
      }
      .inquery_div{
      		border:1px solid #ccc;
      		width:1100px;
      		height:100px;
      }
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="maincontainer">
		<div id="list_select">
			<ul class="tabs-header">
			    <li target="#person_div" class="on">个人用户列表</li>
			    <li target="#group_div">集体用户列表</li>
			</ul>
		</div>
		
		<div id="person_div" class="tabs-con">
			<div class="inquery_div">
				<select id="inquery_method" >
					<option class="default" value="-1">---请选择查询方式---</option>
	  				<option  value="0">按用户姓名</option>
	  				<option  value="1">按用户身份证</option>
	  				<option  value="2">按用户账号</option>
	  				<option  value="3">按用户权限</option>
				</select>
				<input id="inquery_input" type="text" name="personInqueryInfo"  />
	   			<select id="per_select" name="permission">
	  				<option  value="-1">---请选择权限---</option>
	  				<option  value="0">系统管理员权限</option>
	  				<option  value="1">录入权限</option>
	  				<option  value="2">信息查询权限</option>
	      		</select>  		
	   			<input id="personinquerybtn" type="button" onclick=personBtnClick() value="查询" />
   			</div>
			<div id="person_list">
				
			</div>
	        	
		</div>
		
		<div id="group_div" class="tabs-con">
			<div class="inquery_div">
				<select class="selectbox" name="method">
	   				<option class="default" value="-1">---请选择查询方式---</option>
	   				<option value="0">按集体用户账号</option>
	   				<option value="1">按集体名</option>
	   			</select>
	   			<input class="inputbox" type="text" name="groupInqueryInfo"/>
	   			<input id="groupinquerybtn" type="button" onclick=groupBtnClick() value="查询" />
   			</div>
			<div id="group_list">
				
			</div>
		</div>
	
	</div>
</body>
</html>