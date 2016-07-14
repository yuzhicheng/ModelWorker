<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据字典设置</title>

<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('div.tabs-con:first').show();

        $('.tabs-header li').click(function(){
            $(this).addClass('on').siblings('.on').removeClass('on');
            var target = $(this).attr('target');
            $(target).show().siblings('.tabs-con').hide();
            if($(this).attr('target') == '#div1')
            	location.reload();
        });
	
		$('#option').change(function(){
			option_legal();
		});
			
		$("input[name='item']").change(function(){
			item_legal();
		});
		
			
		
    });
    
    function  option_legal(){
    	var option = $("#option option:selected").val();
    	if(option == -1){
    		$("#prompt").text("请选择数据字典类型");
    		return false;
    	}else{
    		$("#prompt").text("");
    		return true;
    	}
    }
    function item_legal(){
    	if($("input[name='item']").val() == ""||$("input[name='item']").val() ==null){
			$("#prompt_item").text("数据字典项不能为空");
			return false;
		}else{
			$("#prompt_item").text("");
			return true;
		}
    }
    function add_item(){
    		if(option_legal() && item_legal()){
    			$.post("/ModelWorker/user/addDataDictionaryItem",{option:$("#option option:selected").val(),item:$("input[name='item']").val()},function(data){
    				if(data == "true"){
    					$("#result").text("添加成功");
    				}else{
    					$("#result").text("添加失败，条目不能完全为空格");
    				}
    			})
    		}
    }
</script>
</head>
<style type="text/css">
	.list_table{
		border-collapse: collapse;
    	width:95%;
    	line-height:35px;
    	border: solid #D2D2D2;
  		border-width: 0 1px 1px 0;
  		font-family: 'lucida',' Grande','Verdana';
	}
	.list_table td{
	 	height:30px;
	 	line-height:30px;
	 	text-align:center;
	}
	.tabs-header li.on {
            background-color: #e3e3e3;
    }
    .tabs-con {
            width: 90%;
            height: 90%;
            border:1px solid #ccc;
            margin-top:10px;
            display: none;
    }
    .tabs-header li {
            border: 1px solid #ccc;
            border-radius: 4px 4px 0 0;
            cursor: pointer;
            display: inline;
            padding: 10px 15px;
    }
    .tabs-header {
            list-style: none;
            padding: 0;
            margin:5px 0 0 0;
    }
    .add_item td{
    	text-align:left;
    	padding-top:5px;
    }
     
      
    

</style>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>

<body style="background-color:#f5f5f5; padding-top:5px">
	<ul class="tabs-header">
                    <li target="#div1" class="on">数据字典显示</li>
                    <li target="#div2">数据字典添加</li>
    </ul>
    <div id="div1" class="tabs-con">
		<div style="border-bottom: 1px solid #d7d7d7;line-height:35px;font-weight: bold;">数据字典内容：</div>
		<table class="list_table" align=center cellPadding=1 align=center  border="1">
			<tr>
				<th>隶属公会</th><th>先进个人荣誉称号</th><th>先进集体荣誉称号</th><th>享受待遇</th><th>个人状态</th><th>认定状态</th><th>民族</th>
			</tr>
			
			<c:forEach var="index" begin="0" end="${biggest - 1}" step="1">
				<tr>
				<td>${ U_L > index ?  union[index] : ""}</td>
				<td>${ AP_L > index ? advancedPerson[index] : ""}</td>
				<td>${ AC_L > index ? advancedCollective[index] : ""}</td>
				<td>${ TM_L > index ? treatment[index] : ""}</td>
				<td>${ PS_L > index ? personStatus[index] : ""}</td>
				<td>${ IS_L > index ? identifyStatus[index] : ""}</td>
				<td>${ NA_L > index ? nation[index] : "" }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="div2" class="tabs-con" >
		<div style="margin-left:5px">
			<iframe name=clear_iframe width=0 height=0></iframe>
			<ul>
			<li>更新数据字典</li>
			<form action="/ModelWorker/user/upDateDataDictionary" method= "post" target=clear_iframe onsubmit="$('#prompt_sucess').text('更新成功');">
				<input type="submit" value="更新数据字典" style="width:111px;height:39px;margin-top:10px;margin-bottom: 10px;"/>
				<div id="prompt_sucess" style="display:inline; color:red;font-size:15px"></div>
			</form>
			<li>向数据字典中添加一项</li>
			<p style="color:red; font-size:15px">警告：因为数据依赖性，添加的数据字典项将无法被删除，请慎重考虑</p>
			<form action="" method="post" target=clear_iframe >
				<table class="add_item">
					<tr>
						<td>请选择数据字典类型：</td>
						<td>
							<select id="option" name="type">
		                        <option value="-1">---请选择---</option>
		                        <option value="0">先进集体荣誉称号</option>
		                        <option value="1">先进个人荣誉称号</option>
		                        <option value="2">隶属公会</option>
		                        <option value="3">享受待遇</option>
		                        <option value="4">个人状态</option>
		                        <option value="5">认定状态</option>
		                        <option value="6">民族</option>
                    		</select>
						</td>
						<td><span id="prompt" style="color:red;font-size:15px"></span></td>
					</tr>
					<tr>
						<td>请填写新条目 ：</td>
						<td><input type="text" name="item"/></td>
						<td><span id="prompt_item" style="color:red;font-size:15px"></span></td>
					</tr>
					<tr>
						<td><input type="button" value="添 加 条 目" style="width:111px;height:39px;" onclick="add_item();"/></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><span id="result" style="color:blue;font-size:15px"></span></td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</form>
			</ul>
		</div>
	</div>
</body>
</html>