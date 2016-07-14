package org.xadzkd.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.transaction.jta.platform.internal.JtaSynchronizationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xadzkd.service.GroupApplyService;
import org.xadzkd.service.GroupService;
import org.xadzkd.service.PersonApplyService;
import org.xadzkd.service.UserService;
import org.xadzkd.tool.CurrentUser;
import org.xadzkd.tool.DataDictionary;
import org.xadzkd.tool.DataDictionaryAddItem;
import org.xadzkd.tool.DataDump;
import org.xadzkd.tool.IdentifyImage;
import org.xadzkd.tool.UploadStatus;
import org.xadzkd.tool.VerifyStatus;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import sun.rmi.transport.proxy.HttpReceiveSocket;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	PersonApplyService personApplyService;
	
	@Autowired
	GroupApplyService groupApplyService;
	
	@RequestMapping("/identify")
	public void getIdentifyImage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		BufferedImage image = IdentifyImage.IdentifyImage(request);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("image/jpeg");
		ServletOutputStream sos = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(sos);
		encoder.encode(image);
		sos.flush();
		sos.close();
	}
	@ResponseBody
	@RequestMapping("/doLogin")
	public String doLogin(String username,String password,String identify, HttpServletRequest request, Model model) {
		Map<String,String> map = new HashMap<String,String>();
		if(((String)request.getSession().getAttribute("Identify")).equalsIgnoreCase(identify)){
			if(userService.login(username, password) != null){
				map.put("message", "pass");
				CurrentUser currentUser = new CurrentUser();
				currentUser.setPersonUser(userService.login(username, password));
				request.getSession().setAttribute("currentUser", currentUser);
			}else{
				if(groupService.login(username, password) != null){
					map.put("message", "pass");
					CurrentUser currentUser = new CurrentUser();
					currentUser.setGroupUser(groupService.login(username, password));
					request.getSession().setAttribute("currentUser",currentUser);
				}else{
					map.put("message", "用户名或密码错误");
				}
			}
			
			
		}else{
			model.addAttribute("prompt", new String("验证码错误"));
			map.put("message", "验证码错误");
			
		}
		
		return JSONObject.toJSONString(map);
	}
	
	@RequestMapping("/doLogout")
	public String doLogout(HttpServletRequest request){
		if(request.getSession().getAttribute("currentUser") != null){
			request.getSession().setAttribute("currentUser", null);
		}
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("/dataDictionary")
	public String dataDictionary(HttpServletRequest request,Model model){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		DataDictionary dataDictionary = DataDictionary.getInstance(request);
		ArrayList<String> advancedCollective = dataDictionary.getAdvancedCollective();
		ArrayList<String> advancedPerson = dataDictionary.getAdvancedPerson();
		ArrayList<String> identifyStatus = dataDictionary.getIdentifyStatus();
		ArrayList<String> personStatus = dataDictionary.getPersonStatus();
		ArrayList<String> treatment = dataDictionary.getTreatment();
		ArrayList<String> union = dataDictionary.getUnion();
		ArrayList<String> nation = dataDictionary.getNation();
		model.addAttribute("advancedCollective",advancedCollective);
		model.addAttribute("advancedPerson",advancedPerson);
		model.addAttribute("identifyStatus", identifyStatus);
		model.addAttribute("personStatus", personStatus);
		model.addAttribute("treatment", treatment);
		model.addAttribute("union", union);
		model.addAttribute("nation", nation);
		model.addAttribute("AC_L", advancedCollective.size());
		model.addAttribute("AP_L", advancedPerson.size());
		model.addAttribute("IS_L", identifyStatus.size());
		model.addAttribute("PS_L", personStatus.size());
		model.addAttribute("TM_L",treatment.size());
		model.addAttribute("U_L", union.size());
		model.addAttribute("NA_L",nation.size());
		
		int[] length = {advancedCollective.size(),advancedCollective.size(),identifyStatus.size(),
				personStatus.size(),treatment.size(),union.size(),nation.size()};
		int biggest = 0;
		for(int i=0; i < length.length;i++){
			if(biggest < length[i])
				biggest = length[i];
		}
		model.addAttribute("biggest",biggest);
		return "user/dataDictionary";
	}
	
	@RequestMapping("/upDateDataDictionary")
	public void upDateDataDictionary(HttpServletRequest request){
		DataDictionary.getInstance(request).update(request);
		System.out.println("数据字典被刷新");
	}
	
	@ResponseBody
	@RequestMapping("/addDataDictionaryItem")
	public String addDataDictionaryItem(HttpServletRequest request,int option,String item){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		String result_item = "";
		for(int i=0; i< item.length();i++){
			if(item.charAt(i) != ' ')
				result_item += item.charAt(i);
		}
		if(result_item == "")
			return "false";
		else{
			DataDictionaryAddItem addItem = new DataDictionaryAddItem();
			addItem.addItem(option, result_item, request);
			DataDictionary.getInstance(request).update(request);
			System.out.println("添加字典项成功");
			return "true";
		}
	}
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		return "/user/upload";
	}
	
	
	@RequestMapping("/upload.do")
	public String upload(@RequestParam(value="file",required = false) MultipartFile file,HttpServletRequest request, Model model){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		if(request.getSession().getAttribute("fileUrl") != null){//处理老的没用的
			File oldFile = new File((String)request.getSession().getAttribute("fileUrl"));
			if(oldFile.exists()){
				oldFile.delete();
			}
			request.getSession().setAttribute("fileUrl", null);
		}
		
		
		System.out.println("开始");
		String path = request.getSession().getServletContext().getRealPath("/") + "tmp\\"; 
		String tmp = file.getOriginalFilename();
		int position = -1;
		for(int i=0; i < tmp.length();i++){
			if(tmp.charAt(i) == '.')
				position = i;//找到后缀名的位置
		}
		String fileName = System.currentTimeMillis()+"" + new Random(System.currentTimeMillis()).nextInt(100);//以当前时间和以时间种子为种，生成一个名称
		if(position != -1){
			fileName += tmp.substring(position , tmp.length());
		}//保留后缀
		UploadStatus status = (UploadStatus) request.getSession().getAttribute("status");
		status.start();//重新设置开始时间
		File targetFile = new File(path,fileName);
		if(!targetFile.exists())
			targetFile.mkdirs();
		
		try{
			file.transferTo(targetFile);
		}catch(Exception e){
			e.printStackTrace();
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("当前时间为：" + df.format(new Date()));
		System.out.println("文件传输完成");
		System.out.println("原文件名为：" + file.getOriginalFilename());
		System.out.println("现文件名为：" + fileName);
		System.out.println("存储路径为：" + path);
		request.getSession().setAttribute("fileUrl", path + fileName);
		
		return "/user/upload";
	}
	
	
	@ResponseBody
	@RequestMapping("/progress")
	public String getPregress(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		UploadStatus status = (UploadStatus) request.getSession().getAttribute("status");
		if(status == null){
			System.out.println("the status is null");
			return "";
		}
		else{
			long time = (System.currentTimeMillis() - status.getStartTime()) / 1000 + 1;//单位s
			double speed = (double)status.getBytesRead() / time;//单位是B/s
			double leftTime = (double)(status.getContentLength() - status.getBytesRead()) /speed;//单位是秒
			double precent = (double)status.getBytesRead() / (double)status.getContentLength() * 100;//单位是%
			
			long time_fmt = time;
			double speed_fmt = speed / 1024 / 1024 ; //单位MB/s
			speed_fmt = (new BigDecimal(speed_fmt)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue(); //单位MB/s 保留小数点后两位
			long leftTime_fmt = (long) leftTime;
			int precent_fmt = (int) precent;
			Map<String,String> map = new HashMap<String,String>();
			map.put("time", ""+time_fmt);
			map.put("speed", ""+speed_fmt);
			map.put("leftTime", ""+leftTime_fmt);
			map.put("precent", precent_fmt+"");
			return JSONObject.toJSONString(map);
		}
	}
	
	@RequestMapping("/dump")
	public String dump(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		return "/user/dump";
	}
	
	@ResponseBody
	@RequestMapping("/dumpDataDictionary.do")
	public String dumpDictionary(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "";
		}
		DataDump dump = new DataDump(request);
		if(dump.dumpDataDictionary())
			return "true";
		else
			return "false";
	}
	
	@ResponseBody
	@RequestMapping("/dumpDatabase.do")
	public String dumpDatabase(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		DataDump dump = new DataDump(request);
		if(dump.dumpDataBase())
			return "true";
		else
			return "false";
	}
	
	@RequestMapping("/recover")
	public String recover(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		return "/user/recover";
	}
	
	@ResponseBody
	@RequestMapping("/recoverDataDictionary.do")
	public String recoverDataDictionary(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		DataDump recover = new DataDump(request);
		if(recover.recoverDataDictionaryXML())
			return "true";
		else
			return "false";
	}
	
	@ResponseBody
	@RequestMapping("/recoverDatabase.do")
	public String recoverDatabase(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		DataDump recover = new DataDump(request);
		if(recover.recoverDateBase())
			return "true";
		else
			return "false";
	}
	
	@RequestMapping("/status")
	public String status(Model model,HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return "falsePermission/permissionFail";
		}
		VerifyStatus status = VerifyStatus.getInstance();
		String statusName = VerifyStatus.statusNames.get(status.getStatus());
		model.addAttribute("statusName", statusName);
		model.addAttribute("status",VerifyStatus.statusNames);
		return "/user/status";
	}
	@RequestMapping("/setStatus")
	public void setStatus(int type,HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return ;
		}
		VerifyStatus status = VerifyStatus.getInstance();
		status.setStatus(type);
		return;
	}
	
	@RequestMapping("/clearDatabase")
	public void clearDatabase(HttpServletRequest request){
		CurrentUser currentUser =(CurrentUser)request.getSession().getAttribute("currentUser");
		int permission=(currentUser != null && currentUser.getIsPerson())? currentUser.getPersonUser().getPermission()  :  -1;
		if(permission != 0){
			return ;
		}
		personApplyService.clearTable();
		groupApplyService.clearTable();
		System.out.println("清除数据库");
	}
	
	@RequestMapping("/test")
	public String test(){
		return "user/test";
	}
}

