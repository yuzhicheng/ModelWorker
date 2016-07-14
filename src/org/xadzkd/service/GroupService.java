package org.xadzkd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.GroupDao;
import org.xadzkd.domain.Group;
/**
 *集体用户信息表服务
 */
@Service
@Transactional
public class GroupService 
{
	@Autowired
	GroupDao groupDao;
	
	public void addGroup(Group g) 
	{
		groupDao.add(g);
	}
	
	public void update(Group g)
	{
		groupDao.update(g);
	}
	
	public void delete(Group g)
	{
		groupDao.delete(g);
	}
	
	public Group login(String groupname,String password) 
	{
		return groupDao.login(groupname, password);
	}
	
	public List<Group> findAll()
	{
		return groupDao.findAll();
	}
	
	public Group get(Long id)
	{
		return groupDao.get(id);
	}
	
	/**
	 * 集体账号名
	 * @param groupname
	 * @return
	 */
	public Group findByGroupName(String groupname)
	{
		return groupDao.findByGroupName(groupname);
	}
	
	/**
	 * 按集体名查找
	 * @param name
	 * @return
	 */
	public List<Group> findByName(String name)
	{
		return groupDao.findByName(name);
	}
	
	public String getPassword(String username){
		return groupDao.getPassword(username);
	}
	
	public void updatePassword(String username, String userpassword){
		groupDao.updatePassword(username, userpassword);
	}
	
	public void updateGroupInfo(String belong, String managerName, String phone, String address, String groupname){
		groupDao.updateGroupInfo(belong, managerName, phone, address, groupname);
	}
}
