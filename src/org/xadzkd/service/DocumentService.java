package org.xadzkd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xadzkd.dao.DocumentDao;
import org.xadzkd.domain.Document;


/**
 * 提交评审的文件表的服务
 */
@Service
@Transactional
public class DocumentService {
	@Autowired
	DocumentDao documentDao;
	
	public Document get(Long id)
	{
		return documentDao.get(id);
	}
	
	public void addDocument(Document d)
	{
		documentDao.add(d);
	}
	
	public void update(Document d)
	{
		documentDao.update(d);
	}
	
	public void delete(Document d)
	{
		documentDao.delete(d);
	}
	
	public Document find(String username)
	{
		return documentDao.find(username);
	}
	
	public List<Document> findAll()
	{
		return documentDao.findAll();
	}
}

