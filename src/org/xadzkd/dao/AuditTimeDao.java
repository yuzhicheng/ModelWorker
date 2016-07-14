package org.xadzkd.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.AuditTime;

@Repository
public class AuditTimeDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public void add(AuditTime time)
	{
		sessionFactory.getCurrentSession().save(time);
	}
	
	public AuditTime get(Long id)
	{
		return (AuditTime) sessionFactory.getCurrentSession().get(AuditTime.class, id);
	}
	
	public void update(AuditTime time) 
	{
		sessionFactory.getCurrentSession().update(time);
	}
}
