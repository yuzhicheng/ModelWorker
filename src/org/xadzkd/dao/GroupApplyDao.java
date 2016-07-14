package org.xadzkd.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.GroupApply;

@Repository
public class GroupApplyDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	 public void add(GroupApply ga)
	{
		sessionFactory.getCurrentSession().save(ga);
	}
	
	public void update(GroupApply ga)
	{
		sessionFactory.getCurrentSession().update(ga);
	}
	
	public GroupApply get(Long id)
	{
		return (GroupApply)sessionFactory.getCurrentSession().get(GroupApply.class , id);
	}
	
	
	
	public void delete(GroupApply ga)
	{
		sessionFactory.getCurrentSession().delete(ga);
	}
	
	@SuppressWarnings("unchecked")
	public List<GroupApply> findAll()
	{
		String hql="from GroupApply as ga ";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> findAllUnchecked()
	{
		String hql="select new map(g.name as name,g.groupname as groupname,g.address as address,g.managerName as manager,g.phone as phone, g.belong as belong ,ga.title as title,ga.achievement as achievement) "
	   + "from GroupApply as ga ,Group g where ga.groupname=g.groupname and ga.result= 0";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> findAllChecked()
	{
		String hql="select new map(g.name as name,g.groupname as groupname,g.address as address,g.managerName as manager,g.phone as phone, g.belong as belong ,ga.title as title,ga.achievement as achievement,ga.result as result) "
				   + "from GroupApply as ga ,Group g where ga.groupname=g.groupname and ga.result != 0";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	/**
	 * 
	 * @return 新上报用户列表
	 */
	@SuppressWarnings("unchecked")
	public List<GroupApply> findresult()
	{
		String hql="from GroupApply as ga where ga.result=0";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	public void clearTable()
	{
		String hql = "delete from GroupApply as ga where 1=1";
		sessionFactory.getCurrentSession().createQuery(hql);
	}
	
	/**
	 * 按登录名查询
	 * 
	 */
	public GroupApply findByGroupname(String groupname)
	{
		String hql="from GroupApply as ag where ag.groupname=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return  (GroupApply) query.setParameter(0, groupname).uniqueResult();
	}
}
