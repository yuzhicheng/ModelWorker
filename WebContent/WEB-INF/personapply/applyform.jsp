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
	.formsubmitdiv{
	}
	.submitbtn{
		width:90px;
		height:38px;
		margin:10px 280px 6px;
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
		<form action="/ModelWorker/personapply/${empty personApply?'save':'edit'}" method="post">
			<input type="hidden" name="id" value="${personApply.id }"/>
			<input type="hidden" name="username" value="${empty personApply?username:personApply.username }"></input>
			<input type="hidden" name="result" value="${personApply.result }"/>
			<input type="hidden" name="year" value="2015"/>
        	<table class="inputtable" >
        		<tr>
        			<td class="leftlabel">劳模称号:</td>
        			<td>
        				<select id="title" class="inputbox" name="title">
	        				<option value="-1">--------请选择--------</option>
	        				<c:forEach items="${advancedPersonList}" var="advancedPerson" varStatus="status">
	        					<option ${personApply.title eq status.index?"selected='selected'":""} value="${status.index}">${advancedPerson}</option>
	        				</c:forEach>
        				</select>
        			</td>
        		</tr>
        		<tr>
        			<td class="leftlabel">家庭困难情况:</td>
        			<td colspan="3"><textarea name="difficulty" cols="60" rows="3">${personApply.difficulty }</textarea></td>
        		</tr>
        		<tr>
            		<td class="leftlabel">身体情况：</td>
                    <td colspan="3"><textarea name="bodycondition" cols="60" rows="3">${personApply.bodycondition }</textarea></td>
                </tr>
                <tr>
            		<td class="leftlabel">就业情况：</td>
                    <td colspan="3"><textarea name="workcondition" cols="60" rows="3">${personApply.workcondition }</textarea></td>
                </tr>
                <tr>
            		<td class="leftlabel">突出事迹：</td>
                    <td colspan="3"><textarea name="achievement" cols="60" rows="3">${personApply.achievement }</textarea></td>
                </tr>
            </table>
            <div class="fmsubmitdiv">
            	<input type="submit" class="submitbtn" value="下一步" />
                <!-- <input type="reset" name="cancel" class="btn" value="取 消" /> -->
            </div>
            </form>
        </div>
    </div>
</body>
</html>