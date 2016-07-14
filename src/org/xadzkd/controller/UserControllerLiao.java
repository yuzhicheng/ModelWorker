package org.xadzkd.controller;

import org.xadzkd.domain.PersonApply;
import org.xadzkd.domain.User;
import org.xadzkd.service.PersonApplyService;
import org.xadzkd.service.UserService;
import org.xadzkd.tool.CurrentUser;
import org.xadzkd.tool.DataDictionary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserControllerLiao {
	
	@Autowired
	UserService userService; 
	@Autowired
	PersonApplyService personApplyService;

	
	@RequestMapping("/toSave")
	public String toSave(Long id ,Integer page, Model model,HttpServletRequest request) {
		//获取数据字典
		DataDictionary dataDictionary = DataDictionary.getInstance(request);
		
		/*
		 * 获取数据字典中的民族
		 * */
		ArrayList<String> nationList = dataDictionary.getNation();
		model.addAttribute("nationList",nationList);
		
		/*
		 * 获取数据字典中的所属公会
		 * */
		ArrayList<String> unionList = dataDictionary.getUnion();
		model.addAttribute("unionList", unionList);
		
		/*
		 *获取数据字典中的性别
		 * */
		ArrayList<String> sexList = dataDictionary.getSex();
		model.addAttribute("sexList",sexList);
		
		if(id != null) {
			User user = userService.get(id) ;
			model.addAttribute("user",user);
		}
		model.addAttribute("page", page);
		
		return "/user/save";
	}
	
	@RequestMapping("/save")
	public String save(User u)  {
		u.setPersoncondition(0);
		userService.addUser(u);
		
		return "redirect:/user/list";
	}
	
	@RequestMapping("/delete")
	public String delete(Long id,Integer size, Integer page) {
		User user = userService.get(id) ;
		
		if( user != null) {
			userService.delete(user);
			PersonApply personApply = personApplyService.findByUsername(user.getUsername());
			if(personApply != null)
				personApplyService.delete(personApply);			
		}
		
		if(10*(page-1) == size-1 && size > 1)
			page -= 1;
			
		String redirect = "redirect:/user/list?page="+page;
		return redirect;
	}
	
	@RequestMapping("/edit")
	public String edit(User user,Integer page) {
		userService.updatePart(user);
		
			
		String redirect = "redirect:/user/list?page="+page;
		
		return redirect;
			
	}
	
	/*
	 * 判断用户的访问权限，若权限为0则可以访问，否则无法访问
	 * */	
	@RequestMapping("/listAccess")
	public String listAccess(HttpServletRequest request,HttpServletResponse response,Model model){
		User user = null;
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		if(currentUser.getIsPerson()){
			user = currentUser.getPersonUser();
		} else {
			String message = "集体账户无权访问用户列表";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		int permission = user.getPermission();
		if(permission != 0) {
			String message = "您的权限不足";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		
		return "redirect:/user/selectList";
	}
	
	@RequestMapping("/selectList")
	public String selectList(){
	
		return "/user/selectList";
	}
	
	@RequestMapping("/list")
	public String list(Integer method, String inqueryInfo,Integer permission, Integer page,Model model,HttpServletRequest request) {
		//获取数据字典
		DataDictionary dataDictionary = DataDictionary.getInstance(request);
		
		/*
		 * 获取数据字典中的民族
		 * */
		ArrayList<String> nationList = dataDictionary.getNation();
		model.addAttribute("nationList",nationList);
		
		/*
		 * 获取数据字典中的所属公会
		 * */
		ArrayList<String> unionList = dataDictionary.getUnion();
		model.addAttribute("unionList", unionList);
		
		/*
		 *获取数据字典中的性别
		 * */
		ArrayList<String> sexList = dataDictionary.getSex();
		model.addAttribute("sexList",sexList);
		
		List<User> userList;
		List<User> userListPart = new ArrayList<>();
		
		if(method == null)
			method = 4;
		
		if(method == 0) {
			userList = userService.findByName(inqueryInfo);
		} else if(method == 1) {
			User user = userService.findByIdcard(inqueryInfo);
			userList = new ArrayList<User>(10);
			if( user != null) userList.add(user);				

		} else if(method == 2 ){
			User user = userService.findByUsername(inqueryInfo);
			userList = new ArrayList<User>(10);
			if(user != null) userList.add(user);
		} else if(method == 3){			
			userList = userService.findByPermission(permission);
		} else {
			userList = userService.findAll();
		}
		
		if(page == null)
			page = 1;
		
		int start = 10*(page-1), end = 10*(page-1) + 9;
		int	size = userList.size();
		
		
		for(int i = start; i <= end; i++){
			if(i > size - 1)
				break;
			User item = userList.get(i);
			userListPart.add(item);
		}
			
		
		model.addAttribute("userListPart", userListPart);
		model.addAttribute("page",page);
		model.addAttribute("size",size);
		model.addAttribute("method", method);
		model.addAttribute("inqueryInfo", inqueryInfo);
		model.addAttribute("permission", permission);
		
		
		return "/user/list";

	}
	/*
	 * 判断用户的访问权限，若权限为0则可以访问，否则无法访问
	 * */
	@RequestMapping("/methodAccess")
	public String methodAccess(HttpServletRequest request,HttpServletResponse response,Model model) {
		User user = null;
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		if(currentUser.getIsPerson()){
			user = currentUser.getPersonUser();
		} else {
			String message = "集体账户无权进行用户添加";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		int permission = user.getPermission();
		if(permission != 0) {
			String message = "您的权限不足";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		
		return "redirect:/user/selectMethod";
	}
	
	@RequestMapping("/selectMethod")
	public String selectMethod(){
		
		return "/user/selectMethod";
	}
	
	@ResponseBody
	@RequestMapping("/checkUsernameDup")
	public String checkUsernameDup(String username){
		User user = userService.findByUsername(username);
		
		if(user != null)
			return "true";
		
		return "false";
	}
	
	@ResponseBody
	@RequestMapping("/checkIdCardDup")
	public String checkIdCardDup(String idCard){
		User user = userService.findByIdcard(idCard);
		
		if(user != null)
			return "true";
		
		return "false";
	}
	
	@RequestMapping("/selectList2")
	public String selectList2(){
		
		return "/user/selectList2";
	}
	
	@RequestMapping("/list2")
	public String list2(Integer method, String inqueryInfo, Integer page,Model model) {
		List<User> userList;
		List<User> userListPart = new ArrayList<>();
		
		if(method == null)
			method = -1;
		
		/*
		 * 判断查找方式
		 * */
		if(method == 0) {
			userList = userService.findByName(inqueryInfo);
		} else if(method == 1) {
			User user = userService.findByIdcard(inqueryInfo);
			userList = new ArrayList<User>(10);
			if( user != null) userList.add(user);				
		} else if(method == 2 ){
			User user = userService.findByUsername(inqueryInfo);
			userList = new ArrayList<User>(10);
			if(user != null) userList.add(user);
		} else if(method == 3){			
			userList = userService.findByPermission(Integer.parseInt(inqueryInfo.trim()));
		} else {
			userList = userService.findAll();
		}
		
		if(page == null)
			page = 1;
		
		int start = 5*(page-1), end = 5*(page-1) + 4;
		int	size = userList.size();
		
		
		for(int i = start; i <= end; i++){
			if(i > size - 1)
				break;
			User item = userList.get(i);
			userListPart.add(item);
		}
			
		
		model.addAttribute("userListPart", userListPart);
		model.addAttribute("page",page);
		model.addAttribute("size",size);
		model.addAttribute("method", method);
		model.addAttribute("inqueryInfo", inqueryInfo);
		
		
		return "/user/list2";

	}
	
}
