package org.xadzkd.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 评审文件表
 */
@Entity
@Table(name="document")
public class Document 
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	@Column
	private Integer title;
	
	@Column
	private String content;//证书内容
	
	@Column
	private String company;//发文单位
	
	@Column
	private String time1;//证书授予时间
	
	@Column
	private String documentname;//文件名
	
	@Column
	private Long documentNO;
	
	@Column
	private String time2;//发文时间

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
/**
 * 
 * @return 称号授予时间
 */
	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	public Long getDocumentNO() {
		return documentNO;
	}

	public void setDocumentNO(Long documentNO) {
		this.documentNO = documentNO;
	}
/**
 * 
 * @return 发文时间
 */
	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}
	
}
