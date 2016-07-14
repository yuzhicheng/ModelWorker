package org.xadzkd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xadzkd.domain.GroupApply;
import org.xadzkd.service.GroupApplyService;
import org.xadzkd.tool.CurrentUser;
import org.xadzkd.tool.DataDictionary;
import org.xadzkd.tool.VerifyStatus;

@Controller
@RequestMapping("/group")
public class GroupCheckController
{
	@Autowired
	GroupApplyService groupApplyService;
	
	@RequestMapping("/groupcheckpage")
	public String checkinfo(Model model,HttpServletRequest request)
	{
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson()&&currentUser.getPersonUser().getPermission() ==1)  ? 1 :  0;
		VerifyStatus verifyStatus = VerifyStatus.getInstance();
		int status=verifyStatus.getStatus();
		if(permission != 1)
		{
			model.addAttribute("errMsg","你没有审核权限！！");
			return "user/errMsg";
		}
		else if(status != 2)
		{
			model.addAttribute("errMsg", "未在审核时间内！");
			return "user/errMsg";
		}
		else 
		{
		List<Map<String, String>> groupApplyList=groupApplyService.findAllUnchecked();
		
		ArrayList<String> groupTitle=DataDictionary.getInstance(request).getAdvancedCollective();
		
		model.addAttribute("groupApplyList",groupApplyList);
		model.addAttribute("groupTitle",groupTitle);

		return "user/groupCheck";
		}
	}
	
	@RequestMapping("/groupcheckpass")
	public String checkpass(String groupname,Model model){		
		GroupApply groupApply=groupApplyService.findByGroupname(groupname);
		if(groupApply!=null){
			groupApply.setResult(1);
		groupApplyService.update(groupApply);
		}
		return "redirect:/group/groupcheckpage" ;	
	}
	@RequestMapping("/groupcheckrefuse")
	public String checkrefuse(String groupname,Model model){		
		GroupApply groupApply=groupApplyService.findByGroupname(groupname);
		if(groupApply!=null){
			groupApply.setResult(2);
		groupApplyService.update(groupApply);
		}
		return "redirect:/group/groupcheckpage" ;	
	}
	
	@RequestMapping("/checkcancel")
	public String checkcancel(String groupname)
	{
		GroupApply groupApply=groupApplyService.findByGroupname(groupname);
		if(groupApply!=null)
		{
			groupApply.setResult(0);
			groupApplyService.update(groupApply);
		}
		int sign=1;
		return "redirect:/user/checkresultpage?sign="+sign;
	}
	
	/*@RequestMapping("/checkresultpage")
	public String checkresultpage(Model model ,HttpServletRequest request)
	{
		List<Map<String,String>> groupCheckedList=groupApplyService.findAllChecked();
		System.out.println(groupCheckedList);
		ArrayList<String> grouptitle=DataDictionary.getInstance(request).getAdvancedCollective();
		
	    model.addAttribute("grouptitle",grouptitle);
		model.addAttribute("groupCheckedList", groupCheckedList);
		return "user/checkedInfo";
	}*/
}
