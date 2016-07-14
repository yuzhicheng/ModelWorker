package org.xadzkd.controller;


import java.util.ArrayList;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xadzkd.service.AdvancedPersonService;
import org.xadzkd.tool.DataDictionary;

@Controller
@RequestMapping("/user")
public class PersonStatisticController 
{
	@Autowired
	AdvancedPersonService advancedPersonService;
	
	@RequestMapping("/personstatistics")
 	public String statistic(HttpServletRequest request , HttpServletResponse response , Model model , String type) 
	{
		if(type == null)
		{
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("persontype"))
				{
					type = cookie.getValue();
				}
			}
		}
		
		if(type!=null)
		{			
			Cookie cookie = new Cookie("persontype", type);
			response.addCookie(cookie);
			cookie.setMaxAge(3600);
			int num=Integer.parseInt(type);
			int sum = 0, tmp = 0;
			//List<Double> personDoubles= new ArrayList<Double>();
			//Map<String, Integer> personMap=new LinkedHashMap<String , Integer>();
			ArrayList<Integer> valueList=new ArrayList<Integer>();
			switch (num) {
			case 1:		
				ArrayList<String> personTitleList=DataDictionary.getInstance(request).getAdvancedPerson();
				for(int i=0;i<personTitleList.size();i++)
				{
					tmp=advancedPersonService.findByTitle(i).size();
					sum+=tmp;
					valueList.add(tmp);
				}
				model.addAttribute("valueList", valueList);
				model.addAttribute("nameList", personTitleList);
				model.addAttribute("sum",sum);
				break;
			case 2:
				ArrayList<String> personConditionList=DataDictionary.getInstance(request).getPersonStatus();
				for(int i=0;i<personConditionList.size();i++)
				{
					tmp=advancedPersonService.findByCondition(i).size();
					sum+=tmp;
					valueList.add(tmp);
				}
				model.addAttribute("valueList", valueList);
				model.addAttribute("nameList", personConditionList);
				model.addAttribute("sum",sum);
				break;
				
			case 3:
				ArrayList<String> personUnionList=DataDictionary.getInstance(request).getUnion();
				for(int i=0;i<personUnionList.size();i++)
				{
					tmp=advancedPersonService.findByUnion(i).size();
					sum+=tmp;
					valueList.add(tmp);
				}
				model.addAttribute("valueList", valueList);
				model.addAttribute("nameList", personUnionList);
				model.addAttribute("sum",sum);
				break;
				
			case 4:
				ArrayList<String> personNationList=DataDictionary.getInstance(request).getNation();
				for(int i=0;i<personNationList.size();i++)
				{
					tmp=advancedPersonService.findByNation(i).size();
					sum+=tmp;
					valueList.add(tmp);
				}
				model.addAttribute("valueList", valueList);
				model.addAttribute("nameList", personNationList);
				model.addAttribute("sum",sum);
				break;
		default:
			break;
			}	
		}
		return "user/personStatistics";
	}
	/*
	private static double get4Double(double a){  
	    DecimalFormat df=new DecimalFormat("0.0000");  
	    return new Double(df.format(a).toString());  
	} */
}
