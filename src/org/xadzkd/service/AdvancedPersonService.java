package org.xadzkd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.AdvancedPersonDao;
import org.xadzkd.domain.AdvancedPerson;

@Service
@Transactional
/**
 * 先进个人服务
 */
public class AdvancedPersonService 
{
	@Autowired
	AdvancedPersonDao advancedPersonDao;
	
	public void add(AdvancedPerson ap)
	{
		advancedPersonDao.add(ap);
	}
	
	public void update(AdvancedPerson ap)
	{
		advancedPersonDao.update(ap);
	}
	
	public void delete(AdvancedPerson ap)
	{
		advancedPersonDao.delete(ap);
	}
	
	public AdvancedPerson get(Long id)
	{
		return advancedPersonDao.get(id);
	}

	/**
	 * 按身份证查询
	 * @param idCard
	 * @return
	 */
	public List<AdvancedPerson>  findByIdcard(String idCard)
	{
		return advancedPersonDao.findByIdcard(idCard);
	}
	
	/**
	 * 按姓名查询
	 * @param name
	 * @return
	 */
	public List<AdvancedPerson> findByName(String name)
	{
		return advancedPersonDao.findByName(name);
	}
	
	public List<AdvancedPerson> findAll()
	{
		return advancedPersonDao.findAll();
	}
	
	public List<AdvancedPerson> findByYear(Integer year)
	{
		return advancedPersonDao.findByYear(year);
	}
	
	/**
	 * 按称号查询（全国劳模、四川省劳模全国五一劳动奖章、四川省五一劳动奖章、其他荣誉称号）
	 * @param title
	 * @return
	 */
	public List<AdvancedPerson> findByTitle(Integer title)
	{
		return advancedPersonDao.findByTitle(title);
	}
	
	/**
	 * 按隶属工会查询（省总工会、市州总工会、省产业(局)工会、企业集团工会）
	 * @param union
	 * @return
	 */
	public List<AdvancedPerson> findByUnion(Integer union)
	{
		return advancedPersonDao.findByUnion(union);
	}
	
	/**
	 * 按个人状况查询(在职，退休，死亡)
	 * @param condition
	 * @return List<AchievedPerson>
	 */
	public List<AdvancedPerson> findByCondition(Integer personcondition)
	{
		return advancedPersonDao.findByCondition(personcondition);
	}
	
	/**
	 * 按称号认定状态查询(新上报、已认定、取消称号)
	 * @param state
	 * @return List<AchievedPerson>
	 */
	public List<AdvancedPerson> findByState(Integer state)
	{
		return advancedPersonDao.findByState(state);
	}
	
	/**
	 * 按民族查询
	 * @param nation
	 * @return
	 */
	public List<AdvancedPerson> findByNation(Integer nation) 
	{
		return advancedPersonDao.findByNation(nation);
	}
	
	/**
	 * 按登录名查询
	 * @param username
	 * @return
	 */
	public List<AdvancedPerson> findByUsername(String username){
		return advancedPersonDao.findByUsername(username);
	}
	
	public List<AdvancedPerson> orderByYear(String username)
	{
		return advancedPersonDao.orderByYear(username);
	}
}
