package org.xadzkd.templet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ImportTemplet {
//	public static final String[][] AdvancedPerson_DB={
//			{"name","idCard","sex","email","address","birthPlace","nation","unionName","post","phone","degree","personcondition"},
//			{"title","state","treatment","year","bodycondition","difficulty","workcondition","achievement"}
//	};
//	public static final String[][] AdvancedPerson_Excel={
//			{"姓名","身份证号","性别","邮箱","住址","籍贯","民族","所属公会","职务","电话","学历","人身状态"},
//			{"称号","称号状态","享受待遇","年份","身体状态","家庭情况","就业情况","突出事迹"}
//	};
//	public static final String[][] AdvancedGroup_Excel={
//			{"集体ID","集体名","地址","联系电话","所属公会","负责人"},
//			{"年份","称号","突出事迹"}
//	};
	
	public static final String[] UserAttr={
		//	  0     1      2     3     4    5     6      7      8    9     10    11      12      
			"姓名","身份证号","性别","邮箱","住址","籍贯","民族","所属公会","职务","电话","学历","人身状态","用户ID"
	};
	public static final String[] AdvancedPersonAttr={
		//    0      1       2      3      4       5       6       7           	
			"称号","称号状态","享受待遇","年份","身体状态","家庭情况","就业情况","突出事迹"
	};
	
	public static final String[] GroupAttr={
		//    0       1     2      3       4      5	
			"集体ID","集体名","地址","联系电话","所属公会","负责人"
	};
	public static final String[] AdvancedGroupAttr={
		//    0    1      2
			"年份","称号","突出事迹"
	};
	
	
	private static Map<String,Integer[]> userDataPattern=new HashMap<String,Integer[]>();
	private static Map<String,Integer[]> advancedDataPattern=new HashMap<String,Integer[]>();

	public static Map<String,Integer[]> getUserDataPattern(int type) {
		String columnNo="columnNo";
		String columnName="columnName";
		String contextType="type";
		switch(type)
		{
			case 1:
				Integer[] columnNoArray={0,1,2,3,4,5,6,7,8,9,10,11};
				Integer[] columnNameArray={0,1,2,3,4,5,6,7,8,9,10,11};
				Integer[] contextTypeArray={-1,-1,7,-1,-1,-1,6,2,-1,-1,-1,4};
				userDataPattern.put(columnNo, columnNoArray);
				userDataPattern.put(columnName, columnNameArray);
				userDataPattern.put(contextType, contextTypeArray);
				break;
			case 2:
				Integer[] columnNoArray1={0,1,2,3,4,5,6,7,8,9,10,11,12};
				Integer[] columnNameArray1={0,1,2,3,4,5,6,7,8,9,10,11,12};
				Integer[] contextTypeArray1={-1,-1,7,-1,-1,-1,6,2,-1,-1,-1,4,-1};
				userDataPattern.put(columnNo, columnNoArray1);
				userDataPattern.put(columnName, columnNameArray1);
				userDataPattern.put(contextType, contextTypeArray1);
				break;
		}
		return userDataPattern;
	}
	public static Map<String,Integer[]> getAdvancedPersonDataPattern(int type) {
		String columnNo="columnNo";
		String columnName="columnName";
		String contextType="type";
		switch(type)
		{
			case 1:
				Integer[] columnNoArray={12,13,14,15,16,17,18,19};
				Integer[] columnNameArray={0,1,2,3,4,5,6,7};
				Integer[] contextTypeArray={1,5,3,-1,-1,-1,-1,-1};
				advancedDataPattern.put(columnNo, columnNoArray);
				advancedDataPattern.put(columnName, columnNameArray);
				advancedDataPattern.put(contextType, contextTypeArray);
				break;
		}
		return advancedDataPattern;
	}
	
	public static Map<String,Integer[]> getGroupDataPattern(int type) {
		String columnNo="columnNo";
		String columnName="columnName";
		String contextType="type";
		switch(type)
		{
			case 1:
				Integer[] columnNoArray={0,1,2,3,4,5};
				Integer[] columnNameArray={0,1,2,3,4,5};
				Integer[] contextTypeArray={-1,-1,-1,-1,-1,-1};
				userDataPattern.put(columnNo, columnNoArray);
				userDataPattern.put(columnName, columnNameArray);
				userDataPattern.put(contextType, contextTypeArray);
				break;
		}
		return userDataPattern;
	}
	public static Map<String,Integer[]> getAdvancedGroupDataPattern(int type) {
		String columnNo="columnNo";
		String columnName="columnName";
		String contextType="type";
		switch(type)
		{
			case 1:
				Integer[] columnNoArray={6,7,8};
				Integer[] columnNameArray={0,1,2};
				Integer[] contextTypeArray={-1,0,-1};
				userDataPattern.put(columnNo, columnNoArray);
				userDataPattern.put(columnName, columnNameArray);
				userDataPattern.put(contextType, contextTypeArray);
				break;
		}
		return userDataPattern;
	}
}