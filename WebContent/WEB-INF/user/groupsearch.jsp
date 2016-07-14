<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/common/common.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/common/fenye.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('div.tabs-con:first').show();

        $('.tabs-header li').click(function(){
            $(this).addClass('on').siblings('.on').removeClass('on');
            var target = $(this).attr('target');
            $(target).show().siblings('.tabs-con').hide();
         	//$(document).load("/ModelWorker/user/groupinfo");   
         
        });
        
        $('#select').change(function(){
			option_legal();
		});
        
        $('#select2').change(function(){
			option_legal2();
		});
        
        $("input[name='textvalue']").change(function(){
			item_legal();
		});
    });
    
    function  option_legal(){
    	var option = $("#select option:selected").val();
    	if(option == -1){
    		$("#prompt").text("请选择查询方式!");
    		return false;
    	}else{
    		$("#prompt").text("");
    		return true;
    	}
    }
    
    function  option_legal2(){
    	var option = $("#select2 option:selected").val();
    	if(option == -1){
    		$("#prompt2").text("请选择查询方式!");
    		return false;
    	}else{
    		$("#prompt2").text("");
    		return true;
    	}
    }
    
    function item_legal(){
    	if($("input[name='textvalue']").val() == ""||$("input[name='textvalue']").val() ==null){
			$("#prompt_item").text("查询项不能为空!");
			return false;
		}else{
			$("#prompt_item").text("");
			return true;
		}
    }
    
    function inquire(){
		if(option_legal() && item_legal()){
			document.getElementById('form').submit();
		}
    }
    
    function inquire2(){
		if(option_legal2()){
			document.getElementById('form2').submit();
		}
    }
    
   
</script>
<style type="text/css">
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
            width: 100%;
            height: 100px;
            border:1px solid #ccc;
            margin-top:10px;
            display: none;
        }
        .bottom {
            background-color: #BED393;
            font-size: 10px;
            margin-top: 7px;
            text-align:right;
            width:98%;    
        }
        #button {
          width: 100%;
          margin:10px 0px;
        }
        #form,#form2{
         margin-top:10px;
         margin-left:10px;
         margin-bottom: 20px;
         margin-right: 500px;
         height: 65px;
        }
       #input{
         width:120px;
         height:38px;
       }
       #select,#select2{
        width:120px;
        height:39px;
       }
       
          body {
	margin:0;
	padding:0;
	font-size:12px;
	background:#fff none repeat scroll 0 0 ;
   }
    body, h1, h2, h3, h4, h5, h6, ul, ol, li, form, p, dl, dt, dd, legend, table, th, td, fieldset {
    margin: 0;
    padding: 0;
   }
   .columnconn {
	background-color:#f5f5f5;
	height:35px;
	line-height:35px;
	border-bottom:1px solid #d7d7d7;
   }
   .rowhover{
	background-color:#E3E6EB;
   }
   .list_table{
	border-collapse: collapse;
    width: 98%;
	border-spacing: 0;
    clear: both;
	border:#D2D2D2 solid;
	border-width:1px 0 0 1px;
	text-align:center;
   }
   
   .list_table th {
    background: none repeat scroll 0 0 #EEEEEE;
    border: 1px solid #D2D2D2;
    height: 28px;
    overflow: hidden;
	text-align:center;
	font-weight: bold;
}
   .list_table th,.list_table td{
	padding: 5px 10px;
    word-wrap: break-word;
	border:solid #D2D2D2;
	border-width:0 1px 1px 0;
	vertical-align:middle;
   }
   .list_table .checkbox_first{
	text-align:center;
   }
</style>
</head>

<body class="nested"  style="background-color: #f5f5f5;">
    <div class="maincontainer">
    	  <div class="columnconn"><span class="columntitle">先进集体信息查询</span></div>  	  
    	  <div>
    	      <div id="button">
                   <ul class="tabs-header">
                      <li target="#div1" class="on">按基本信息查询</li>
                      <li target="#div2" >按荣誉称号查询</li>  
                   </ul>
                   <div id="div1" class="tabs-con">
                       <form id="form" action="/ModelWorker/user/groupsearch" method="post">
    	               <table>
    	                 <tr>
    	                   <td>
    	                     <select name="type" id="select">
                        	   <option value="-1">请选择查询方式</option>
                        	   <option value=-2>按注册时间查询</option>
                               <option value=-3>按集体名查询</option>              
                             </select>
    	                   </td>
    	                   <td><input type="text" name="textvalue" style="width:160px;height:33px;"></input></td>
    	                   <td><input type="button" value="查询" id="input" onclick="inquire();"></input></td>
    	                 </tr>
    	                 <tr>
    	                   <td><span id="prompt" style="color:red;font-size:15px"></span></td>
    	                   <td><span id="prompt_item" style="color:red;font-size:15px"></span></td>
    	                   <td></td>
    	                 </tr>
    	               </table>
    	               </form>
                   </div>
                   <div id="div2" class="tabs-con">
                       <form id="form2" action="/ModelWorker/user/groupsearch" method="post">
                       <table>
                       <tr>
                         <td>
                            <select name="type" id="select2">
                        	   <option value="-1">请选择荣誉称号</option>	   
                               <c:forEach items="${advancedGroup }" var="grouptitle" varStatus="status">
                                 <option value=${status.index}>${grouptitle}</option>
                               </c:forEach>          
                            </select>
                         </td>
                         <td><input type="button" value="查询" id="input" onclick="inquire2();"></input></td>
                       </tr>
                       <tr>
                         <td><span id="prompt2" style="color:red;font-size:15px"></span></td>
                         <td></td>
                       </tr>
                       </table>             
    	               </form>
                   </div> 
               </div>
    	   </div>
          <div  id="yzc" style="display:${result eq 'ok' ? 'block':'none'}" class="frame-submain">
    	  <h2 style="text-align:center;">先进集体信息查询结果</h2>
    	   <div>
    	   <table width="98%"><tr><td><span>统计信息：共${length}人</span></td>
    	   <td style="text-align: right;"><input type="button" value="保存" onclick="window.location.href='/ModelWorker/DataImprotAndExport/ExportAdvancedGroupDataBySearch?fileName=查询结果'"></input></td></tr>
    	   </table>   	 
    	  </div>
    	  <table id="senfe" border="1" cellpadding="0" cellspacing="0"  class="list_table" width="98%" style="text-align: center;align=center;">
             <thead>
                   <tr align="center" valign="middle">           
                    <th width="50px">序号</th><th>先进集体名</th><th>负责人姓名</th><th>称号</th><th>评定年份</th><th>所属州市</th><th>荣誉事迹</th>
                   </tr>
             </thead>
             <tbody id="group_one">
                  <c:forEach items="${groupinfo }" var="group" varStatus="status">
            	   <tr>
            	     <td>${status.index+1 }</td>
                     <td>${group.name}</td>
                     <td>${group.managerName}</td>
                     <td>${advancedGroup[group.title]}</td>
                     <td>${group.year }</td>
                     <td>${group.belong }</td>
                     <td>${group.achievement }</td>
                   </tr>
                 </c:forEach>

             </tbody>
         </table>
          
            <div class="bottom">
            <a href="#" onclick="page.firstPage();">首 页</a>|
            <a href="#" onclick="page.prePage();">上一页</a>|
            <a href="#" onclick="page.nextPage();">下一页</a>|
            <a href="#" onclick="page.lastPage();">末 页</a>
            <span id="divFood"></span>|第<input id="pageno" value="1" style="width:20px"/>页|
            <a href="#" onclick="page.aimPage();">跳转</a>
            </div>
      
         </div>
       </div>
</body>
</html>