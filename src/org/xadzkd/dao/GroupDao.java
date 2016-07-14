package org.xadzkd.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.Group;
import org.xadzkd.tool.MessageDigestUtil;

@Repository
public class GroupDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public void add(Group group) 
	{
		String pwd = MessageDigestUtil.digestByMD5(group.getPassword());
		group.setPassword(pwd);
		sessionFactory.getCurrentSession().save(group);
	}
	
	public void update(Group group)
	{
		sessionFactory.getCurrentSession().merge(group);
	}
	
	public void delete(Group group)
	{
		sessionFactory.getCurrentSession().delete(group);
	}
	
	public Group get(Long id)
	{
		return (Group)sessionFactory.getCurrentSession().get(Group.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> findAll()
	{
		String hql="from Group as group";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	
	public Group login(String groupname,String password) 
	{
		String pwd = MessageDigestUtil.digestByMD5(password);
		String hql="from Group as group where group.groupname=? and group.password= ?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setParameter(0, groupname);
		query.setParameter(1, pwd);
		Group group=(Group)query.uniqueResult();
		return group;
	}
	
	public Group findByGroupName(String groupname) {
		String hql="from Group as group where group.groupname=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setParameter(0, groupname);
		Group group=(Group)query.uniqueResult();
		return group;
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> findByName(String name)
	{
		String hql="from Group as g where g.name=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.setParameter(0, name).list();
	}
	
	/**
	 * 根据用户名取出用户密码
	 * @param username
	 * @return
	 */
	public String getPassword(String username){
		String hq1="select u.password from Group as u where u.groupname=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hq1);
		query.setParameter(0, username);
		String userpassword=(String)query.uniqueResult();
		return userpassword;
	}
	
	/**
	 * 更改用户密码
	 * @param username
	 * @param userpassword
	 */
	public void updatePassword(String username, String userpassword){
		String hql="update Group u set u.password= ? where u.groupname= ?";
		String pwd =MessageDigestUtil.digestByMD5(userpassword);
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);		
		query.setParameter(0, pwd);
		query.setParameter(1, username);	
		query.executeUpdate();
	}
	
	/**
	 * 更新Group信息
	 * @param belong
	 * @param managerName
	 * @param phone
	 * @param address
	 * @param groupname
	 */
	public void updateGroupInfo(String belong, String managerName, String phone, String address, String groupname){
		String hql = "update Group g set g.belong=?,g.managerName=?,g.phone=?,g.address=? where g.groupname=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);		
		query.setParameter(0, belong);
		query.setParameter(1, managerName);	
		query.setParameter(2, phone);	
		query.setParameter(3, address);	
		query.setParameter(4, groupname);	
		query.executeUpdate();
	}
}
