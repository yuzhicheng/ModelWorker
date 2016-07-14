package org.xadzkd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.AuditTimeDao;
import org.xadzkd.domain.AuditTime;

@Service
@Transactional
public class AuditTimeService 
{
	@Autowired
	AuditTimeDao auditTimeDao;
	
	public void start()
	{
		AuditTime time=auditTimeDao.get((long) 1);
		if(time!=null && time.getStart()==false)
		{
			time.setStart(true);
		}
		auditTimeDao.update(time);
	}
	
	public void close()
	{
		AuditTime time=auditTimeDao.get((long) 1);
		if(time!=null && time.getStart()==true)
		{
			time.setStart(false);
		}
		auditTimeDao.update(time);
	}
}
