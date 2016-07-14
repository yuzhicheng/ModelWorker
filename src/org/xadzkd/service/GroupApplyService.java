package org.xadzkd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.GroupApplyDao;
import org.xadzkd.domain.GroupApply;

/**
 * 集体申请表的服务
 */
@Service
@Transactional
public class GroupApplyService 
{
	@Autowired
	GroupApplyDao groupApplyDao;
	
	public void add(GroupApply ga)
	{
		groupApplyDao.add(ga);
	}
	
	public void update(GroupApply ga)
	{
		groupApplyDao.update(ga);
	}
	
	public void delete(GroupApply ga)
	{
		groupApplyDao.delete(ga);
	}
	
	public GroupApply get(Long id)
	{
		return groupApplyDao.get(id);
	}
	
	public List<GroupApply> findAll()
	{
		return groupApplyDao.findAll();
	}
	
	/**
	 * 返回未认定用户信息
	 * @return
	 */
	public List<Map<String, String>> findAllUnchecked()
	{
		return groupApplyDao.findAllUnchecked();
	}
	
	/**
	 * 返回已认定用户信息
	 * @return
	 */
	public List<Map<String, String>> findAllChecked()
	{
		return groupApplyDao.findAllChecked();
	}
	
	/**
	 * 
	 * @return 新上报用户列表
	 */
	public List<GroupApply> findresult()
	{
		return groupApplyDao.findresult();
	}
	
	public void clearTable()
	{
		groupApplyDao.clearTable();
	}
	
	/**
	 * 按登录名查询
	 * 
	 */
	public GroupApply findByGroupname(String groupname)
	{
		return groupApplyDao.findByGroupname(groupname);
	}
}
