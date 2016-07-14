package org.xadzkd.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xadzkd.domain.AdvancedPerson;
import org.xadzkd.domain.StatusChange;
import org.xadzkd.domain.User;
import org.xadzkd.service.AdvancedPersonService;
import org.xadzkd.service.StatusChangeService;
import org.xadzkd.service.UserService;
import org.xadzkd.tool.DataDictionary;

@Controller
@RequestMapping("/StatusManage")
public class StatusChangeController {
	
	@Autowired
	UserService userService;
	@Autowired
	AdvancedPersonService advancedPersonService;
	@Autowired
	StatusChangeService statusChangeService;
	
	@RequestMapping("/JumpToChangePage")
	public String jumpToChangePage(Long ap_id,Model model,HttpServletRequest request)
	{
		System.out.println(ap_id);
		if(ap_id!=null)
		{
			AdvancedPerson advancedPerson=advancedPersonService.get(ap_id);
			User user=userService.findByUsername(advancedPerson.getUsername());
			//model.addAttribute("advancedPerson",advancedPerson);
			model.addAttribute("user",user);
			model.addAttribute("personcondition",DataDictionary.getInstance(request).getPersonStatus().get(user.getPersoncondition()));
		}
		return "/user/statusChange";
	}
	
	@RequestMapping("/Change")
	public String changeStatus(int status,String cause,String idCard)
	{
		User user=userService.findByIdcard(idCard);
		user.setPersoncondition(status);
		userService.update(user);
		StatusChange sc=new StatusChange();
		sc.setUsername(user.getUsername());
		sc.setPersoncondition(status);
		sc.setCause(cause);
		java.util.Date date=new java.util.Date();
		java.sql.Date data_sql=new java.sql.Date(date.getTime());
		sc.setCreateDate(data_sql);
		statusChangeService.add(sc);
		return "redirect:/user/personinfo";
	}
}
