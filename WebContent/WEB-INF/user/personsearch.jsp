<%@page contentType="text/html; charset=UTF-8" language="java" import="java.util.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/ModelWorker/static/css/global.css" rel="stylesheet" type="text/css" /> 
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
        });
        
        $('#select').change(function(){
			option_legal();
		});
        
        $("input[name='persontextvalue']").change(function(){
			item_legal();
		});
        
        $('#select2').change(function(){
			option_legal2();
		});
        
        $('#select3').change(function(){
			option_legal3();
		});
        $('#select4').change(function(){
			option_legal4();
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
    
    function  option_legal3(){
    	var option = $("#select3 option:selected").val();
    	if(option == -1){
    		$("#prompt3").text("请选择查询方式!");
    		return false;
    	}else{
    		$("#prompt3").text("");
    		return true;
    	}
    }
    
    function  option_legal4(){
    	var option = $("#select4 option:selected").val();
    	if(option == -1){
    		$("#prompt4").text("请选择查询方式!");
    		return false;
    	}else{
    		$("#prompt4").text("");
    		return true;
    	}
    }
    
    function item_legal(){
    	if($("input[name='persontextvalue']").val() == ""||$("input[name='persontextvalue']").val() ==null){
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
    function inquire3(){
		if(option_legal3()){
			document.getElementById('form3').submit();
		}
    }
    function inquire4(){
		if(option_legal4()){
			document.getElementById('form4').submit();
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
        #form,#form2,#form3,#form4{
         margin-top:10px;
         margin-left:10px;
         margin-bottom: 20px;
         margin-right: 600px;
         height: 65px;
        }
       #input{
         width:120px;
         height:38px;
       }
       #select,#select2,#select3,#select4{
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
    	  <div class="columnconn"><span class="columntitle">先进个人信息查询</span></div>  	  
    	  <div><!-- 表单div -->
    	      <div id="button">
                   <ul class="tabs-header">
                    <li target="#div1" class="on">按基本信息查询</li>
                    <li target="#div2">按荣誉称号查询</li>
                    <li target="#div3">按个人状态查询</li>
                    <li target="#div4">按认定状态查询</li> 
                   </ul>
                   <div id="div1" class="tabs-con">
                       <form id="form" action="/ModelWorker/user/personsearch" method="post">
    	               <table>
    	                <tr>
    	                 <td>
    	                    <select name="persontype" id="select">
                        	<option value="-1">请选择查询方式</option></br>
                        	<option value=-2>按身份证号查询</option>
                            <option value=-3>按姓名查询</option>            
                            </select>
    	                 </td>
    	                 <td>
    	                    <input type="text" name="persontextvalue" style="width:160px;height:33px;"></input>
    	                 </td>
    	                 <td>
    	                    <input type="button" value="查询" id="input" onclick="inquire();"></input>
    	                 </td>
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
                       <form id="form2" action="/ModelWorker/user/personsearch" method="post">
                       <table>
                         <tr>
                           <td>
                            <select name="persontype" id="select2">
                        	  <option value="-1">请选择荣誉称号</option>
                              <c:forEach items="${advancePerson}" var="persontitle" varStatus="status">
                                 <option value=${status.index}>${persontitle}</option>
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
                   <div id="div3" class="tabs-con">
                       <form id="form3" action="/ModelWorker/user/personsearch" method="post">
    	               <table>
    	                 <tr>
    	                   <td>
    	                    <select name="persontype" id="select3">
                        	  <option value="-1">请选择个人状态</option>
                        	     <c:forEach items="${personstate}" var="state" varStatus="status">
                                 <option value=${status.index+1000}>${state}</option>
                              </c:forEach>       
                            </select>
    	                   </td>
    	                   <td>
    	                       <input type="button" value="查询" id="input" onclick="inquire3();"></input>
    	                   </td>
    	                 </tr>
    	                 <tr>
    	                   <td><span id="prompt3" style="color:red;font-size:15px"></span></td>
    	                   <td></td>
    	                 </tr>
    	               </table>   
    	               </form>
                    </div>
                    <div id="div4" class="tabs-con">
                      <form id="form4" action="/ModelWorker/user/personsearch" method="post">
                      <table>
                        <tr>
                          <td>
                            <select name="persontype" id="select4">
                        	  <option value="-1">请选择认定状态</option></br>
                        	   <c:forEach items="${identitystate}" var="identity" varStatus="status">
                                 <option value=${status.index+10000}>${identity}</option>
                              </c:forEach>     
                            </select>
                          </td>
                          <td><input type="button" value="查询" id="input" onclick="inquire4();"></input></td>
                        </tr>
                        <tr>
                           <td><span id="prompt4" style="color:red;font-size:15px"></span></td>
                           <td></td>
                        </tr>
                      </table>
    	              </form>
                    </div>
               </div>
    	   </div>
          <div  id="yzc" style="display:${result eq 'ok' ? 'block':'none'}" class="frame-submain">
    	  <h2 style="text-align:center;">先进个人信息查询结果</h2>
    	  <div>
    	   <table width="98%"><tr><td><span>统计信息：共${length}人</span></td>
    	   <td style="text-align: right;"><input type="button" value="保存" onclick="window.location.href='/ModelWorker/DataImprotAndExport/ExportAdvancedPersonDataBySearch?fileName=查询结果'"></input></td></tr>
    	   </table>   	 
    	  </div>
    	  <table id="senfe" class="list_table" width="98%" style="text-align: center;align=center;">
             <thead>
                   <tr align="center" valign="middle">
                      
                      <th width="50px">序号</th><th>姓名</th><th>性别</th><th>民族</th><th>称号</th><th>享受待遇</th><th>突出事迹</th><th>个人状态</th><th>评定年份</th>
                         <c:if test="${currentUser != null && (currentUser.isPerson && currentUser.personUser.permission == 0) }">
                      <th>状态变更</th>
                        </c:if>
                   </tr>
                   
             </thead>
             <tbody id="group_one">
                  <c:forEach items="${personList }" var="person" varStatus="status">
            	   <tr>          	    
                      <td>${status.index+1 }</td>
                      <td>${userInfoList[status.index].name }</td>
                      <td>${personsex[userInfoList[status.index].sex]}</td>
                      <td>${personnation[userInfoList[status.index].nation]}</td>
                      <td>${advancePerson[person.title]}</td>
                      <td>${personTreatment[person.treatment]}</td>
                      <td>${person.achievement }</td>
                      <td>${personstate[userInfoList[status.index].personcondition]}</td>
                      <td>${person.year }</td>
                     <c:if test="${currentUser != null && (currentUser.isPerson && currentUser.personUser.permission == 0) }">
                      <td><a href="/ModelWorker/StatusManage/JumpToChangePage?ap_id=${person.id} ">状态变更</a></td>
                     </c:if>	
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