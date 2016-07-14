package org.xadzkd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.PersonApplyDao;
import org.xadzkd.domain.PersonApply;

/**
 * 个人申请表的操作
 */
@Service
@Transactional
public class PersonApplyService 
{
	@Autowired
	PersonApplyDao personApplyDao;
	
	public void addPersonApply(PersonApply pa)
	{
		personApplyDao.add(pa);
	}
	
	public void update(PersonApply pa)
	{
		personApplyDao.update(pa);
	}
	
	public void delete(PersonApply pa)
	{
		personApplyDao.delete(pa);
	}
	
	public PersonApply get(Long id)
	{
		return personApplyDao.get(id);
	}
	
	public PersonApply findByUsername(String username){
		
		return personApplyDao.findByUsername(username);
	}
	
	public List<PersonApply> findAll()
	{
		return personApplyDao.findAll();
	}
	
	/**
	 * 查找所有未认定用户
	 * @return
	 */
	public List<Map<String, String>> findAllUnchecked()
	{
		return personApplyDao.findAllUnckecked();
	}
	
	/**
	 * 查找所有认定完成用户
	 * @return
	 */
	public List<Map<String, String>> findAllChecked()
	{
		return personApplyDao.findAllChecked();
	}
	
	/**
	 * 
	 * @return 新上报用户列表
	 */
	public List<PersonApply> findresult()
	{
		return personApplyDao.findresult();
	}
	
	public void clearTable()
	{
		personApplyDao.clearTable();
	}
}
