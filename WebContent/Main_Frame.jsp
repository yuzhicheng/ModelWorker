<%@page import="org.xadzkd.domain.User"%>
<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>总工会劳模管理系统</title>
<link href="static/css/Main_Frame.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="static/js/common/jquery2.js"></script>
<script type="text/javascript" src="static/js/common/common.js"></script>
<script type="text/javascript" src="static/js/common/jquery2.min.js"></script>
<script type="text/javascript">
$(document).ready(function huadong(){
	$(".systitle").animate({left:'42%'},1500);
});

</script>
</head>
<body>
<div>

	<!-- 顶部信息 -->
	<div id="FrameTop" class="frame-top">
	    <div class="topdata">
	        <div id="SysIcon"></div>
	        <h1 class="systitle">总工会劳模管理系统</h1>
	        <div class="siteinfo">
	        	<p>欢迎您，${(currentUser != null && !currentUser.isPerson)  ?  "集体用户：" : ""}${currentUser != null ? ( currentUser.isPerson ? currentUser.personUser.username: currentUser.groupUser.groupname) : "null"}</p>
	            <div class="toprightmenu">
	                <a href="/ModelWorker/user/doLogout">退出系统</a>
	            </div>
	         </div>
	     </div>
	</div>
 
 	<!-- 中部菜单 -->  
 	<div id="middle_menu">   
 		<!-- 左侧菜单-->
		<div class="frame-left"> 
				<ul class="leftmenu">
				<c:if test="${currentUser != null}">
					
					<li>
						<a class="dropmenu">劳模申请/变更</a>
						<ul class="submenu">
							<c:if test="${currentUser!= null && currentUser.isPerson }">
								<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/personapply/access">个人劳模申请</a></li>
								<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/personapply/editaccess">个人劳模变更</a></li>						
							</c:if>
							<c:if test="${currentUser!= null && !currentUser.isPerson }">
								<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/groupapply/access">集体劳模申请</a></li>
								<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/groupapply/editaccess">集体劳模变更</a></li>									
							</c:if>
						</ul>
					</li>
				</c:if>
				<c:if test="${currentUser != null && (currentUser.isPerson && currentUser.personUser.permission == 1) }">
					<li>
						<a class="dropmenu">劳模审核</a>
						<ul class="submenu">
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/personcheckpage">个人劳模审核</a></li>						
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/group/groupcheckpage">集体劳模审核</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/checkresultpage?sign=0">已审核信息</a></li>														
						</ul>
					</li>
				</c:if>
					<li>
						<a class="dropmenu">查询与统计</a>
						<ul class="submenu">
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/personinfo">先进个人信息查询</a></li>							
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/personstatistics">先进个人信息统计</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/groupinfo">先进集体信息查询</a></li>																											
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/groupstatistics">先进集体信息统计</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/allpreviousinfo">历届劳模信息查询</a></li>									
						</ul>
					</li>
				<c:if test="${currentUser != null && (currentUser.isPerson && currentUser.personUser.permission == 0) }">
					<li>
						<a class="dropmenu">数据管理</a>
						<ul class="submenu">
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/upload">信息导入</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/DataImprotAndExport/DataExport">信息导出</a></li>
						</ul>
					</li>
				</c:if>
					<li>
						<a class="dropmenu">个人管理</a>
						<ul class="submenu">
							<li><a target="mainFrame" class="submenu_text" href="/ModelWorker/User_Manage/modifyPasswordPage">密码修改</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/User_Manage/userInfo">用户信息修改</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/User_Manage/userInfoSelect">用户信息查询</a></li>
						</ul>
					</li>
				<c:if test="${currentUser != null && (currentUser.isPerson && currentUser.personUser.permission == 0) }">		
					<li>
						<a class="dropmenu">系统管理</a>
						<ul class="submenu">				
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/dataDictionary">数据字典</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/dump">数据备份</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/recover">数据还原</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/methodAccess">添加用户</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/listAccess">用户列表</a></li>
							<li><a class="submenu_text" target="mainFrame" href="/ModelWorker/user/status">期限管理</a></li>
						</ul>
					</li>
				</c:if>				
				</ul>
		</div>
		
		<!-- 内容区域-->
    	<div class="frame-main">
    	<iframe id="MainFrame" scrolling="auto" frameborder="no" name="mainFrame" src="list.jsp">
        </iframe>
		
		</div>
	
		<!-- 底部菜单 -->
		<div id="bottom_menum" style="padding-top:10px;">
		<span id="bottom_text">西安电子科技大学第四组版权所有 &copy;2015-2020</span>
		
	</div>
	</div>
</div>
</body>
</html>