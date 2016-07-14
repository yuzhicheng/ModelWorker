package org.xadzkd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xadzkd.domain.AdvancedGroup;
import org.xadzkd.domain.AdvancedPerson;
import org.xadzkd.domain.Group;
import org.xadzkd.domain.User;
import org.xadzkd.service.AdvancedGroupService;
import org.xadzkd.service.AdvancedPersonService;
import org.xadzkd.service.GroupService;
import org.xadzkd.service.JExcelService;
import org.xadzkd.service.UserService;
import org.xadzkd.templet.ExcelErrorInfo;
import org.xadzkd.templet.TempletType;
import org.xadzkd.tool.DataDictionary;

import com.alibaba.fastjson.JSONObject;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


@Controller
@RequestMapping("/DataImprotAndExport")
public class JExcelController {
	@Autowired
	JExcelService jExcelService;
	@Autowired
	UserService userService;
	@Autowired
	AdvancedPersonService advancedPersonService;
	@Autowired
	GroupService groupService;
	@Autowired
	AdvancedGroupService advancedGroupService;
	
	@RequestMapping("/DataExport")
	public String jumpToDataExport() {
		return "/user/DataExport";
	}
	
	@RequestMapping("/ExportAdvancedPersonDataAll")
	public void createAdvancedPersonDataExcel(String fileName, HttpServletRequest request,HttpServletResponse response) throws IOException, RowsExceededException, WriteException{
		OutputStream os = response.getOutputStream();//取得输出流
	    response.reset();//清空输出流	    
	    //下面是对中文文件名的处理
	    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
	    fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
	    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"GBK")+".xls");
	    response.setContentType("application/msexcel");//定义输出类型
	    List<AdvancedPerson> advancedPersonList=advancedPersonService.findAll();
	    if(advancedPersonList.size()==0)
	    {
	    	return;
	    }
	    List<User> userList=new LinkedList<User>();
	    for(AdvancedPerson advancedPerson:advancedPersonList)
	    {
	    	userList.add(userService.findByUsername(advancedPerson.getUsername()));
	    }
	    DataDictionary dataDictionary=DataDictionary.getInstance(request);
	    jExcelService.ExportAdvancedPersonDataToExcel(os, userList,advancedPersonList,dataDictionary);
	}
	
	@RequestMapping("/ExportAdvancedPersonDataByYear")
	public void createAdvancedPersonDataExcel(String fileName,Integer startYear,Integer endYear,HttpServletRequest request,HttpServletResponse response) throws IOException, RowsExceededException, WriteException{
		OutputStream os = response.getOutputStream();//取得输出流
	    response.reset();//清空输出流	    
	    //下面是对中文文件名的处理
	    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
	    fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
	    if(startYear.intValue()!=endYear.intValue()) fileName=startYear.toString()+"-"+endYear.toString()+fileName;
	    else fileName=startYear.toString()+fileName;
	    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"GBK")+".xls");
	    response.setContentType("application/msexcel");//定义输出类型
	    List<AdvancedPerson> advancedPersonList=new LinkedList<AdvancedPerson>();
	    for(int count=startYear;count<=endYear;count++)
	    {
	    	advancedPersonList.addAll(advancedPersonService.findByYear(count));
	    }
	    List<User> userList=new LinkedList<User>();
	    if(advancedPersonList.size()==0)
	    {
	    	return;
	    }
	    for(AdvancedPerson advancedPerson:advancedPersonList)
	    {
	    	userList.add(userService.findByUsername(advancedPerson.getUsername()));
	    }
	    DataDictionary dataDictionary=DataDictionary.getInstance(request);
	    jExcelService.ExportAdvancedPersonDataToExcel(os, userList,advancedPersonList,dataDictionary);
	}
	
	@RequestMapping("/ExportAdvancedPersonDataBySearch")
	public void createAdvancedPersonSearchDataExcel(String fileName, HttpServletRequest request,HttpServletResponse response) throws IOException, RowsExceededException, WriteException{
		OutputStream os = response.getOutputStream();//取得输出流
	    response.reset();//清空输出流	    
	    //下面是对中文文件名的处理
	    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
	    fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
	    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"GBK")+".xls");
	    response.setContentType("application/msexcel");//定义输出类型
	    List<User> userList=(List<User>)request.getSession().getAttribute("userInfoList");
	    List<AdvancedPerson> advancedPersonList=(List<AdvancedPerson>)request.getSession().getAttribute("personList");
	    if(userList.size()==0) return;
	    DataDictionary dataDictionary=DataDictionary.getInstance(request);
	    jExcelService.ExportAdvancedPersonDataToExcel(os, userList,advancedPersonList,dataDictionary);
	}
	
	@RequestMapping("/ExportAdvancedGroupDataAll")
	public void createAdvancedGroupDataExcel(String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException, RowsExceededException, WriteException{
		OutputStream os = response.getOutputStream();//取得输出流
	    response.reset();//清空输出流	    
	    //下面是对中文文件名的处理
	    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
	    fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
	    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"GBK")+".xls");
	    response.setContentType("application/msexcel");//定义输出类型
	    List<AdvancedGroup> advancedGroupList=advancedGroupService.findAll();
	    if(advancedGroupList.size()==0)
	    {
	    	return;
	    }
	    List<Group> groupList=new LinkedList<Group>();
	    for(AdvancedGroup advancedGroup:advancedGroupList)
	    {
	    	groupList.add(groupService.findByGroupName(advancedGroup.getGroupname()));
	    }
	    DataDictionary dataDictionary=DataDictionary.getInstance(request);
	    jExcelService.ExportAdvancedGroupDataToExcel(os, groupList,advancedGroupList,dataDictionary);
	}
	
	@RequestMapping("/ExportAdvancedGroupDataByYear")
	public void createAdvancedGroupDataExcel(String fileName,Integer startYear,Integer endYear,HttpServletRequest request,HttpServletResponse response) throws IOException, RowsExceededException, WriteException{
		OutputStream os = response.getOutputStream();//取得输出流
	    response.reset();//清空输出流	    
	    //下面是对中文文件名的处理
	    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
	    fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
	    if(startYear.intValue()!=endYear.intValue()) fileName=startYear.toString()+"-"+endYear.toString()+fileName;
	    else fileName=startYear.toString()+fileName;
	    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"GBK")+".xls");
	    response.setContentType("application/msexcel");//定义输出类型
	    List<AdvancedGroup> advancedGroupList=new LinkedList<AdvancedGroup>();
	    for(int count=startYear;count<=endYear;count++)
	    {
	    	advancedGroupList.addAll(advancedGroupService.findByYear(count));
	    }
	    if(advancedGroupList.size()==0)
	    {
	    	return;
	    }
	    List<Group> groupList=new LinkedList<Group>();
	    for(AdvancedGroup advancedGroup:advancedGroupList)
	    {
	    	groupList.add(groupService.findByGroupName(advancedGroup.getGroupname()));
	    }
	    DataDictionary dataDictionary=DataDictionary.getInstance(request);
	    jExcelService.ExportAdvancedGroupDataToExcel(os, groupList,advancedGroupList,dataDictionary);
	}
	
	@RequestMapping("/ExportAdvancedGroupDataBySearch")
	public void createAdvancedGroupSearchDataExcel(String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException, RowsExceededException, WriteException{
		OutputStream os = response.getOutputStream();//取得输出流
	    response.reset();//清空输出流	    
	    //下面是对中文文件名的处理
	    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
	    fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
	    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"GBK")+".xls");
	    response.setContentType("application/msexcel");//定义输出类型
	    List<AdvancedGroup> advancedGroupList=(List<AdvancedGroup>)request.getSession().getAttribute("groupList");
	    List<Group> groupList=(List<Group>)request.getSession().getAttribute("groupInfoList");
	    if(groupList.size()==0) return;
	    DataDictionary dataDictionary=DataDictionary.getInstance(request);
	    jExcelService.ExportAdvancedGroupDataToExcel(os, groupList,advancedGroupList,dataDictionary);
	}

	@RequestMapping("/DownloadTemplet")
	public void creatTempletExcel(String typeName,HttpServletResponse response) throws IOException
	{
		OutputStream os = response.getOutputStream();//取得输出流
	    response.reset();//清空输出流	    
	    //下面是对中文文件名的处理
	    response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
	    String fileName;
	    TempletType type;
	    if(typeName.contentEquals("AdvancedPerson"))
	    {
	    	type=TempletType.AdvancedPerson;
	    	fileName="先进个人导入模板";
	    }
	    else if(typeName.contentEquals("AdvancedGroup"))
	    {	
	    	type=TempletType.AdvancedGroup;
	    	fileName="先进集体导入模板";
	    }
	    else if(typeName.contentEquals("UserAccount"))
	    {
	    	type=TempletType.UserAccount;
	    	fileName="个人账号导入模板";
	    }
	    else if(typeName.contentEquals("GroupAccount"))
	    {
	    	type=TempletType.GroupAccount;
	    	fileName="集体账号导入模板";
	    }
	    else
	    {
	    	type=TempletType.Unknow;
	    	fileName="Unknow";
	    }
	    if(type!=TempletType.Unknow)
	    {
		    fileName = java.net.URLEncoder.encode(fileName,"UTF-8");
		    response.setHeader("Content-Disposition","attachment;filename="+new String(fileName.getBytes("UTF-8"),"GBK")+".xls");
		    response.setContentType("application/msexcel");//定义输出类型
		    //生成模板
		    jExcelService.createExcelTemplet(type, os);
		}
	}
	
	@ResponseBody
	@RequestMapping("/ImportAdvancedPersonData")
	public String importAdvancedPerson(int type,HttpServletRequest request) throws BiffException, IOException{
		File file=new File((String) request.getSession().getAttribute("fileUrl"));
		InputStream is=new FileInputStream(file);
		DataDictionary dataDictionary=DataDictionary.getInstance(request);
		
		
		Map<String,String> map = new HashMap<String,String>();
		List<ExcelErrorInfo> errorList=new LinkedList<ExcelErrorInfo>();
		List<User> userList=jExcelService.getUserData(is, errorList, type,dataDictionary);
		if(errorList.size()!=0)
		{
			request.getSession().setAttribute("errorList",errorList);
			map.put("stauts", "error");
		}
		else
		{
			for(int count=0;count<userList.size();count++)
			{
				try
				{
					userList.get(count).setUsername(
							userService.findByIdcard(userList.get(count).getIdCard())
							.getUsername()
					);
					userList.get(count).setId(
							userService.findByIdcard(userList.get(count).getIdCard())
							.getId()
					);
				}
				catch(java.lang.NullPointerException e)
				{
					errorList.add(new ExcelErrorInfo(count+2, 0, "身份证为"+userList.get(count).getIdCard()+"的用户不存在"));
				}
			}
			if(errorList.size()!=0)
			{
				request.getSession().setAttribute("errorList",errorList);
				map.put("stauts", "error");
				return JSONObject.toJSONString(map);
			}
			is=new FileInputStream(file);
			List<AdvancedPerson> advancedPersonList=jExcelService.getAdvancedPersonData(is,errorList, type, dataDictionary);
			if(errorList.size()!=0)
			{
				request.getSession().setAttribute("errorList",errorList);
				map.put("stauts", "error");
			}
			else 
			{
				for(User user:userList) userService.update(user);
				request.getSession().setAttribute("uesrList",userList);
				for(int i=0;i<advancedPersonList.size();i++)
				{
					advancedPersonList.get(i).setUsername(userList.get(i).getUsername());
					List<AdvancedPerson> apl=advancedPersonService.findByIdcard(userList.get(i).getIdCard());
					if(apl!=null)
					{
						boolean exist=false;
						for(int j=0;j<apl.size();j++)
						{
							if(apl.get(j).getYear().intValue()==advancedPersonList.get(i).getYear().intValue()
							 &&apl.get(j).getTitle().intValue()==advancedPersonList.get(i).getTitle().intValue())
							{
								exist=true;
								advancedPersonList.get(i).setId(apl.get(j).getId());
								break;
							}
						}
						if(exist) advancedPersonService.update(advancedPersonList.get(i));
						else advancedPersonService.add(advancedPersonList.get(i));
					}
					else advancedPersonService.add(advancedPersonList.get(i));
				}
				request.getSession().setAttribute("advancedPersonList",advancedPersonList);
				map.put("stauts", "succeed");
			}
		}
		return JSONObject.toJSONString(map);
	}
	
	@ResponseBody
	@RequestMapping("/ImportAdvancedGroupData")
	public String importAdvancedGroup(int type,HttpServletRequest request) throws BiffException, IOException{
		File file=new File((String) request.getSession().getAttribute("fileUrl"));
		InputStream is=new FileInputStream(file);
		DataDictionary dataDictionary=DataDictionary.getInstance(request);
		
		Map<String,String> map = new HashMap<String,String>();
		List<ExcelErrorInfo> errorList=new LinkedList<ExcelErrorInfo>();
		List<Group> groupList=jExcelService.getGroupData(is, errorList, type,dataDictionary);
		if(errorList.size()!=0)
		{
			request.getSession().setAttribute("errorList",errorList);
			map.put("stauts", "error");
		}
		else
		{
			for(int count=0;count<groupList.size();count++)
			{
				try
				{
					groupList.get(count).setId(
							groupService.findByGroupName(groupList.get(count).getGroupname())
							.getId()
					);
				}
				catch(java.lang.NullPointerException e)
				{
					errorList.add(new ExcelErrorInfo(count+2, 0, "ID为"+groupList.get(count).getGroupname()+"的集体不存在"));
				}
			}
			if(errorList.size()!=0)
			{
				request.getSession().setAttribute("errorList",errorList);
				map.put("stauts", "error");
				return JSONObject.toJSONString(map);
			}
			is=new FileInputStream(file);
			List<AdvancedGroup> advancedGroupList=jExcelService.getAdvancedGroupData(is,errorList, type, dataDictionary);
			if(errorList.size()!=0)
			{
				request.getSession().setAttribute("errorList",errorList);
				map.put("stauts", "error");
			}
			else 
			{
				for(Group group:groupList) groupService.update(group);
				request.getSession().setAttribute("groupList",groupList);
				for(int i=0;i<advancedGroupList.size();i++)
				{
					advancedGroupList.get(i).setGroupname(groupList.get(i).getGroupname());
					List<AdvancedGroup> agl=advancedGroupService.findByGroupname(advancedGroupList.get(i).getGroupname());
					if(agl!=null)
					{
						boolean exist=false;
						for(int j=0;j<agl.size();j++)
						{
							if(agl.get(j).getYear().intValue()==advancedGroupList.get(i).getYear().intValue()
							 &&agl.get(j).getTitle().intValue()==advancedGroupList.get(i).getTitle().intValue())
							{
								exist=true;
								advancedGroupList.get(i).setId(agl.get(j).getId());
								break;
							}
						}
						if(exist) advancedGroupService.update(advancedGroupList.get(i));
						else advancedGroupService.add(advancedGroupList.get(i));
					}
					else advancedGroupService.add(advancedGroupList.get(i));
				}
				request.getSession().setAttribute("advancedGroupList",advancedGroupList);
				map.put("stauts", "succeed");
			}
		}
		return JSONObject.toJSONString(map);
	}

	@ResponseBody
	@RequestMapping("/ImportUserAccountData")
	public String importUserAccount(int type,HttpServletRequest request) throws BiffException, IOException{
		File file=new File((String) request.getSession().getAttribute("fileUrl"));
		InputStream is=new FileInputStream(file);
		DataDictionary dataDictionary=DataDictionary.getInstance(request);
		
		Map<String,String> map = new HashMap<String,String>();
		List<ExcelErrorInfo> errorList=new LinkedList<ExcelErrorInfo>();
		List<User> userList=jExcelService.getUserData(is, errorList, type,dataDictionary);
		if(errorList.size()!=0)
		{
			request.getSession().setAttribute("errorList",errorList);
			map.put("stauts", "error");
		}
		else
		{
			for(int count=0;count<userList.size();count++)
			{
				if(userService.findByUsername(userList.get(count).getUsername())==null)
				{
					userList.get(count).setPassword(userList.get(count).getUsername());
					userService.addUser(userList.get(count));
				}
				else
				{
					errorList.add(new ExcelErrorInfo(count+2, 0, userList.get(count).getUsername()+"已存在"));
				}
			}
			if(errorList.size()!=0)
			{
				request.getSession().setAttribute("errorList",errorList);
				map.put("stauts", "error");
			}
			else
			{
				//for(User user:userList) userService.addUser(user);
				map.put("stauts", "succeed");
			}
		}
		return JSONObject.toJSONString(map);
	}
	
	@ResponseBody
	@RequestMapping("/ImportGroupAccountData")
	public String importGroupAccount(int type,HttpServletRequest request) throws BiffException, IOException{
		File file=new File((String) request.getSession().getAttribute("fileUrl"));
		InputStream is=new FileInputStream(file);
		DataDictionary dataDictionary=DataDictionary.getInstance(request);
		
		Map<String,String> map = new HashMap<String,String>();
		List<ExcelErrorInfo> errorList=new LinkedList<ExcelErrorInfo>();
		List<Group> groupList=jExcelService.getGroupData(is, errorList, type,dataDictionary);
		if(errorList.size()!=0)
		{
			request.getSession().setAttribute("errorList",errorList);
			map.put("stauts", "error");
		}
		else
		{
			for(int count=0;count<groupList.size();count++)
			{
				if(groupService.findByGroupName(groupList.get(count).getGroupname())==null)
				{
					groupList.get(count).setPassword(groupList.get(count).getGroupname());
					groupService.addGroup(groupList.get(count));
				}
				else
				{
					errorList.add(new ExcelErrorInfo(count+2, 0, groupList.get(count).getGroupname()+"已存在"));
				}
			}
			if(errorList.size()!=0)
			{
				request.getSession().setAttribute("errorList",errorList);
				map.put("stauts", "error");
			}
			else
			{
				//for(Group group:groupList) groupService.addGroup(group);
				map.put("stauts", "succeed");
			}
		}
		return JSONObject.toJSONString(map);
	}
	
	@RequestMapping("/Error")
	public String jumpToErrorInfo(HttpServletRequest request,Model model)
	{	
		model.addAttribute("errorList", request.getSession().getAttribute("errorList"));
		return "/user/import_result";
	}
}
