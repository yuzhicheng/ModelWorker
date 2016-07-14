package org.xadzkd.tool;

import java.io.IOException;
import java.io.RandomAccessFile;

import javax.servlet.http.HttpServletRequest;
/**
 * 对数据字典进行添加，其他人请勿使用此类
 * @author green
 * @since 2015.8.25
 * @version 1.0
 */
public class DataDictionaryAddItem {
	/**
	 * 对NO所指的数据字典添加一项内容
	 * @param NO
	 * @param value
	 */
	public void addItem(int NO,String value,HttpServletRequest request){
		String name = null;
		//判断插入序列
		switch(NO){
		case DataDictionary.ADVANCED_COLLECTIVE:
			name = "先进集体荣誉称号";
			break;
		case DataDictionary.ADVANCED_PERSON:
			name = "先进个人荣誉称号";
			break;
		case DataDictionary.TREATMENT:
			name = "享受待遇";
			break;
		case DataDictionary.UNION:
			name = "隶属公会";
			break;
		case DataDictionary.PERSON_STATUS:
			name = "个人状态";
			break;
		case DataDictionary.IDENTIFY_STATUS:
			name = "认定状态";
			break;
		case DataDictionary.NATION:
			name = "民族";
		}
		
		try{
			RandomAccessFile file = new RandomAccessFile(request.getSession().getServletContext().getRealPath("/")+"WEB-INF/config/dataDictionary.xml", "rw");
			String lineSeparator = System.getProperty("line.separator", "/n"); 
			int length = 0;
			while(true){
				String content = file.readLine();
				if(content.contains("</content>")){
					//找到根元素的结尾
					break;
				}else{
					length += content.length() + lineSeparator.length();
				}
			}
			file.seek(length);
		
			
			file.write(("<object name=\""+ name + "\">"+ lineSeparator).getBytes("UTF-8"));
			file.write(("<item>" + value +"</item>"+ lineSeparator).getBytes("UTF-8"));
			file.write(("</object>" + lineSeparator).getBytes("UTF-8"));
			file.write("</content>".getBytes("UTF-8"));
			file.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
