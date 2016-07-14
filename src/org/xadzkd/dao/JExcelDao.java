package org.xadzkd.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.AdvancedGroup;
import org.xadzkd.domain.AdvancedPerson;
import org.xadzkd.domain.Group;
import org.xadzkd.domain.User;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.xadzkd.templet.*;
import org.xadzkd.tool.DataDictionary;


@Repository
public class JExcelDao {
	@Autowired
	SessionFactory sessionFactory;
	
    public void createExcelTemplet(TempletType type, OutputStream os) throws WriteException,IOException{
        //创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
        //创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
        List<String> columnNameList=new LinkedList<String>();
        Integer[] columnNameArray;
        switch(type)
        {
        	case AdvancedPerson:
        		columnNameArray=ImportTemplet.getUserDataPattern(1).get("columnName");
        		for(int index=0;index<columnNameArray.length;index++)
        			columnNameList.add(ImportTemplet.UserAttr[columnNameArray[index]]);
        		columnNameArray=ImportTemplet.getAdvancedPersonDataPattern(1).get("columnName");
        		for(int index=0;index<columnNameArray.length;index++)
        			columnNameList.add(ImportTemplet.AdvancedPersonAttr[columnNameArray[index]]);
        		break;
        	case AdvancedGroup:
        		columnNameArray=ImportTemplet.getGroupDataPattern(1).get("columnName");
        		for(int index=0;index<columnNameArray.length;index++)
        			columnNameList.add(ImportTemplet.GroupAttr[columnNameArray[index]]);
        		columnNameArray=ImportTemplet.getAdvancedGroupDataPattern(1).get("columnName");
        		for(int index=0;index<columnNameArray.length;index++)
        			columnNameList.add(ImportTemplet.AdvancedGroupAttr[columnNameArray[index]]);
        		break;
        	case UserAccount:
        		columnNameArray=ImportTemplet.getUserDataPattern(2).get("columnName");
        		for(int index=0;index<columnNameArray.length;index++)
        			columnNameList.add(ImportTemplet.UserAttr[columnNameArray[index]]);
        		break;
        	case GroupAccount:
        		columnNameArray=ImportTemplet.getGroupDataPattern(1).get("columnName");
        		for(int index=0;index<columnNameArray.length;index++)
        			columnNameList.add(ImportTemplet.GroupAttr[columnNameArray[index]]);
        		break;
        	default:
        		System.out.println("Unknow Type");
        		return;
        }
    	for(int index=0;index<columnNameList.size();index++)
    	{
    		Label newColumn=new Label(index,0,columnNameList.get(index));
        	try {
				sheet.addCell(newColumn);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
        //把创建的内容写入到输出流中，并关闭输出流
        workbook.write();
        workbook.close();
        os.close();
    }
	
	public void ExportAdvancedPersonDataToExcel(OutputStream os,List<User> userList,List<AdvancedPerson> advancedPersonList, DataDictionary dataDictionary) throws IOException, WriteException {
		//创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
		//创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		String[][] columnNameList=ExportTemplet.AdvancedPerson_Excel;
        for(int i=0,p=0;i<columnNameList.length;p+=columnNameList[i].length,i++)
        {
        	//System.out.println(i);
        	for(int j=0;j<columnNameList[i].length;j++)
        	{
        		Label newColumn=new Label(j+p,0,columnNameList[i][j]);
            	try {
					sheet.addCell(newColumn);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        //从数据库获取数据填入表格
        for(int row=1;row<=userList.size();row++)
        {
        	List<String> tuple=getAdvancedPersonInfo(userList.get(row-1), advancedPersonList.get(row-1),dataDictionary);
        	for(int index=0;index<tuple.size();index++)
        	{
        		Label newLabel=new Label(index,row,tuple.get(index));
            	try {
					sheet.addCell(newLabel);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
		workbook.write();
        workbook.close();
        os.close();
	}
    
	public List<String> getAdvancedPersonInfo(User user,AdvancedPerson advancedPerson, DataDictionary dataDictionary) {
    	List<String> tuple=new LinkedList<String>();
    	tuple.add(user.getName().toString());
    	tuple.add(user.getIdCard().toString());
    	tuple.add(dataDictionary.getSex().get(user.getSex()));
    	tuple.add(user.getEmail().toString());
    	tuple.add(user.getAddress().toString());
    	tuple.add(user.getBirthPlace().toString());
    	tuple.add(dataDictionary.getNation().get(user.getNation()));
    	tuple.add(dataDictionary.getUnion().get(user.getUnionName()));
    	tuple.add(user.getPost().toString());
    	tuple.add(user.getPhone().toString());
    	tuple.add(user.getDegree().toString());
    	tuple.add(dataDictionary.getPersonStatus().get(user.getPersoncondition()));
    	
    	tuple.add(dataDictionary.getAdvancedPerson().get(advancedPerson.getTitle()));
    	tuple.add(dataDictionary.getIdentifyStatus().get(advancedPerson.getState()));
    	tuple.add(dataDictionary.getTreatment().get(advancedPerson.getTreatment()));
    	tuple.add(advancedPerson.getYear().toString());
    	tuple.add(advancedPerson.getBodycondition().toString());
    	tuple.add(advancedPerson.getDifficulty().toString());
    	tuple.add(advancedPerson.getWorkcondition().toString());
    	tuple.add(advancedPerson.getAchievement().toString());
    	return tuple;
    }
    
    public void ExportAdvancedGroupDataToExcel(OutputStream os,List<Group> groupList,List<AdvancedGroup> advancedGroupList, DataDictionary dataDictionary) throws IOException, WriteException {
		//创建工作薄
        WritableWorkbook workbook = Workbook.createWorkbook(os);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet("First Sheet",0);
		//创建要显示的内容,创建一个单元格，第一个参数为列坐标，第二个参数为行坐标，第三个参数为内容
		String[][] columnNameList=ExportTemplet.AdvancedGroup_Excel;
        for(int i=0,p=0;i<columnNameList.length;p+=columnNameList[i].length,i++)
        {
        	//System.out.println(i);
        	for(int j=0;j<columnNameList[i].length;j++)
        	{
        		Label newColumn=new Label(j+p,0,columnNameList[i][j]);
            	try {
					sheet.addCell(newColumn);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
        //从数据库获取数据填入表格
        for(int row=1;row<=groupList.size();row++)
        {
        	List<String> tuple=getAdvancedGroupInfo(groupList.get(row-1), advancedGroupList.get(row-1),dataDictionary);
        	for(int index=0;index<tuple.size();index++)
        	{
        		Label newLabel=new Label(index,row,tuple.get(index));
            	try {
					sheet.addCell(newLabel);
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
		workbook.write();
        workbook.close();
        os.close();
    }
    
    public List<String> getAdvancedGroupInfo(Group group,AdvancedGroup advancedGroup, DataDictionary dataDictionary){
    	List<String> tuple=new LinkedList<String>();
    	tuple.add(group.getName().toString());
    	tuple.add(group.getAddress().toString());
    	tuple.add(group.getPhone().toString());
    	tuple.add(group.getBelong().toString());
    	tuple.add(group.getManagerName().toString());
    	
    	tuple.add(advancedGroup.getYear().toString());
    	tuple.add(dataDictionary.getAdvancedCollective().get(advancedGroup.getTitle()));
    	tuple.add(advancedGroup.getAchievement().toString());
    	return tuple;
    }
    
    public List<User> getUserData(InputStream is, List<ExcelErrorInfo> errorList, int type,DataDictionary dataDictionary) throws BiffException, IOException{
    	List<User> userList=new LinkedList<User>();
    	Workbook workbook = Workbook.getWorkbook(is);
    	Sheet sheet=workbook.getSheet(0);
    	
    	Map<String,Integer[]> userDataPattern=ImportTemplet.getUserDataPattern(type);
    	Integer[] columnNoArray=userDataPattern.get("columnNo");
		Integer[] columnNameArray=userDataPattern.get("columnName");
		Integer[] contextTypeArray=userDataPattern.get("type");
		
    	for(int row=1;row<sheet.getRows();row++)
    	{
    		User newUser=new User();
    		boolean hasError=false;
    		String error="";
    		for(int index=0;index<columnNoArray.length;index++)
    		{
    			 Cell cell = sheet.getCell(columnNoArray[index],row);
    			 switch(contextTypeArray[index])
    			 {
	    			 case -1:
	    				 switch(columnNameArray[index])
	    				 {
		    				 case 0:
		    					 newUser.setName(cell.getContents());break;
			    			 case 1:
			    				 newUser.setIdCard(cell.getContents());break;
			    			 case 3:
			    				 newUser.setEmail(cell.getContents());break;
			    			 case 4:
			    				 newUser.setAddress(cell.getContents());break;
			    			 case 5:
			    				 newUser.setBirthPlace(cell.getContents());break;
			    			 case 8:
			    				 newUser.setPost(cell.getContents());break;
			    			 case 9:
			    				 newUser.setPhone(cell.getContents());break;
			    			 case 10:
			    				 newUser.setDegree(cell.getContents());break;
			    			 case 12:
			    				 newUser.setUsername(cell.getContents());break;
			    			 default:
			    				 hasError=true;
			    				 errorList.add(
			    						 new ExcelErrorInfo(
			    								 row+1, 
			    								 columnNoArray[index]+1,
			    								 ImportTemplet.UserAttr[columnNameArray[index]]+"输入有误"
			    						 )
			    				 );
			    				 break;
	    				 };
	    				 break;
	    			 case DataDictionary.SEX:
	    				 int sex=dataDictionary.getSexNo(cell.getContents());
	    				 if(sex==-1)
	    				 {
	    					 hasError=true;
	    					 errorList.add(
		    						 new ExcelErrorInfo(
		    								 row+1, 
		    								 columnNoArray[index]+1,
		    								 ImportTemplet.UserAttr[columnNameArray[index]]+"输入有误"
		    						 )
		    				 );
	    				 }
	    				 else newUser.setSex(sex);
	    				 break;
	    			 case DataDictionary.NATION:
	    				 int nation=dataDictionary.getNationNo(cell.getContents());
	    				 if(nation==-1)
	    				 {
	    					 hasError=true;
	    					 errorList.add(
		    						 new ExcelErrorInfo(
		    								 row+1, 
		    								 columnNoArray[index]+1,
		    								 ImportTemplet.UserAttr[columnNameArray[index]]+"输入有误"
		    						 )
		    				 );
	    				 }
	    				 else newUser.setNation(nation);
	    				 break;
	    			 case DataDictionary.UNION:
	    				 int union=dataDictionary.getUnionNo(cell.getContents());
	    				 if(union==-1)
	    				 {
	    					 hasError=true;
	    					 errorList.add(
		    						 new ExcelErrorInfo(
		    								 row+1, 
		    								 columnNoArray[index]+1,
		    								 ImportTemplet.UserAttr[columnNameArray[index]]+"输入有误"
		    						 )
		    				 );
	    				 }
	    				 else newUser.setUnionName(union);
	    				 break;
	    			 case DataDictionary.PERSON_STATUS:
	    				 int personcondition=dataDictionary.getPersonStatusNo(cell.getContents());
	    				 if(personcondition==-1)
	    				 {
	    					 hasError=true;
	    					 errorList.add(
		    						 new ExcelErrorInfo(
		    								 row+1, 
		    								 columnNoArray[index]+1,
		    								 ImportTemplet.UserAttr[columnNameArray[index]]+"输入有误"
		    						 )
		    				 );
	    				 }
	    				 else newUser.setPersoncondition(personcondition);
	    				 break;
	    			 default:
	    				 hasError=true;
	    				 errorList.add(
	    						 new ExcelErrorInfo(
	    								 row+1, 
	    								 columnNoArray[index]+1,
	    								 ImportTemplet.UserAttr[columnNameArray[index]]+"输入有误"
	    						 )
	    				 );
	    				 break;
    			 }
    		}
			if(!hasError) 
			{
				userList.add(newUser);
				System.out.println("add a new user");
			}
			else System.out.println(error);
    	}
        workbook.close();
        is.close();
    	return userList;
    }
    
    public List<AdvancedPerson> getAdvancedPersonData(InputStream is,List<ExcelErrorInfo> errorList, int type, DataDictionary dataDictionary) throws BiffException, IOException{
    	List<AdvancedPerson> advancedPersonList=new LinkedList<AdvancedPerson>();
    	Workbook workbook = Workbook.getWorkbook(is);
    	Sheet sheet=workbook.getSheet(0);
    	
    	Map<String,Integer[]> userDataPattern=ImportTemplet.getAdvancedPersonDataPattern(type);
    	Integer[] columnNoArray=userDataPattern.get("columnNo");
		Integer[] columnNameArray=userDataPattern.get("columnName");
		Integer[] contextTypeArray=userDataPattern.get("type");
		
    	for(int row=1;row<sheet.getRows();row++)
    	{
    		AdvancedPerson newAdvancedPerson=new AdvancedPerson();
    		boolean hasError=false;
    		for(int index=0;index<columnNoArray.length;index++)
    		{
    			 Cell cell = sheet.getCell(columnNoArray[index],row);
    			 switch(contextTypeArray[index])
    			 {
	    			 case -1:
	    				 switch(columnNameArray[index])
	    				 {
		    				 case 3:
		    					 try{
		    						 Integer year=Integer.parseInt(cell.getContents());
			    					 newAdvancedPerson.setYear(year);
		    					 }
		    					 catch(NumberFormatException e)
		    					 {
		    						 errorList.add(
				    						 new ExcelErrorInfo(
				    								 row+1, 
				    								 columnNoArray[index]+1,
				    								 ImportTemplet.AdvancedPersonAttr[columnNameArray[index]]+"输入有误"
				    						 )
				    				 );
		    					 }
		    					 break;
			    			 case 4:
			    				 newAdvancedPerson.setBodycondition(cell.getContents());break;
			    			 case 5:
			    				 newAdvancedPerson.setDifficulty(cell.getContents());break;
			    			 case 6:
			    				 newAdvancedPerson.setWorkcondition(cell.getContents());break;
			    			 case 7:
			    				 newAdvancedPerson.setAchievement(cell.getContents());break;
			    			 default:
			    				 hasError=true;
			    				 errorList.add(
			    						 new ExcelErrorInfo(
			    								 row+1, 
			    								 columnNoArray[index]+1,
			    								 ImportTemplet.AdvancedPersonAttr[columnNameArray[index]]+"输入有误"
			    						 )
			    				 );
			    				 break;
	    				 };
	    				 break;
	    			 case DataDictionary.ADVANCED_PERSON:
	    				 int title=dataDictionary.getAdvancedPersonNo(cell.getContents());
	    				 if(title==-1)
	    				 {
	    					 hasError=true;
	    					 errorList.add(
		    						 new ExcelErrorInfo(
		    								 row+1, 
		    								 columnNoArray[index]+1,
		    								 ImportTemplet.AdvancedPersonAttr[columnNameArray[index]]+"输入有误"
		    						 )
		    				 );
	    				 }
	    				 else newAdvancedPerson.setTitle(title);
	    				 break;
	    			 case DataDictionary.IDENTIFY_STATUS:
	    				 int status=dataDictionary.getIdentifyStatusNo(cell.getContents());
	    				 if(status==-1)
	    				 {
	    					 hasError=true;
	    					 errorList.add(
		    						 new ExcelErrorInfo(
		    								 row+1, 
		    								 columnNoArray[index]+1,
		    								 ImportTemplet.AdvancedPersonAttr[columnNameArray[index]]+"输入有误"
		    						 )
		    				 );
	    				 }
	    				 else newAdvancedPerson.setState(status);
	    				 break;
	    			 case DataDictionary.TREATMENT:
	    				 int treatment=dataDictionary.getTreatmentNo(cell.getContents());
	    				 if(treatment==-1)
	    				 {
	    					 hasError=true;
	    					 errorList.add(
		    						 new ExcelErrorInfo(
		    								 row+1, 
		    								 columnNoArray[index]+1,
		    								 ImportTemplet.AdvancedPersonAttr[columnNameArray[index]]+"输入有误"
		    						 )
		    				 );
	    				 }
	    				 else newAdvancedPerson.setTreatment(treatment);
	    				 break;
	    			 default:
	    				 hasError=true;
	    				 errorList.add(
	    						 new ExcelErrorInfo(
	    								 row+1, 
	    								 columnNoArray[index]+1,
	    								 ImportTemplet.AdvancedPersonAttr[columnNameArray[index]]+"输入有误"
	    						 )
	    				 );
	    				 break;
    			 }
    		}
			if(!hasError) 
			{
				advancedPersonList.add(newAdvancedPerson);
				System.out.println("add a new AdvancedPerson");
			}
    	}
        workbook.close();
        is.close();
    	return advancedPersonList;
    }
    
    public List<Group> getGroupData(InputStream is, List<ExcelErrorInfo> errorList, int type,DataDictionary dataDictionary) throws BiffException, IOException{
    	List<Group> groupList=new LinkedList<Group>();
    	Workbook workbook = Workbook.getWorkbook(is);
    	Sheet sheet=workbook.getSheet(0);
    	
    	Map<String,Integer[]> groupDataPattern=ImportTemplet.getGroupDataPattern(type);
    	Integer[] columnNoArray=groupDataPattern.get("columnNo");
		Integer[] columnNameArray=groupDataPattern.get("columnName");
		Integer[] contextTypeArray=groupDataPattern.get("type");
		
    	for(int row=1;row<sheet.getRows();row++)
    	{
    		Group newGroup=new Group();
    		boolean hasError=false;
    		String error="";
    		for(int index=0;index<columnNoArray.length;index++)
    		{
    			 Cell cell = sheet.getCell(columnNoArray[index],row);
    			 switch(contextTypeArray[index])
    			 {
	    			 case -1:
	    				 switch(columnNameArray[index])
	    				 {
		    				 case 0:
		    					 newGroup.setGroupname(cell.getContents());break;
			    			 case 1:
			    				 newGroup.setName(cell.getContents());break;
			    			 case 2:
			    				 newGroup.setAddress(cell.getContents());break;
			    			 case 3:
			    				 newGroup.setPhone(cell.getContents());break;
			    			 case 4:
			    				 newGroup.setBelong(cell.getContents());break;
			    			 case 5:
			    				 newGroup.setManagerName(cell.getContents());break;
			    			 default:
			    				 hasError=true;
			    				 errorList.add(
			    						 new ExcelErrorInfo(
			    								 row+1, 
			    								 columnNoArray[index]+1,
			    								 ImportTemplet.GroupAttr[columnNameArray[index]]+"输入有误"
			    						 )
			    				 );
			    				 break;
	    				 };
	    				 break;
//	    			 case DataDictionary.UNION:
//	    				 int union=dataDictionary.getUnionNo(cell.getContents());
//	    				 if(union==-1)
//	    				 {
//	    					 hasError=true;
//	    					 errorList.add(
//		    						 new ExcelErrorInfo(
//		    								 row+1, 
//		    								 columnNoArray[index]+1,
//		    								 ImportTemplet.AdvancedPerson_Excel[0][columnNameArray[index]]+"输入有误"
//		    						 )
//		    				 );
//	    				 }
//	    				 else newGroup.setBelong(union);
//	    				 break;
	    			 default:
	    				 hasError=true;
	    				 errorList.add(
	    						 new ExcelErrorInfo(
	    								 row+1, 
	    								 columnNoArray[index]+1,
	    								 ImportTemplet.GroupAttr[columnNameArray[index]]+"输入有误"
	    						 )
	    				 );
	    				 break;
    			 }
    		}
			if(!hasError) 
			{
				groupList.add(newGroup);
				System.out.println("add a new group");
			}
			else System.out.println(error);
    	}
        workbook.close();
        is.close();
    	return groupList;
    }
    
    public List<AdvancedGroup> getAdvancedGroupData(InputStream is, List<ExcelErrorInfo> errorList, int type,DataDictionary dataDictionary) throws BiffException, IOException{
    	List<AdvancedGroup> advancedGroupList=new LinkedList<AdvancedGroup>();
    	Workbook workbook = Workbook.getWorkbook(is);
    	Sheet sheet=workbook.getSheet(0);
    	
    	Map<String,Integer[]> advancedGroupDataPattern=ImportTemplet.getAdvancedGroupDataPattern(type);
    	Integer[] columnNoArray=advancedGroupDataPattern.get("columnNo");
		Integer[] columnNameArray=advancedGroupDataPattern.get("columnName");
		Integer[] contextTypeArray=advancedGroupDataPattern.get("type");
		
    	for(int row=1;row<sheet.getRows();row++)
    	{
    		AdvancedGroup newAdvancedGroup=new AdvancedGroup();
    		boolean hasError=false;
    		String error="";
    		for(int index=0;index<columnNoArray.length;index++)
    		{
    			 Cell cell = sheet.getCell(columnNoArray[index],row);
    			 switch(contextTypeArray[index])
    			 {
	    			 case -1:
	    				 switch(columnNameArray[index])
	    				 {
		    				 case 0:
		    					 try{
		    						 Integer year=Integer.parseInt(cell.getContents());
			    					 newAdvancedGroup.setYear(year);
		    					 }
		    					 catch(NumberFormatException e)
		    					 {
		    						 errorList.add(
				    						 new ExcelErrorInfo(
				    								 row+1, 
				    								 columnNoArray[index]+1,
				    								 ImportTemplet.AdvancedGroupAttr[columnNameArray[index]]+"输入有误"
				    						 )
				    				 );
		    					 }
		    					 break;
			    			 case 2:
			    				 newAdvancedGroup.setAchievement(cell.getContents());break;
			    			 default:
			    				 hasError=true;
			    				 errorList.add(
			    						 new ExcelErrorInfo(
			    								 row+1, 
			    								 columnNoArray[index]+1,
			    								 ImportTemplet.AdvancedGroupAttr[columnNameArray[index]]+"输入有误"
			    						 )
			    				 );
			    				 break;
	    				 };
	    				 break;
	    			 case DataDictionary.ADVANCED_COLLECTIVE:
	    				 int title=dataDictionary.getAdvancedCollectiveNo(cell.getContents());
	    				 if(title==-1)
	    				 {
	    					 hasError=true;
	    					 errorList.add(
		    						 new ExcelErrorInfo(
		    								 row+1, 
		    								 columnNoArray[index]+1,
		    								 ImportTemplet.AdvancedGroupAttr[columnNameArray[index]]+"输入有误"
		    						 )
		    				 );
	    				 }
	    				 else newAdvancedGroup.setTitle(title);
	    				 break;
	    			 default:
	    				 hasError=true;
	    				 errorList.add(
	    						 new ExcelErrorInfo(
	    								 row+1, 
	    								 columnNoArray[index]+1,
	    								 ImportTemplet.GroupAttr[columnNameArray[index]]+"输入有误"
	    						 )
	    				 );
	    				 break;
    			 }
    		}
			if(!hasError) 
			{
				advancedGroupList.add(newAdvancedGroup);
				System.out.println("add a new advancedGroup");
			}
			else System.out.println(error);
    	}
        workbook.close();
        is.close();
    	return advancedGroupList;
    }
}