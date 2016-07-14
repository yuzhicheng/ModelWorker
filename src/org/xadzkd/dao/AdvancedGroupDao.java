package org.xadzkd.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.AdvancedGroup;


@Repository
public class AdvancedGroupDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public void add(AdvancedGroup ag)
	{
		sessionFactory.getCurrentSession().save(ag);
	}
	
	public void update(AdvancedGroup ag)
	{
		sessionFactory.getCurrentSession().merge(ag);
	}
	
	public void delete(AdvancedGroup ag)
	{
		sessionFactory.getCurrentSession().delete(ag);
	}
	
	public AdvancedGroup get(Long id)
	{
		return (AdvancedGroup)sessionFactory.getCurrentSession().get(AdvancedGroup.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<AdvancedGroup> findAll()
	{
		String hql="from AdvancedGroup as ag";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AdvancedGroup> findByYear(int year)
	{
		String hql="from AdvancedGroup as ag where ag.year=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.setParameter(0, year).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> findByYearExtends(int year)
	{
		String hql="select new map(g.name as name,g.managerName as managerName,g.belong as belong,g.groupname as groupname,g.phone as phone,ag.title as title,ag.achievement as achievement,ag.year as year) "+"from AdvancedGroup as ag,Group g where ag.groupname=g.groupname and ag.year=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.setParameter(0, year).list();
	}
	
	/**
	 * 按荣誉称号（全国五一劳动奖章单位、四川省五一劳动奖章单位、全国工人先锋号、四川省工人先锋号）
	 * @param 称号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedGroup> findByTitle(Integer title)
	{
		String hql="from AdvancedGroup as ag where ag.title=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.setParameter(0, title).list();
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> findByTitleExtends(Integer title)
	{
		String hql="select new map(g.name as name,g.managerName as managerName,g.belong as belong,g.groupname as groupname,g.phone as phone,ag.title as title,ag.achievement as achievement,ag.year as year)"+" from AdvancedGroup as ag,Group g where ag.groupname=g.groupname and  ag.title=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.setParameter(0, title).list();
	}
	
	/**
	 * 按集体账号/集体用户名
	 * @param groupname
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedGroup> findByGroupname(String groupname)
	{
		String hql="from AdvancedGroup as ag where ag.groupname=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return  query.setParameter(0, groupname).list();
	}
	
	/**
	 * 按集体名称
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedGroup> findByName(String name)
	{
		String hql="from AdvancedGroup as ag where ag.name=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.setParameter(0, name).list();
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,String>> findByNameExtends(String name)
	{
		String hql="select new map(g.name as name,g.managerName as managerName,g.belong as belong,g.groupname as groupname,g.phone as phone,ag.title as title,ag.achievement as achievement,ag.year as year)"+" from AdvancedGroup as ag,Group g where ag.groupname=g.groupname and ag.name=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.setParameter(0, name).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AdvancedGroup> orderByYear(String groupname)
	{
			String hql="from AdvancedGroup as ag where ag.groupname=? order by ag.year desc";
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			return query.setParameter(0, groupname).list();
	}
}
