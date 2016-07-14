package org.xadzkd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.UserDao;
import org.xadzkd.domain.User;

/**
 * 用户表服务
 */
@Service
@Transactional
public class UserService 
{
	@Autowired
	UserDao userDao;
	
	public void addUser(User u) {
		userDao.add(u);
	}
	
	public void update(User u) {
		userDao.update(u);
		
	}
	
	public User login(String username,String password){
		return userDao.login(username,password);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
	
	public List<User> findByName(String name) {
		
		return userDao.findByName(name);
	}

	public User findByIdcard(String idcard)
	{
		return userDao.findByIdcard(idcard);
	}
	
	public User findByUsername(String username)
	{
		return userDao.findByUsername(username);
	}
	
	public List<User> findByPermission(Integer permission)
	{
		return userDao.findByPermission(permission);
	}
	
	
	/*
	 * 若传入的User字段有null，则该字段不更新
	 * */
	public void updatePart(User u) {
		userDao.updatePart(u);
	}
	
	public User get(Long id) {
		return userDao.get(id);
	}

	public void delete(User u) {
		userDao.delete(u);
	}
	
	public String getPassword(String username){
		return userDao.getPassword(username);
	}
	
	public void updatePassword(String username, String userpassword){
		userDao.updatePassword(username, userpassword);
	}
	
	public void updateInfo(String username, String email, String phone, String post, String degree, int unionName, String address){
		userDao.updateInfo(username, email, phone, post, degree, unionName, address);
	}
	
	public User getUser(String username){
		return userDao.getUser(username);
	}
}
