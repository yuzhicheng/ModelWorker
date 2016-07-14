package org.xadzkd.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xadzkd.domain.User;
import org.xadzkd.tool.MessageDigestUtil;

import java.sql.Date;
import java.util.List;


@Repository
public class UserDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public void add(User u)
	{
		String pwd=MessageDigestUtil.digestByMD5(u.getPassword());
		u.setPassword(pwd);
		sessionFactory.getCurrentSession().save(u);
	}
	
	public void update(User u)
	{
		sessionFactory.getCurrentSession().merge(u);
	}
	
	
	/**
	 *  若传入的User字段有null，则该字段不更新
	 * @param u
	 */
	public void updatePart(User u) {
		User user = this.get(u.getId());
		
		String name, idCard,username,password,email,address,birthPlace,post,phone,degree;
		Integer permission,sex,nation,unionName,personCondition;
		Date createDate;
		
		if((name=u.getName()) != null){
			user.setName(name);
		}
		if((idCard=u.getIdCard()) != null){
			user.setIdCard(idCard);
		}
		if((username=u.getUsername()) != null){
			user.setUsername(username);
		}
		if((password=u.getPassword()) != null){
			user.setPassword(MessageDigestUtil.digestByMD5(password));
		}
		if((email=u.getEmail()) != null){
			user.setEmail(email);
		}
		if((address=u.getAddress()) != null){
			user.setAddress(address);
		}
		if((birthPlace=u.getBirthPlace()) != null){
			user.setBirthPlace(birthPlace);
		}
		if((post=u.getPost()) != null){
			user.setPost(post);
		}
		if((phone=u.getPhone()) != null){
			user.setPhone(phone);
		}
		if((degree=u.getDegree()) != null){
			user.setDegree(degree);
		}
		if((permission=u.getPermission()) != null){
			user.setPermission(permission);
		}
		if((sex=u.getSex()) != null){
			user.setSex(sex);
		}
		if((nation=u.getNation()) != null){
			user.setNation(nation);
		}
		if((unionName=u.getUnionName()) != null){
			user.setUnionName(unionName);
		}
		if((personCondition=u.getPersoncondition()) != null){
			user.setPersoncondition(personCondition);
		}
		if((createDate=u.getCreateDate()) != null){
			user.setCreateDate(createDate);
		}
		
		sessionFactory.getCurrentSession().update(user);
	}
	
	public void delete(User u)
	{
		sessionFactory.getCurrentSession().delete(u);
	}
	
	public User get(Long id)
	{
		return (User)sessionFactory.getCurrentSession().get(User.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAll()
	{
		String hql="from User as u";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	/**
	 * 按姓名
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByName(String name){
		String hql = "from User as u where u.name=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, name);
		return query.list();
	}
	

	/**
	 * 按用户名
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		String hql = "from User as u where u.username=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, username);
		return (User)query.uniqueResult();
	}
	
	/**
	 * 按权限查找（普通用户、有审核权限用户、管理员）
	 * @param permission
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByPermission(Integer permission)
	{
		String hql = "from User as u where u.permission=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, permission);
		return query.list();
	}

	/**
	 * 按身份证号
	 * @param idcard
	 * @return
	 */
	public User findByIdcard(String idcard){
		String hql = "from User as u where u.idCard=?";
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, idcard);
		return (User)query.uniqueResult();
	}
	
	public User login(String username,String password)
	{
		String hql="from User as u where u.username=? and u.password= ?";
		String pwd= MessageDigestUtil.digestByMD5(password);
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, pwd);
		User u=(User)query.uniqueResult();
		return u;
	}
	
	//===============根据用户名判断密码是否正确==================
		public String getPassword(String username){
			System.out.println(username);
			String hq1="select u.password from User as u where u.username=?";
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery(hq1);
			query.setParameter(0, username);
			String userpassword=(String)query.uniqueResult();
			return userpassword;
		}
		
		public void updatePassword(String username, String userpassword){
			String hq2="update User u set u.password= ? where u.username= ?";
			String pwd = MessageDigestUtil.digestByMD5(userpassword);
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery(hq2);		
			query.setParameter(0, pwd);
			query.setParameter(1, username);	
			query.executeUpdate();
		}
		
		public void updateInfo(String username, String email, String phone, String post, String degree, int unionName, String address){
			String hq2="update User u set u.email= ?,u.phone=?,u.post=?,u.degree=?,u.unionName=?,u.address=? where u.username= ?";
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery(hq2);		
			query.setParameter(0, email);
			query.setParameter(1, phone);
			query.setParameter(2, post);
			query.setParameter(3, degree);
			query.setParameter(4, unionName);
			query.setParameter(5, address);
			query.setParameter(6, username);
			query.executeUpdate();
		}
		
		//=================用户名取得用户信息===========
		public User getUser(String username){
			String hql = "from User as u where u.username=?";
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(hql);
			query.setParameter(0, username);
			User u=(User)query.uniqueResult();
			return u;
		}
}
