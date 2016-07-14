package org.xadzkd.tool;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * 
 * 数据字典类，读取config/dataDictionary.xml文件<br/>
 * 将  		隶属公会  	先进集体荣誉称号  			先进个人荣誉称号  			享受待遇 		个人状态			认定状态<br/>
 * 分别存入	union	advancedCollective	advancedPerson		treatment   personStatus	identifyStatus<br/>
 * Array中.
 * @author green
 * @version 2.0
 * @since 2015.8.19 1.0<br/>
 * 2015.8.25 2.0<br/>
 * 2015.8.26 2.1<br/>
 * 
 *
 */
public class DataDictionary {
	public final static int ADVANCED_COLLECTIVE = 0;
	public final static int ADVANCED_PERSON = 1;
	public final static int UNION = 2;
	public final static int TREATMENT = 3;
	public final static int PERSON_STATUS = 4;
	public final static int IDENTIFY_STATUS = 5;
	public final static int NATION = 6;
	public final static int SEX = 7;
	private DataDictionary(HttpServletRequest request){
		update(request);
	}
	
	static private DataDictionary  dataDictionary = null;
	
	static public DataDictionary getInstance(HttpServletRequest request){
		if(dataDictionary == null){
			dataDictionary = new DataDictionary(request);
		}
		return dataDictionary;
	}
	
	private ArrayList<String> union = new ArrayList<String>();
	private ArrayList<String> advancedCollective = new ArrayList<String>();
	private ArrayList<String> advancedPerson = new ArrayList<String>();
	private ArrayList<String> treatment = new ArrayList<String>();
	private ArrayList<String> personStatus = new ArrayList<String>();
	private ArrayList<String> identifyStatus = new ArrayList<String>();
	private ArrayList<String> sex = new ArrayList<String>();
	private ArrayList<String> nation = new ArrayList<String>();
	/**
	 * 重新读取(动态更新)dataDictionary的数据字典
	 */
	public void update(HttpServletRequest request){
		union.clear();
		advancedCollective.clear();
		advancedPerson.clear();
		treatment.clear();
		personStatus.clear();
		identifyStatus.clear();
		nation.clear();
		File xmlFile = new File(request.getSession().getServletContext().getRealPath("/")+"WEB-INF/config/dataDictionary.xml");
		DocumentBuilder builder = null;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		
		try{
			builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			Element root = document.getDocumentElement();
			NodeList childNodes = root.getChildNodes();
			for(int i=0; i < childNodes.getLength(); i++){
				Node node = childNodes.item(i);
				if("object".equals(node.getNodeName())){
					if("隶属公会".equals(node.getAttributes().getNamedItem("name").getNodeValue())){
						insertArray(union, node);
					
					}
					if("先进集体荣誉称号".equals(node.getAttributes().getNamedItem("name").getNodeValue())){
						insertArray(advancedCollective, node);
					
					}
					if("先进个人荣誉称号".equals(node.getAttributes().getNamedItem("name").getNodeValue())){
						insertArray(advancedPerson, node);
					
					}
					if("享受待遇".equals(node.getAttributes().getNamedItem("name").getNodeValue())){
						insertArray(treatment,node);
					
					}
					if("个人状态".equals(node.getAttributes().getNamedItem("name").getNodeValue())){
						insertArray(personStatus,node);
					
					}
					if("认定状态".equals(node.getAttributes().getNamedItem("name").getNodeValue())){
						insertArray(identifyStatus,node);
					
					}
					if("性别".equals(node.getAttributes().getNamedItem("name").getNodeValue())){
						insertArray(sex, node);
					}
					if("民族".equals(node.getAttributes().getNamedItem("name").getNodeValue())){
						insertArray(nation,node);
					
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 将子目录中的item包含的值，加入相应的arrayList中
	 * @param array
	 * @param node
	 */
	private void insertArray(ArrayList<String> array, Node node){
		NodeList nodeDetail = node.getChildNodes();
		for(int j=0; j < nodeDetail.getLength(); j++ ){
			Node subNode = nodeDetail.item(j);
			if("item".equals(subNode.getNodeName()))
			array.add(subNode.getTextContent());
		}
	}
	@Override
	public String toString(){
		String info = "";
		info += "隶属公会：\n";
		for(int i=0; i < union.size(); i++){
			info += union.get(i) + "\n";
		}
		info += "先进个人荣誉称号:\n";
		for(int i=0; i < advancedPerson.size(); i++){
			info+= advancedPerson.get(i) + "\n";
		}
		
		info += "先进集体荣誉称号:\n";
		for(int i=0; i < advancedCollective.size(); i++){
			info += advancedCollective.get(i) + "\n";
		}
		
		info += "享受待遇:\n";
		for(int i=0; i < treatment.size(); i++){
			info+= treatment.get(i) + "\n";
		}
		info += "个人状态:\n";
		for(int i=0; i < personStatus.size(); i++){
			info+= personStatus.get(i) + "\n";
		}
		
		info += "认定状态:\n";
		for(int i=0; i < identifyStatus.size(); i++){
			info += identifyStatus.get(i) + "\n";
		}
		
		info += "性别:\n";
		for(int i=0; i < sex.size(); i++){
			info += sex.get(i) + "\n";
		}
		
		return info;
	}
	/**
	 * 返回隶属公会的数据字典
	 * @return ArrayList类型，包含所有的表项
	 */
	public ArrayList<String> getUnion() {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(union);
		return tmp;
	}

	
	/**
	 * 返回先进集体荣誉称号的数据字典
	 * @return ArrayList类型，包含所有的表项
	 */
	public ArrayList<String> getAdvancedCollective() {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(advancedCollective);
		return tmp;
	}

	
	/**
	 * 返回先进个人荣誉称号的数据字典
	 * @return ArrayList类型，包含所有的表项
	 */
	public ArrayList<String> getAdvancedPerson() {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(advancedPerson);
		return tmp;
	}

	
	/**
	 * 返回享受待遇的数据字典
	 * @return ArrayList类型，包含所有的表项
	 */
	public ArrayList<String> getTreatment() {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(treatment);
		return tmp;
	}
	/**
	 * 返回个人状态的数据字典
	 * @return ArrayList类型，包含所有的表项
	 */
	public ArrayList<String> getPersonStatus(){
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(personStatus);
		return tmp;
	}
	/**
	 * 返回认定状态的数据字典
	 * @return ArrayList类型，包含所有的表项
	 */
	public ArrayList<String> getIdentifyStatus(){
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(identifyStatus);
		return tmp;
	}
	/**
	 * 返回性别的数据字典
	 * @return ArrayList类型，包含所有的表项
	 */
	public ArrayList<String> getSex(){
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(sex);
		return tmp;
	}
	/**
	 * 返回民族的数据字典
	 * @return
	 */
	public ArrayList<String> getNation() {
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.addAll(nation);
		return tmp;
	}
	/**
	 * 检索隶属公会对应的序列号
	 * @param key 公会名称
	 * @return 序列号
	 */
	public int getUnionNo(String key){
		int i = 0;
		for(i = 0; i < union.size(); i++){
			if(union.get(i).equals(key))
				return i;
		}
		return -1;
	}
	
	/**
	 * 检索先进集体荣誉称号对应的序列号
	 * @param key 进集体荣誉称号
	 * @return 序列号
	 */
	public int getAdvancedCollectiveNo(String key){
		int i=0;
		for(i=0; i < advancedCollective.size(); i++){
			if(advancedCollective.get(i).equals(key))
				return  i;
		}
		return -1;
	}
	
	/**
	 * 检索先进个人荣誉称号对应的序列号
	 * @param key 先进个人荣誉称号
	 * @return 序列号
	 */
	public int getAdvancedPersonNo(String key){
		int i=0;
		for(i=0; i < advancedPerson.size(); i++){
			if(advancedPerson.get(i).equals(key))
				return i;
		}
		return -1;
	}
	
	/**
	 * 检索享受待遇对应的序列号
	 * @param key 享受待遇
	 * @return 序列号
	 */
	public int  getTreatmentNo(String key){
		int i=0;
		for(i=0; i < treatment.size(); i++){
			if(treatment.get(i).equals(key))
				return i;
		}
		return -1;
	}
	
	/**
	 * 检索个人状态对应的序列号
	 * @param key 享受待遇
	 * @return 序列号
	 */
	public int  getPersonStatusNo(String key){
		int i=0;
		for(i=0; i < personStatus.size(); i++){
			if(personStatus.get(i).equals(key))
				return i;
		}
		return -1;
	}
	
	/**
	 * 检索认定状态对应的序列号
	 * @param key 享受待遇
	 * @return 序列号
	 */
	public int  getIdentifyStatusNo(String key){
		int i=0;
		for(i=0; i < identifyStatus.size(); i++){
			if(identifyStatus.get(i).equals(key))
				return i;
		}
		return -1;
	}
	
	/**
	 * 检索认定状态对应的序列号
	 * @param key 享受待遇
	 * @return 序列号
	 */
	public int  getSexNo(String key){
		int i=0;
		for(i=0; i < sex.size(); i++){
			if(sex.get(i).equals(key))
				return i;
		}
		return -1;
	}
	
	/**
	 * 检索认定状态对应的序列号
	 * @param key 享受待遇
	 * @return 序列号
	 */
	public int  getNationNo(String key){
		int i=0;
		for(i=0; i < nation.size(); i++){
			if(nation.get(i).equals(key))
				return i;
		}
		return -1;
	}
}
