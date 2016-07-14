package org.xadzkd.controller;


import org.xadzkd.domain.Group;
import org.xadzkd.domain.GroupApply;
import org.xadzkd.service.GroupService;
import org.xadzkd.service.GroupApplyService;
import org.xadzkd.tool.MessageDigestUtil;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired
	GroupService groupService;
	@Autowired
	GroupApplyService groupApplyService;
	
	@RequestMapping("/toSave")
	public String toSave(Long id ,Integer page, Model model) {
		if(id != null) {
			Group group = groupService.get(id) ;
			model.addAttribute("group",group);
		}
		model.addAttribute("page", page);
		
		return "/group/save";
	}
	
	@RequestMapping("/save")
	public String save(Group group) {
		
		groupService.addGroup(group);
		
		return "redirect:/group/list";
	}
	
	@RequestMapping("/edit")
	public String edit(Group group,Integer page) {
		String pwd = MessageDigestUtil.digestByMD5(group.getPassword());
		group.setPassword(pwd);
		groupService.update(group);
		
			
		String redirect = "redirect:/group/list?page="+page;
		
		return redirect;
			
	}
	
	@RequestMapping("/delete")
	public String delete(Long id,Integer size, Integer page) {
		Group group = groupService.get(id) ;
		
		if( group != null) {
			groupService.delete(group);
			GroupApply groupApply = groupApplyService.findByGroupname(group.getGroupname());
			if(groupApply != null)
				groupApplyService.delete(groupApply);			
		}
		
		if(10*(page-1) == size-1 && size > 1)
			page -= 1;
			
		String redirect = "redirect:/group/list?page="+page;
		return redirect;
	}
	
	@RequestMapping("/list")
	public String list(Integer method,String inqueryInfo,Integer page,Model model){
		
		List<Group> groupList = groupService.findAll();
		
		List<Group> groupListPart = new ArrayList<>();
			
		if(method == null)
			method = 2;
		
		if(method == 0) {
			groupList = new ArrayList<Group>(2);
			groupList.add(groupService.findByGroupName(inqueryInfo));
		} else if(method == 1) {
			groupList = groupService.findByName(inqueryInfo);
		} else {
			groupList = groupService.findAll();
		}
		
		if(page == null)
			page = 1;
		
		int start = 10*(page-1), end = 10*(page-1) + 9;
		int	size = groupList.size();
		
		
		for(int i = start; i <= end; i++){
			if(i > size - 1)
				break;
			Group item = groupList.get(i);
			groupListPart.add(item);
		}
			
		
		model.addAttribute("groupListPart", groupListPart);
		model.addAttribute("page",page);
		model.addAttribute("size",size);
		model.addAttribute("method", method);
		model.addAttribute("inqueryInfo", inqueryInfo);
		
		return "/group/list";
	}
	
	@ResponseBody
	@RequestMapping("checkGroupnameDup")
	public String checkGroupnameDup(String groupname){
		Group group = groupService.findByGroupName(groupname);
		
		if(group != null)
			return "true";
		
		return "false";
	}
	
	@RequestMapping("/list2")
	public String list2(Integer method,String inqueryInfo,Integer page,Model model){
		
		List<Group> groupList = groupService.findAll();
		
		List<Group> groupListPart = new ArrayList<>();
			
		if(method == null)
			method = -1;
		
		if(method == 0) {
			groupList = new ArrayList<Group>(2);
			groupList.add(groupService.findByGroupName(inqueryInfo));
		} else if(method == 1) {
			groupList = groupService.findByName(inqueryInfo);
		} else {
			groupList = groupService.findAll();
		}
		
		if(page == null)
			page = 1;
		
		int start = 5*(page-1), end = 10*(page-1) + 4;
		int	size = groupList.size();
		
		
		for(int i = start; i <= end; i++){
			if(i > size - 1)
				break;
			Group item = groupList.get(i);
			groupListPart.add(item);
		}
			
		
		model.addAttribute("groupListPart", groupListPart);
		model.addAttribute("page",page);
		model.addAttribute("size",size);
		model.addAttribute("method", method);
		model.addAttribute("inqueryInfo", inqueryInfo);
		
		return "/group/list2";
	}
}
