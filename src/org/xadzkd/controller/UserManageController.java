package org.xadzkd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xadzkd.domain.AdvancedGroup;
import org.xadzkd.domain.AdvancedPerson;
import org.xadzkd.domain.Group;
import org.xadzkd.domain.GroupApply;
import org.xadzkd.domain.PersonApply;
import org.xadzkd.domain.User;
import org.xadzkd.service.AdvancedGroupService;
import org.xadzkd.service.AdvancedPersonService;
import org.xadzkd.service.GroupApplyService;
import org.xadzkd.service.GroupService;
import org.xadzkd.service.PersonApplyService;
import org.xadzkd.service.UserService;
import org.xadzkd.tool.CurrentUser;
import org.xadzkd.tool.MessageDigestUtil;

@Controller
@RequestMapping("/User_Manage")
public class UserManageController {
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	PersonApplyService personApplyService;
	
	@Autowired
	AdvancedPersonService advancedPersonService;
	
	@Autowired
	GroupApplyService groupApplyService;
	
	@Autowired
	AdvancedGroupService advancedGroupService;
	
	

	@RequestMapping("/modifyPasswordPage")
	public String modifyPasswordPage(Model model,HttpServletRequest request){
		CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("currentUser");
		if(currentUser == null)
		{
			String errMsg = "请先登录！！";
			model.addAttribute("errMsg",errMsg);
			return "/user/errMsg";
		}
		else
		return "user/user_password";
	}
	
	@ResponseBody
	@RequestMapping("/Modify_Password")
	public String Modify_Password(String newpassword1, String oldpassword, HttpServletRequest request, HttpServletResponse response, Model model){
		String username =null;
		String message=null;
		CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("currentUser");
		
		if(currentUser.getIsPerson()){
			username = currentUser.getPersonUser().getUsername();
			if(username!=null){
				String userpassword = userService.getPassword(username);
				oldpassword = MessageDigestUtil.digestByMD5(oldpassword);
				if(userpassword.equals(oldpassword)){
					userService.updatePassword(username, newpassword1);
					currentUser.setPersonUser(userService.login(username, newpassword1));//============================================
					request.getSession().setAttribute("currentUser", currentUser);
					message = "true";
				}else {
					message = "false";
				}
			}			
		}else{
			username = currentUser.getGroupUser().getGroupname();
			if(username!=null){
				String userpassword = currentUser.getGroupUser().getPassword();//==========================================
				oldpassword = MessageDigestUtil.digestByMD5(oldpassword);
				if(userpassword.equals(oldpassword)){//================================================================
					groupService.updatePassword(username, newpassword1);//============================================
					currentUser.setGroupUser(groupService.login(username, newpassword1));//======================================================
					request.getSession().setAttribute("currentUser", currentUser);
					message = "true";
				}else {
					message = "false";
				}
			}
		}
		
		return message;
	}
	
	@ResponseBody
	@RequestMapping("/password")
	public String getPassword(String oldpassword, HttpServletRequest request){//=============================================
		oldpassword = MessageDigestUtil.digestByMD5(oldpassword);
		CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("currentUser");
		String userpassword = null;
		if(currentUser.getIsPerson()){
			userpassword = currentUser.getPersonUser().getPassword();
		}else {
			userpassword = currentUser.getGroupUser().getPassword();
		}
		if(!oldpassword.equals(userpassword)){
			return "false";
		}else {
			return "true";
			
		}
	}
	
	
	@RequestMapping("/userInfo")
	public String getUserInfo(HttpServletRequest request, Model model){
		CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("currentUser");
		String username = null;
		String str = null;
		if(currentUser == null)
		{
			String errMsg = "请先登录！！";
			model.addAttribute("errMsg",errMsg);
			return "/user/errMsg";
		}
		else if(currentUser.getIsPerson()){
			username = currentUser.getPersonUser().getUsername();
			User user = userService.getUser(username);
			model.addAttribute("userinfo", user);
		}else {
			username = currentUser.getGroupUser().getGroupname();
			Group group = currentUser.getGroupUser();
			model.addAttribute("groupinfo", group);
		}

		str = (String) request.getParameter("mofify_message");
		if(str!=null){
			if(str.equals("yes")){
				model.addAttribute("mofify_message", "修改成功！");
			}else {
				model.addAttribute("mofify_message", "修改失败！");
			}		
		}
		
		return "user/user_info";
	}
	
	
	@RequestMapping("/modifyUserInfo")
	public String modifyUserInfo(String people, HttpServletRequest request, HttpServletResponse response, Model model){
		CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("currentUser");
		String username = null;
		String password = null;//========================================================================
		if(currentUser == null)
		{
			String errMsg = "请先登录！！";
			model.addAttribute("errMsg",errMsg);
			return "/user/errMsg";
		}
		else if(currentUser.getIsPerson()){
			username = currentUser.getPersonUser().getUsername();
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String post = request.getParameter("post");
			String degree = request.getParameter("degree");
			String str = (String)request.getParameter("unionName");
			int unionName = -1;
			switch (str) {
			case "省总工会":
				unionName = 0;
				break;
			case "市州总工会":
				unionName = 1;
				break;
			case "省产业局":
				unionName = 2;
				break;
			case "企业集团公会":
				unionName = 3;
				break;
			default:
				break;
			}
			String address = request.getParameter("address");
			userService.updateInfo(username, email, phone, post, degree, unionName, address);
			currentUser.setPersonUser(userService.findByUsername(username));//==========================================================================
			request.getSession().setAttribute("currentUser", currentUser);
		}else {
			username = currentUser.getGroupUser().getGroupname();
			String belong =request.getParameter("belong");
			String managerName = request.getParameter("managerName");
			String phone = request.getParameter("phone");
			String address =request.getParameter("address");
			groupService.updateGroupInfo(belong, managerName, phone, address, username);
			currentUser.setGroupUser(groupService.findByGroupName(username));//============================================================================
			request.getSession().setAttribute("currentUser", currentUser);
		}
		return "redirect:/User_Manage/userInfo?mofify_message=yes";
	}
	
	//================用户信息判断=================
	@RequestMapping("/userInfoSelect")
	public String userInfoSelect(HttpServletRequest request, Model model){
		CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("currentUser");
		String username = null;
		if(currentUser == null)
		{
			String errMsg = "请先登录！！";
			model.addAttribute("errMsg",errMsg);
			return "/user/errMsg";
		}
		else if(currentUser.getIsPerson()){
			username = currentUser.getPersonUser().getUsername();
			if((advancedPersonService.findByUsername(username)).size()!=0){	
				//==================先进个人表
				List<AdvancedPerson> advancePerson_list = advancedPersonService.orderByYear(username);
				model.addAttribute("advancePerson", advancePerson_list.get(0));
				User user = userService.findByUsername(username);
				model.addAttribute("user", user);
				return "user/advancePersonInfoSelect";
			}else if (personApplyService.findByUsername(username)!=null) {	
				//===================个人申请表
				PersonApply personApply = personApplyService.findByUsername(username);
				model.addAttribute("personApply", personApply);
				User user = userService.findByUsername(username);
				model.addAttribute("user", user);
				return "user/personInfoSelect";
			}
		}else {
			username = currentUser.getGroupUser().getGroupname();
			if (advancedGroupService.findByGroupname(username).size()!=0) {	
				//=======================先进集体表
				List<AdvancedGroup> advancedGroup_list = advancedGroupService.orderByYear(username);
				model.addAttribute("advancedGroup", advancedGroup_list.get(0));
				Group group = groupService.findByGroupName(username);
				model.addAttribute("group", group);
				return "user/advanceGroupInfoSelect";
			}else if (groupApplyService.findByGroupname(username)!=null) {	
				//=======================集体申请表
				GroupApply groupApply = groupApplyService.findByGroupname(username);
				model.addAttribute("groupApply", groupApply);
				Group group = groupService.findByGroupName(username);
				model.addAttribute("group", group);
				return "user/groupInfoSelect";
			}
		}
		String errMsg = "请到用户信息修改查看自己的信息";
		model.addAttribute("errMsg",errMsg);
		return "/user/errMsg";
	}
}
