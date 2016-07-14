package org.xadzkd.controller;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xadzkd.service.AdvancedGroupService;
import org.xadzkd.tool.DataDictionary;


@Controller
@RequestMapping("/user")
public class GroupStatisticsController
{
	@Autowired
	AdvancedGroupService advancedGroupService;
	
	@RequestMapping("/groupstatistics")
	public String statistic(Model model,String type , HttpServletRequest request,HttpServletResponse response)
	{
	
		if(type == null)
		{
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("grouptype"))
				{
					type = cookie.getValue();
				}
			}
		}

		if(type!=null)
		{
			Cookie cookie = new Cookie("grouptype", type);
			response.addCookie(cookie);
			cookie.setMaxAge(3600);
			int num=Integer.parseInt(type);
			int groupsum = 0, tmp = 0;
			//List<Integer> titlenumList=new ArrayList<Integer>();
			//List<Double> titleDoubles= new ArrayList<Double>();
			//Map<String, Integer> titlenumMap=new HashMap<String, Integer>();
			ArrayList<Integer> valueList=new ArrayList<Integer>();
			switch (num) {
			case 1:		
				ArrayList<String > advancedCollective = DataDictionary.getInstance(request).getAdvancedCollective();
				for(int i=0;i<advancedCollective.size();i++)
				{
					tmp=advancedGroupService.findByTitle(i).size();
					groupsum+=tmp;
					valueList.add(tmp);
				}
				model.addAttribute("valueList", valueList);
				model.addAttribute("nameList", advancedCollective);
				model.addAttribute("groupsum",groupsum);
				break;
		default:
			break;
			}
		}
		return "user/groupStatistics" ;
		
	}
	
	private static double get4Double(double a){  
	    DecimalFormat df=new DecimalFormat("0.0000");  
	    return new Double(df.format(a).toString());  
	} 
}
