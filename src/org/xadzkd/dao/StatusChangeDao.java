package org.xadzkd.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.StatusChange;

@Repository
public class StatusChangeDao {
	@Autowired
	SessionFactory sessionFactory;
	
	public void add(StatusChange sc)
	{
		sessionFactory.getCurrentSession().save(sc);
	}
	
	@SuppressWarnings("unchecked")
	public List<StatusChange> findAll()
	{
		String hql="from StatusChange as sc";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}
