package org.xadzkd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
*个人申请表
 */
@Entity
@Table(name="person_apply")
public class PersonApply 
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String username;
	
	@Column
	private int  year;
	
	@Column
	private Integer title;//称号
	
	@Column
	private String bodycondition;//身体状况
	
	@Column
	private String difficulty;//家庭困难
	
	@Column
	private String workcondition;//就业情况
	
	@Column
	private String achievement;//突出事迹
	
	@Column
	private Integer result;//认定结果

	

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public String getBodycondition() {
		return bodycondition;
	}

	public void setBodycondition(String bodycondition) {
		this.bodycondition = bodycondition;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getWorkcondition() {
		return workcondition;
	}

	public void setWorkcondition(String workcondition) {
		this.workcondition = workcondition;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	
	
}
