package org.xadzkd.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.PersonApply;

@Repository
public class PersonApplyDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public void add(PersonApply pa)
	{
		sessionFactory.getCurrentSession().save(pa);
	}
	
	public void update(PersonApply pa)
	{
		sessionFactory.getCurrentSession().update(pa);
	}
	
	public PersonApply get(Long id)
	{
		return (PersonApply)sessionFactory.getCurrentSession().get(PersonApply.class, id);
	}
	
	public void delete(PersonApply pa)
	{
		sessionFactory.getCurrentSession().delete(pa);
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonApply> findAll()
	{
		String hql="from PersonApply as pa ";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public  List<Map<String,String>>findAllChecked()
	{
		String hql="select new map(u.name as name,u.idCard as idCard,u.nation as nation,u.sex as sex,u.username as username,pa.title as title,pa.achievement as achievement,pa.result as result) "
				   + "from PersonApply as pa ,User u where pa.username=u.username and pa.result != 0";
					return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public  List<Map<String,String>>findAllUnckecked()
	{
		String hql="select new map(u.name as name,u.idCard as idCard,u.nation as nation,u.sex as sex,u.username as username,pa.title as title,pa.achievement as achievement) "
	   + "from PersonApply as pa ,User u where pa.username=u.username and pa.result=0";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<PersonApply> findresult()
	{
		String hql="from PersonApply as pa where pa.result = 0";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	public PersonApply findByUsername(String username){
		String hql = "from PersonApply as p where p.username=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		return (PersonApply)query.uniqueResult();
	}
	
	public void  clearTable()
	{
		String hql= "delete from PersonApply as pa where 1=1 ";
		sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
		
	}

	
}
