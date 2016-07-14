package org.xadzkd.service;

import org.xadzkd.dao.*;
import org.xadzkd.domain.AdvancedGroup;
import org.xadzkd.domain.AdvancedPerson;
import org.xadzkd.domain.Group;
import org.xadzkd.domain.User;
import org.xadzkd.templet.ExcelErrorInfo;
import org.xadzkd.templet.TempletType;
import org.xadzkd.tool.DataDictionary;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JExcelService {
	@Autowired
	JExcelDao jExcelDao;
	
	public void createExcelTemplet(TempletType type, OutputStream os){
		try {
			jExcelDao.createExcelTemplet(type,os);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ExportAdvancedPersonDataToExcel(OutputStream os,List<User> userList,List<AdvancedPerson> advancedPersonList, DataDictionary dataDictionary) throws WriteException, IOException{
		jExcelDao.ExportAdvancedPersonDataToExcel(os, userList,advancedPersonList,dataDictionary);
	}
	
	public void ExportAdvancedGroupDataToExcel(OutputStream os,List<Group> groupList,List<AdvancedGroup> advancedGroupList, DataDictionary dataDictionary) throws WriteException, IOException{
		jExcelDao.ExportAdvancedGroupDataToExcel(os, groupList, advancedGroupList,dataDictionary);
	}

	public List<User> getUserData(InputStream is, List<ExcelErrorInfo> errorList,int type, DataDictionary dataDictionary) throws BiffException, IOException {
		return jExcelDao.getUserData(is, errorList,type, dataDictionary);
	}
	
	public List<AdvancedPerson> getAdvancedPersonData(InputStream is, List<ExcelErrorInfo> errorList, int type, DataDictionary dataDictionary) throws BiffException, IOException{
		return jExcelDao.getAdvancedPersonData(is, errorList, type, dataDictionary);
	}
	
	public List<Group> getGroupData(InputStream is, List<ExcelErrorInfo> errorList,int type, DataDictionary dataDictionary) throws BiffException, IOException {
		return jExcelDao.getGroupData(is, errorList, type, dataDictionary);
	}
	
	public List<AdvancedGroup> getAdvancedGroupData(InputStream is,List<ExcelErrorInfo> errorList, int type, DataDictionary dataDictionary) throws BiffException, IOException{
		return jExcelDao.getAdvancedGroupData(is, errorList, type, dataDictionary);
	}
	
	public List<String> getAdvancedPersonInfo(User user,AdvancedPerson advancedPerson, DataDictionary dataDictionary)
	{
		return jExcelDao.getAdvancedPersonInfo(user, advancedPerson, dataDictionary);
	}
	
	public List<String> getAdvancedGroupInfo(Group group,AdvancedGroup advancedGroup, DataDictionary dataDictionary)
	{
		return jExcelDao.getAdvancedGroupInfo(group, advancedGroup, dataDictionary);
	}
}
