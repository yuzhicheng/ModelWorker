package org.xadzkd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.StatusChangeDao;
import org.xadzkd.domain.StatusChange;

@Service
@Transactional
public class StatusChangeService {
	
	@Autowired
	StatusChangeDao statusChangeDao;
	
	public void add(StatusChange sc)
	{
		statusChangeDao.add(sc);
	}
	
	public List<StatusChange> findAll()
	{
		return statusChangeDao.findAll();
	}
}
