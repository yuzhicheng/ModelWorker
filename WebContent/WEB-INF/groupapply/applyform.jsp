<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	.fmcontainer{
		margin-top:95px;
	}
	.submitbtn{
		width:90px;
		height:38px;
		margin:10px 225px 6px;
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
		width:196px;
		height:30px;
		margin-left:0;
		margin-right:25px;
		text-align:center;
	}
	textarea{
		resize:none;
	}
	
</style>
</head>
<body>
    <div class="maincontainer">
		<div class="fmcontainer">
		<form action="/ModelWorker/groupapply/${empty groupApply?'save':'edit'}" method="post">
			<input type="hidden" name="id" value="${groupApply.id }"/>
			<input type="hidden" name="groupname" value="${empty groupApply?groupname:groupApply.groupname }"></input>
			<input type="hidden" name="result" value="${groupApply.result }"/>
			<input type="hidden" name="year" value="2015"/>
        	<table class="inputtable" >
        		<tr>
        			<td class="leftlabel">劳模称号:</td>
        			<td>
        				<select id="title" class="inputbox" name="title">
	        				<option value="-1">--------请选择--------</option>
	        				<c:forEach items="${advancedGroupList}" var="advancedGroup" varStatus="status">
	        					<option ${groupApply.title eq status.index?"selected='selected'":"" } value="${status.index }">${advancedGroup }</option>
	        				</c:forEach>
        				</select>
        			</td>
        		</tr>
                <tr>
            		<td class="leftlabel">先进事迹：</td>
                    <td colspan="3"><textarea name="achievement" cols="45" rows="4">${groupApply.achievement }</textarea></td>
                </tr>
            </table>
            <div class="fmsubmitbtn">
            	<input type="submit" class="submitbtn" value="确定" />
                <!-- <input type="reset" name="cancel" class="btn" value="取 消" /> -->
            </div>
            </form>
        </div>
    </div>
</body>
</html>