package org.xadzkd.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.AdvancedPerson;


@Repository
public class AdvancedPersonDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public void add(AdvancedPerson ap)
	{
		sessionFactory.getCurrentSession().save(ap);
	}
	
	public void update(AdvancedPerson ap)
	{
		sessionFactory.getCurrentSession().merge(ap);
	}
	
	public void delete(AdvancedPerson ap)
	{
		sessionFactory.getCurrentSession().delete(ap);
	}
	
	public AdvancedPerson get(Long id)
	{
		return (AdvancedPerson)sessionFactory.getCurrentSession().get(AdvancedPerson.class, id);
	}
	
	/**
	 * 按身份证查询
	 * @param idCard
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson>findByIdcard(String idCard)
	{
		String hql="select ap from AdvancedPerson ap ,User u "
				+ "where u.username = ap.username and u.idCard = ?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return  query.setParameter(0, idCard).list();
	}
	
	/**
	 * 按姓名查询
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findByName(String name)
	{
		String hql=" from AdvancedPerson ap where ap.name=?";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		return query.setParameter(0, name).list();
	}
	
	/**
	 * 按隶属工会查询（省总工会、市州总工会、省产业(局)工会、企业集团工会）
	 * @param union
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findByUnion(Integer union)
	{
		String hql="from AdvancedPerson ap , User u "
				+ "where u.username=ap.username and u.unionName=?";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		return query.setParameter(0, union).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findAll()
	{
		String hql="from AdvancedPerson as ap";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findByYear(Integer year)
	{
		String hql="from AdvancedPerson as ap where ap.year=?";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		return query.setParameter(0, year).list();
	}
	
	/**
	 * 按称号查询（全国劳模、四川省劳模全国五一劳动奖章、四川省五一劳动奖章、其他荣誉称号）
	 * @param title
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findByTitle(Integer title)
	{
		String hql="from AdvancedPerson as ap where ap.title=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.setParameter(0, title).list();
	}
	
	/**
	 *  按个人状况查询(在职，退休，死亡)
	 * @param personcondition
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findByCondition(Integer personcondition)
	{
		String hql="select ap from AdvancedPerson ap ,User u "
				+ "where ap.username=u.username and u.personcondition=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.setParameter(0, personcondition).list();
	}
	
	/** 
	 * 按荣誉认定状态查询(新上报、已认定、取消称号)
	 * @param state
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findByState(Integer state)
	{
		String hql="from AdvancedPerson as ap where ap.state=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.setParameter(0, state).list();
	}
	
	/**
	 * 按民族查询
	 * @param nation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findByNation(Integer nation)
	{
		String hql="select ap from AdvancedPerson ap ,User u "
				+ "where ap.username=u.username and u.nation=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.setParameter(0, nation).list();
	}
	
	/**
	 * 按登录名查询
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> findByUsername(String username){
		String hql = "from AdvancedPerson as ap where ap.username=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AdvancedPerson> orderByYear(String username)
	{
			String hql="from AdvancedPerson as ap  where ap.username=? order by ap.year desc";
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setParameter(0, username);
			return query.list();
	}
}
