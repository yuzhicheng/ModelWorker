package org.xadzkd.controller;

import org.xadzkd.domain.Document;
import org.xadzkd.domain.PersonApply;
import org.xadzkd.domain.User;
import org.xadzkd.service.DocumentService;
import org.xadzkd.service.PersonApplyService;
import org.xadzkd.service.UserService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMethod;

import org.xadzkd.tool.VerifyStatus;
import org.xadzkd.tool.CurrentUser;
import org.xadzkd.tool.DataDictionary;
@Controller
@RequestMapping("/personapply")
public class PersonApplyController {
	
	@Autowired
	PersonApplyService personApplyService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DocumentService documentService;
	
	@RequestMapping("/access")
	public String apply(HttpServletRequest request,HttpServletResponse response,Model model,RedirectAttributes attr){
		/*
		 * 判断申请是否结束，若结束，则提示用户
		 * */
		VerifyStatus verifyStatus = VerifyStatus.getInstance();
		/*verifyStatus.setStatus(0);*/
		int status = verifyStatus.getStatus();
		
		if(status != 0){
			String message = "申请已结束";
			model.addAttribute("message", message);
			return "/personapply/promp";
		}
		
		
		User user = null;
		/*
		 * 判断用户是否登录，若没登录，提醒登录。否则判断是集体用户还是个人用户，若是集体用户，则提醒用户申请集体劳模
		 * */
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		if(currentUser == null)
		{
			String message = "请先登录！！";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		else if(currentUser.getIsPerson()){
			user = currentUser.getPersonUser();
		}
		else {
			String message = "请到集体劳模申请模块申请!";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		String username = user.getUsername();
		
		
		
		/*
		 * 判断用户是否已经申请过，若是，则提醒
		 * */
		
		if(personApplyService.findByUsername(username) != null){
			String message = "您已经申请了，请不要重复申请";
			model.addAttribute("message", message);
			return "/personapply/promp";
		}
		
		/*
		 * 判断结束，重定向到申请界面
		 * */
		attr.addAttribute("username", username);
		return "redirect:/personapply/showdetails";
	}
	
	@RequestMapping("/showdetails")
	public String showDetails(String username,Model model,HttpServletRequest request){
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
		
		User user = userService.findByUsername(username);
		
		model.addAttribute("user",user);
		
		return "personapply/showdetails";
	}

	
	@RequestMapping("/applyform")
	public String applyForm(String username,Model model,HttpServletRequest request){
		//获取数据字典
		DataDictionary dataDictionary = DataDictionary.getInstance(request);
		//获取劳模称号
		ArrayList<String> advancedPersonList = dataDictionary.getAdvancedPerson();
		model.addAttribute("advancedPersonList", advancedPersonList);
		
		PersonApply personApply = personApplyService.findByUsername(username);
		if(personApply!=null){
			model.addAttribute("personApply", personApply);
		} else {
			model.addAttribute("username", username);
		}
		
		return "/personapply/applyform";
	}
	
	@RequestMapping("/save")
	public String save(PersonApply pa, Model model,RedirectAttributes attr){
		
		pa.setResult(0);
		String username = pa.getUsername();
		personApplyService.addPersonApply(pa);
		
		/*model.addAttribute("username",username);*/
		attr.addAttribute("username", username);
		
		return "redirect:/personapply/documentAccess";
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
		 * 若是集体用户，则提醒用户到集体劳模变更模块变更
		 * */
		User user = null;
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		if(currentUser == null)
		{
			String message = "请先登录！！";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		else if(currentUser.getIsPerson()){
			user = currentUser.getPersonUser();
		} else {
			String message = "请到集体劳模变更模块变更!";
			model.addAttribute("message",message);
			return "/personapply/promp";
		}
		String username = user.getUsername();
			
		/*
		 * 判断该个人用户是否申请，若没申请，则提醒用户到个人申请模块申请
		 * */
		PersonApply personApply = personApplyService.findByUsername(username);
		if(personApply == null){
			String message = "请先到个人劳模申请进行申请";
			model.addAttribute("message", message);
			return "/personapply/promp";
		}

		attr.addAttribute("username", username);
		
		return "redirect:/personapply/applyform";
	}
	
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	public String edit(PersonApply personApply, Model model,RedirectAttributes attr){
		personApplyService.update(personApply);
		
		String username = personApply.getUsername();
		
		attr.addAttribute("username", username);
/*		Document document = documentService.find(username);
		
		model.addAttribute("document",document);*/
		
		return "redirect:/personapply/documentAccess";
	}
	
	@RequestMapping("/documentAccess")
	public String documentAccess(String username,Model model){
		Document document = documentService.find(username);
		if(document != null){
			model.addAttribute("document", document);
		} else {
			model.addAttribute("username", username);
		}
		
		return "/personapply/document";
	}
	
	@RequestMapping("/saveDocument")
	public String saveDocument(Document document,Model model){
		documentService.addDocument(document);
		
		String message = "您已申请成功！";
		
		model.addAttribute("message", message);
		
		return "/personapply/promp";
	}
	
	@RequestMapping("/editDocument")
	public String editDocument(Document document, Model model) {
		documentService.update(document);
		
		String message = "您的申请更改成功！";
		
		model.addAttribute("message", message);
		
		return "/personapply/promp";
		
	}

}
