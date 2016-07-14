<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/ModelWorker/static/css/Main_Frame.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/common/common.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/common/jquery.min.js"></script>
<script type="text/javascript" src="/ModelWorker/static/js/common/fenye.js"></script>
<script type="text/javascript">
    $(document).ready(function(){ 
    	turn();
        $('#select').change(function(){
			option_legal();
		});        
        $("input[name='year']").change(function(){
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
    function item_legal(){
    	if($("input[name='year']").val() == ""||$("input[name='year']").val() ==null){
			$("#prompt_item").text("查询项不能为空,输入年份");
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
    function turn(){
 	   $("#turn_a").click(function(){
 		   value=$("#turn").val();
 		   this.href = '/ModelWorker/user/allprevioussearch?option=${option}&year=${year}&page='+value;
 		   return true;
 	   });
    };  
   
</script>
<script type="text/javascript" language="javascript">
   
</script>
<style type="text/css">
        .tabs-con {
            width: 100%;
            height: 100px;
            border:1px solid #ccc;
            margin-top:10px;
        }
        .bottom {
            background-color: #BED393;
            font-size: 10px;
            margin-top: 7px;
            text-align:right;
            width:98%;    
        }
        #button {
          width: 1040px;
          margin:10px 0px;
        }
        #form{
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
       #select{
        width:120px;
        height:39px;
       }
</style>
</head>
<body class="nested"  style="background-color: #f5f5f5;">
    <div class="maincontainer">
    	  <div class="columnconn"><span class="columntitle">历届劳模信息查询</span></div>  	  
                   <div id="div1" class="tabs-con">
                       <form id="form" action="/ModelWorker/user/allprevioussearch" method="post">
    	               <table>
    	                 <tr>
    	                   <td>
    	                     <select name="option" id="select">
                        	   <option value="-1">请选择查询方式</option>
                        	   <option value="0">按先进个人查询</option>
                               <option value="1">按先进集体查询</option>              
                             </select>
    	                   </td>
    	                   <td><input type="text" name="year" style="width:160px;height:33px;"></input></td>
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
          <div  id="yzc" style="display:${result eq 'ok' ? 'block':'none'}" class="frame-submain">
    	  <h2 style="text-align:center;">历届劳模信息查询结果</h2>
    	  <div style="display:${tablevalue eq 'group' ? 'block':'none'}">
    	  <table id="senfe" border="1" cellpadding="0" cellspacing="0"  class="list_table" width="98%" 
    	   style="text-align: center;align:center;">
             <thead>
                   <tr align="center" valign="middle">           
                    <th width="50px">序号</th><th>先进集体名</th><th>称号</th><th>评定年份</th><th>荣誉事迹</th>     
                   </tr>
             </thead>
             <tbody id="group_one">
                  <c:forEach items="${groupList }" var="group" varStatus="status">
            	   <tr>
            	     <td>${status.index+1 }</td>
                     <td>${group.name}</td>
                     <td>${advancedGroup[group.title]}</td>
                     <td>${group.year }</td>
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
         <div style="display:${tablevalue eq 'person' ? 'block':'none'}">
         <table id="person" border="1" cellpadding="0" cellspacing="0"  class="list_table" width="98%" 
          style="text-align: center;align:center;">
             <thead>
                <tr align="center" valign="middle">        
                  <th width="50px">序号</th><th>姓名</th><th>称号</th><th>享受待遇</th><th>突出事迹</th><th>个人状态</th><th>评定年份</th>
                </tr>
             </thead>
             <tbody id="person_one">
                  <c:forEach items="${userListPart}" var="person" varStatus="status">
            	   <tr>
            	    
                      <td>${status.index+1 }</td>
                      <td>${person.name }</td>
                      <td>${advancePerson[person.title]}</td>
                      <td>${personTreatment[person.treatment]}</td>
                      <td>${person.achievement }</td>
                      <td>${personstate[person.state] }</td>
                      <td>${person.year }</td>
                   </tr>
                 </c:forEach>

             </tbody>
         </table>
            
            <div class="bottom">
             <a class="firstpage" href="/ModelWorker/user/allprevioussearch?option=${option}&year=${year}">首页</a>|
             <a class="previouspage" href="/ModelWorker/user/allprevioussearch?page=${page-1}&option=${option}&year=${year}">上一页</a>|
	         <a class="nextpage" href="/ModelWorker/user/allprevioussearch?page=${page+1}&option=${option}&year=${year}">下一页</a>|
	         <a class="lastpage" href="/ModelWorker/user/allprevioussearch?page=${maxpage}&option=${option}&year=${year}">末页</a>
	         <span>第${page}页/共
	         <fmt:formatNumber type="number" value="${size eq 0?1: Math.ceil(size/2)}" maxFractionDigits="0"/>页
	         </span>
	         <span ></span>|第<input id = "turn" name="turn" value="" style="width:20px"/>页|
             <a id="turn_a" href="/ModelWorker/user/allprevioussearch">跳转</a>
	         <span>共${size}条</span>	           
	        </div> 
	             
         </div>
         </div>
       </div>
</body>
</html>