package org.xadzkd.tool;

import java.util.ArrayList;

/**
 * 保存当前的审核进行的状态，在默认下是审核结束
 * 单例模式类，需要使用VerifyStatus.getInstance获取类
 * @author green
 *
 */
public class VerifyStatus {
	int status;
	public static ArrayList<String> statusNames = new ArrayList<String>();
	static{
		statusNames.add("申请开始");
		statusNames.add("申请结束");
		statusNames.add("审核开始");
		statusNames.add("审核结束");
	}
	
	
	static final int APPLY_BEGIN = 0; //申请开始
	static final int APPLY_END = 1;		//申请结束
	static final int VERIFY_BEGIN = 2;	//审核开始
	static final int VERIFY_END = 3;	//审核结束
	private VerifyStatus(){
		status = VERIFY_END;
	}
	
	static private VerifyStatus verifyStatus = null;
	
	static public VerifyStatus getInstance(){
		if(verifyStatus == null)
			verifyStatus = new VerifyStatus();
		return verifyStatus;
	}
	
	/**
	 * 设置当前状态，非法情况下返回false
	 * @param status
	 * @return
	 */
	public boolean setStatus(int status){
		if(status != APPLY_BEGIN && status !=  APPLY_END && status !=  VERIFY_BEGIN && status !=  VERIFY_END)
			return false;//不合法
		this.status = status;
		return true;
	}
	
	/**
	 * 获取当前状态
	 * @return 返回值相对与本类中的四个状态常量
	 */
	public int getStatus(){
		return this.status;
	}
}
