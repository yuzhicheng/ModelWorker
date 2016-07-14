package org.xadzkd.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.Document;


@Repository
public class DocumentDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public void add(Document document)
	{
		sessionFactory.getCurrentSession().save(document);
	}
	
	public void update(Document document)
	{
		sessionFactory.getCurrentSession().update(document);
	}
	
	public void delete(Document document)
	{
		sessionFactory.getCurrentSession().delete(document);
	}
	
	public Document get(Long id)
	{
		return (Document)sessionFactory.getCurrentSession().get(Document.class, id);
	}
	

	public Document find(String username)
	{
		String hql="from Document as document where document.username= ?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setParameter(0, username);
		return (Document)query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Document> findAll()
	{
		String hql="from Document as document";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}
