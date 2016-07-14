package org.xadzkd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xadzkd.domain.PersonApply;
import org.xadzkd.service.GroupApplyService;
import org.xadzkd.service.PersonApplyService;
import org.xadzkd.tool.CurrentUser;
import org.xadzkd.tool.DataDictionary;
import org.xadzkd.tool.VerifyStatus;

@Controller
@RequestMapping("/user")
public class CheckController {
	
	@Autowired
	PersonApplyService personapply;
	
	@Autowired
	GroupApplyService groupApplyService;
	
	@RequestMapping("/personcheckpage")
	public String checkinfo(Model model,HttpServletRequest request) {
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson()&&currentUser.getPersonUser().getPermission() ==1)  ? 1 :  0;
		VerifyStatus verifyStatus = VerifyStatus.getInstance();
		int status=verifyStatus.getStatus();
		if(permission != 1)
		{
			model.addAttribute("errMsg", "你没有审核权限！！");
			return "user/errMsg";
		}
		else if(status != 2)
		{
			model.addAttribute("errMsg", "未在审核时间内！");
			return "user/errMsg";
		}
		else
		{
		List<Map<String,String>> applyList=personapply.findAllUnchecked();
		System.out.println(applyList);
		//返回民族的数据字典
		ArrayList<String> nation=DataDictionary.getInstance(request).getNation();
		ArrayList<String> persontitle=DataDictionary.getInstance(request).getAdvancedPerson();
		//创建性别的数据字典
		ArrayList<String> sex=new ArrayList<String>();sex.add("男");sex.add("女");
	    model.addAttribute("personnation", nation);
	    model.addAttribute("persontitle",persontitle);
	    model.addAttribute("personsex",sex);
		model.addAttribute("applyList", applyList);
		return "user/personcheck";
		}
	}
	@RequestMapping("/checkpass")
	public String checkpass(String username,Model model){	
		PersonApply apply=personapply.findByUsername(username);
		if(apply!=null){
		apply.setResult(1);
		personapply.update(apply);
		}
		return "redirect:/user/personcheckpage" ;	
	}
	@RequestMapping("/checkrefuse")
	public String checkrefuse(String username,Model model){		
		PersonApply apply=personapply.findByUsername(username);
		if(apply!=null){
		apply.setResult(2);
		personapply.update(apply);
		}
		return "redirect:/user/personcheckpage" ;	
	}
	
	@RequestMapping("/checkcancel")
	public String checkcancel(String username)
	{
		PersonApply apply=personapply.findByUsername(username);
		if(apply!=null)
		{
			apply.setResult(0);
			personapply.update(apply);
		}
		return "redirect:/user/checkresultpage?sign=0";
	}
	
	@RequestMapping("/checkresultpage")
	public String checkresultpage(Model model ,HttpServletRequest request,int sign)
	{
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson()&&currentUser.getPersonUser().getPermission() ==1)  ? 1 :  0;
		VerifyStatus verifyStatus = VerifyStatus.getInstance();
		int status=verifyStatus.getStatus();
		if(permission != 1)
		{
			model.addAttribute("errMsg", "你没有审核权限！！");
			return "user/errMsg";
		}		
		else if(status != 2)
		{
			model.addAttribute("errMsg", "未在审核时间内！");
			return "user/errMsg";
		}
		else 
		{
		List<Map<String,String>> checkedList=personapply.findAllChecked();
		System.out.println(checkedList);
		//返回民族的数据字典
		ArrayList<String> nation=DataDictionary.getInstance(request).getNation();
		ArrayList<String> persontitle=DataDictionary.getInstance(request).getAdvancedPerson();
		//创建性别的数据字典
		ArrayList<String> result=new ArrayList<String>();
		result.add("未审核");result.add("通过");result.add("未通过");
		ArrayList<String> sex=new ArrayList<String>();sex.add("男");sex.add("女");
	    model.addAttribute("personnation", nation);
	    model.addAttribute("persontitle",persontitle);
	    model.addAttribute("result",result);
	    model.addAttribute("personsex",sex);
		model.addAttribute("checkedList", checkedList);
		
		List<Map<String,String>> groupCheckedList=groupApplyService.findAllChecked();
		System.out.println(groupCheckedList);
		ArrayList<String> grouptitle=DataDictionary.getInstance(request).getAdvancedCollective();
	    model.addAttribute("grouptitle",grouptitle);
		model.addAttribute("groupCheckedList", groupCheckedList);
		model.addAttribute("sign",sign);
		
		return "user/checkedInfo";
		}
	}
	
	

}
