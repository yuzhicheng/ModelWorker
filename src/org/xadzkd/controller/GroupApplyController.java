package org.xadzkd.controller;

import org.xadzkd.tool.DataDictionary;

import org.xadzkd.domain.GroupApply;
import org.xadzkd.domain.Group;

import org.xadzkd.service.GroupApplyService;
import org.xadzkd.service.GroupService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xadzkd.tool.CurrentUser;
import org.xadzkd.tool.VerifyStatus;

@Controller
@RequestMapping("/groupapply")
public class GroupApplyController{
	@Autowired
	GroupApplyService groupApplyService;
	@Autowired
	GroupService groupService;
	
	@RequestMapping("/access")
	public String apply(HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes attr){
		/*
		 * 判断申请是否结束，若结束，则提示用户
		 * */
		VerifyStatus verifyStatus = VerifyStatus.getInstance();
		int status = verifyStatus.getStatus();
		
		if(status != 0){
			String message = "申请已结束";
			model.addAttribute("message", message);
			return "/personapply/promp";
		}
		
		Group group = null;
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		/*
		 * 判断用户是否登录，若没登录，提醒登录。否则判断是集体用户还是个人用户，若是个人，则提醒用户申请个人劳模
		 * */
		if(currentUser == null)
		{
			String message = "请先登录！！";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		else if(! currentUser.getIsPerson()){
			group = currentUser.getGroupUser();
		} else {
			String message = "请到个人劳模申请模块申请!";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		String groupname = group.getGroupname();
		
		
		
		/*
		 * 判断用户是否已经申请过，若是，则提醒
		 * */
		
		if(groupApplyService.findByGroupname(groupname) != null){
			String message = "您已经申请了，请不要重复申请";
			model.addAttribute("message", message);
			return "/personapply/promp";
		}
		
		/*
		 * 判断结束，重定向到申请界面
		 * */
		attr.addAttribute("groupname", groupname);
		return "redirect:/groupapply/showdetails";
	}
	
	@RequestMapping("/showdetails")
	public String showDetails(String groupname,Model model){
		Group group = groupService.findByGroupName(groupname);
		
		model.addAttribute("group",group);
		
		return "groupapply/showdetails";
	}

	@RequestMapping("/applyform")
	public String applyForm(String groupname,Model model,HttpServletRequest request){
		//获取数据字典
		DataDictionary dataDirtionary = DataDictionary.getInstance(request);
		//获取先进自己荣誉称号
		ArrayList<String> advancedGroupList = dataDirtionary.getAdvancedCollective();
		model.addAttribute("advancedGroupList", advancedGroupList);
		
		GroupApply groupApply = groupApplyService.findByGroupname(groupname);
		if(groupApply != null){
			model.addAttribute("groupApply", groupApply);
		} else {
			model.addAttribute("groupname", groupname);
		}
		
		return "/groupapply/applyform";
	}
	
	@RequestMapping("/save")
	public String save(GroupApply ga, Model model){
		
		ga.setResult(0);
		groupApplyService.add(ga);
		
		String message = "您已申请成功！";
				
		model.addAttribute("message", message);
		
		return "/personapply/promp";
	}
	
	@RequestMapping(value="/editaccess",method = RequestMethod.GET)
	public String edit(HttpServletRequest request,HttpServletResponse response ,Model model,RedirectAttributes attr){
		/*
		 * 判断申请是否结束，若结束，则提示用户
		 * */
		VerifyStatus verifyStatus = VerifyStatus.getInstance();
		int status = verifyStatus.getStatus();
		
		if(status != 0){
			String message = "申请已结束";
			model.addAttribute("message", message);
			return "/personapply/promp";
		}
		
		/*
		 * 判断用户是否登录，若没登录，提醒登录。否则判断是集体用户还是个人用户，
		 * 若是个人用户，则提醒用户到个人劳模变更模块变更
		 * */
		Group group = null;
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		if(currentUser == null)
		{
			String message = "请先登录！！";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		else if(!currentUser.getIsPerson()){
			group = currentUser.getGroupUser();
		} else {
			String message = "请到个人劳模变更模块变更!";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		String groupname = group.getGroupname();
		
		
		/*
		 * 判断该集体用户是否申请，若没申请，则提醒用户到集体申请模块申请
		 * */
		GroupApply groupApply = groupApplyService.findByGroupname(groupname);
		if(groupApply == null){
			String message = "请先到集体劳模申请进行申请";
			model.addAttribute("message", message);
			return "/personapply/promp";
		}
		/*model.addAttribute("groupApply", groupApply);*/
		attr.addAttribute("groupname", groupname);
		
		return "redirect:/groupapply/applyform";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String edit(GroupApply groupApply, Model model){
		groupApplyService.update(groupApply);
		
		String message = "申请变更成功";
		model.addAttribute("message",message);
		
		return "/personapply/promp";
	}
	
}
