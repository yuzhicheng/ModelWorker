package org.xadzkd.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xadzkd.domain.AdvancedGroup;
import org.xadzkd.domain.AdvancedPerson;
import org.xadzkd.domain.Group;
import org.xadzkd.domain.User;
import org.xadzkd.service.AdvancedGroupService;
import org.xadzkd.service.AdvancedPersonService;
import org.xadzkd.service.GroupService;
import org.xadzkd.service.JExcelService;
import org.xadzkd.service.UserService;
import org.xadzkd.tool.DataDictionary;

@Controller
@RequestMapping("/user")
public class SearchController {
	@Autowired
	AdvancedPersonService personServer;
	@Autowired
	AdvancedGroupService groupServer;
	
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	JExcelService jExcelService;
	
	@RequestMapping("/personinfo")
	public String personinfo(Model model,HttpServletRequest request) {
		//返回先进个人荣誉称号的数据字典
		ArrayList<String> advancePerson = DataDictionary.getInstance(request).getAdvancedPerson();
		model.addAttribute("advancePerson", advancePerson);
		//获取返回个人状态的数据字典
		ArrayList<String> personstate=DataDictionary.getInstance(request).getPersonStatus();
		model.addAttribute("personstate", personstate);
		//返回认定状态的数据字典
		ArrayList<String> identitystate=DataDictionary.getInstance(request).getIdentifyStatus();
		model.addAttribute("identitystate", identitystate);
		return "user/personsearch";
	}
	@RequestMapping("/personsearch")
	public String personsearch(Integer persontype, String persontextvalue, HttpServletRequest request,Model model) {
		List<AdvancedPerson> personList=null;
		//返回先进个人荣誉称号的数据字典
	    ArrayList<String> advancePerson = DataDictionary.getInstance(request).getAdvancedPerson();
	    //获取返回个人状态的数据字典
	    ArrayList<String> personstate = DataDictionary.getInstance(request).getPersonStatus();
	    //返回民族的数据字典
	  	ArrayList<String> nation=DataDictionary.getInstance(request).getNation();
	  	//返回性别的数据字典
	  	ArrayList<String> sex=DataDictionary.getInstance(request).getSex();
	    //返回认定状态的数据字典
	  	ArrayList<String> identitystate=DataDictionary.getInstance(request).getIdentifyStatus();
	    int b=advancePerson.size();//先进个人荣誉称号长度
	    int c=personstate.size();//个人状态数据字典长度
	    int d=identitystate.size();//认定状态数据字典长度
	    if(persontype==-2)personList=(List<AdvancedPerson>) personServer.findByIdcard(persontextvalue);
	    if(persontype==-3)personList=personServer.findByName(persontextvalue);
	    if(persontype>=0&&persontype<=b)personList=personServer.findByTitle(persontype);
	    if(persontype>=1000&&persontype<=c+1000)personList=personServer.findByCondition(persontype-1000);
	    if(persontype>=10000&&persontype<=d+10000)personList=personServer.findByState(persontype-10000);
	    System.out.println(personList);
	    //返回享受待遇的数据字典
	    ArrayList<String> personTreatment=DataDictionary.getInstance(request).getTreatment();	    
	    model.addAttribute("advancePerson", advancePerson);
		model.addAttribute("personstate", personstate);
		model.addAttribute("personnation", nation);
		model.addAttribute("personsex", sex);
		model.addAttribute("identitystate", identitystate);
	    model.addAttribute("result","ok");
	    model.addAttribute("personTreatment", personTreatment);
		model.addAttribute("personList", personList);
		model.addAttribute("length",personList.size());
		if(personList!=null)
		{
			List<User> userInfoList=new LinkedList<User>();
			for(AdvancedPerson ap:personList)
			{
				userInfoList.add(userService.findByUsername(ap.getUsername()));
			}
			model.addAttribute("userInfoList", userInfoList);
			for(int i=0;i<userInfoList.size();i++){
			System.out.println(userInfoList.get(i));
			}
			request.getSession().setAttribute("userInfoList", userInfoList);
			request.getSession().setAttribute("personList",personList);
		}
		return "user/personsearch";
	}
	@RequestMapping("/groupinfo")
	public String groupinfo(Model model,HttpServletRequest request) {
		ArrayList<String> advancedGroup=DataDictionary.getInstance(request).getAdvancedCollective();
	    model.addAttribute("advancedGroup",advancedGroup);
		return "user/groupsearch";
	}
	@RequestMapping("/groupsearch")
	public String groupsearch(Integer type, String textvalue, HttpServletRequest request,Model model) {
		List<AdvancedGroup> groupList = null;
		List<Map<String,String>> groupinfo=null;
		ArrayList<String> advancedGroup=DataDictionary.getInstance(request).getAdvancedCollective();
		int a=advancedGroup.size();
		if(type==-2)
		{groupList=groupServer.findByYear(Integer.parseInt(textvalue));
		 groupinfo=groupServer.findByYearExtends(Integer.parseInt(textvalue));
		}
		if(type==-3)
		{groupList=groupServer.findByName(textvalue);
		 groupinfo=groupServer.findByNameExtends(textvalue);
		}
		if(type>=0&&type<=a){
		 groupList=groupServer.findByTitle(type);
		 groupinfo=groupServer.findByTitleExtends(type);
		 System.out.println(groupinfo);
		}
		model.addAttribute("result","ok");
		model.addAttribute("advancedGroup",advancedGroup);
		model.addAttribute("groupList", groupList);
		model.addAttribute("groupinfo", groupinfo);
		model.addAttribute("length",groupList.size());
		if(groupList!=null)
		{
			List<Group> groupInfoList=new LinkedList<Group>();
			for(AdvancedGroup ag:groupList)
			{
				groupInfoList.add(groupService.findByGroupName(ag.getGroupname()));
			}
			model.addAttribute("groupInfoList", groupInfoList);
			request.getSession().setAttribute("groupInfoList", groupInfoList);
			request.getSession().setAttribute("groupList",groupList);
		}
		return "user/groupsearch";
	}
	@RequestMapping("/allpreviousinfo")
	public String allpreviousinfo(Model model,HttpServletRequest request) {
		return "user/allprevioussearch";
	}
	@RequestMapping("/allprevioussearch")
	public String allprevioussearch(String option, String year,Model model,Integer page,HttpServletRequest request) {
		List<AdvancedGroup> groupList = null;
		List<AdvancedPerson> personList=null;
		List<AdvancedPerson> personListPart=new ArrayList<AdvancedPerson>();
		//返回先进个人荣誉称号的数据字典
	    ArrayList<String> advancePerson = DataDictionary.getInstance(request).getAdvancedPerson();
	    //返回先进集体荣誉称号的数据字典
		ArrayList<String> advancedGroup=DataDictionary.getInstance(request).getAdvancedCollective();
		//返回享受待遇的数据字典
		ArrayList<String> personTreatment=DataDictionary.getInstance(request).getTreatment();
		 //获取返回个人状态的数据字典
	    ArrayList<String> personstate = DataDictionary.getInstance(request).getPersonStatus();
		if("0".equals(option)){
		  personList=personServer.findByYear(Integer.parseInt(year));
		  model.addAttribute("personList",personList);
		  model.addAttribute("tablevalue","person");
		  int size = personList.size();
		  int maxpage;
		  if(size%2==0) maxpage=size/2; else maxpage=size/2+1;
		  if(page == null||page<=0)	page = 1;	 
		  if(page>maxpage) page=maxpage;
			int start = 2*(page-1), end = 2*(page-1) + 1;		
			for(int i = start; i <= end; i++){
				if(i > size - 1)
					break;
				AdvancedPerson item=personList.get(i);
				personListPart.add(item);
			}
			model.addAttribute("size",size);
			model.addAttribute("maxpage",maxpage);
		}
		if("1".equals(option)){
			  groupList=groupServer.findByYear(Integer.parseInt(year));
			  model.addAttribute("groupList",groupList);
			  model.addAttribute("tablevalue","group");
		}
		model.addAttribute("option", option);
		model.addAttribute("year", year);
		model.addAttribute("userListPart", personListPart);
		model.addAttribute("page",page);
		
		model.addAttribute("advancedGroup",advancedGroup);
		model.addAttribute("advancePerson", advancePerson);
		model.addAttribute("personTreatment", personTreatment);
		model.addAttribute("personstate", personstate);
		model.addAttribute("result","ok");
		if(personList!=null)
		{
			List<User> userInfoList=new LinkedList<User>();
			for(AdvancedPerson ap:personList)
			{
				userInfoList.add(userService.findByUsername(ap.getUsername()));
			}
			model.addAttribute("userInfoList", userInfoList);
			request.getSession().setAttribute("userInfoList", userInfoList);
			request.getSession().setAttribute("personList",personList);
		}
		if(groupList!=null)
		{
			List<Group> groupInfoList=new LinkedList<Group>();
			for(AdvancedGroup ag:groupList)
			{
				groupInfoList.add(groupService.findByGroupName(ag.getGroupname()));
			}
			model.addAttribute("groupInfoList", groupInfoList);
			request.getSession().setAttribute("groupInfoList", groupInfoList);
			request.getSession().setAttribute("groupList",groupList);
		}
		return "user/allprevioussearch";
		 
	}
}

