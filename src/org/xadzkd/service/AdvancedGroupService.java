package org.xadzkd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.AdvancedGroupDao;
import org.xadzkd.domain.AdvancedGroup;

/**
 * 先进集体表服务
 */
@Service
@Transactional
public class AdvancedGroupService 
{
	@Autowired
	AdvancedGroupDao advancedGroupDao;
	
	public void add(AdvancedGroup ag)
	{
		advancedGroupDao.add(ag);
	}
	
	public void update(AdvancedGroup ag)
	{
		advancedGroupDao.update(ag);
	}
	
	public void delete(AdvancedGroup ag)
	{
		advancedGroupDao.delete(ag);
	}
	
	public AdvancedGroup get(Long id)
	{
		return advancedGroupDao.get(id);
	}
	
	public List<AdvancedGroup> findAll()
	{
		return advancedGroupDao.findAll();
	}
	
	public List<AdvancedGroup> findByYear(Integer year)
	{
		return advancedGroupDao.findByYear(year);
	}
	public List<Map<String,String>> findByYearExtends(Integer year)
	{
		return advancedGroupDao.findByYearExtends(year);
	}
	
	/**
	 * 按荣誉称号查询
	 * @param title
	 * @return List<AchievedGroup>
	 */
	public List<AdvancedGroup> findByTitle(Integer title)
	{
		return advancedGroupDao.findByTitle(title);
	}
	public List<Map<String,String>> findByTitleExtends(Integer title)
	{
		return advancedGroupDao.findByTitleExtends(title);
	
	}
	/**
	 * 
	 * @param 集体账号用户名
	 * @return
	 */
	public List<AdvancedGroup> findByGroupname(String groupname)
	{
		return advancedGroupDao.findByGroupname(groupname);
	}
	
	/**
	 * 
	 * @param 集体名称
	 * @return
	 */
	public List<AdvancedGroup> findByName(String name)
	{
		return advancedGroupDao.findByName(name);
	}
	public List<Map<String,String>> findByNameExtends(String name)
	{
		return advancedGroupDao.findByNameExtends(name);
	}
	
	public List<AdvancedGroup> orderByYear(String groupname)
	{
		return advancedGroupDao.orderByYear(groupname);
	}
} 
