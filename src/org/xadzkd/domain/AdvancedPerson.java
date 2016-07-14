package org.xadzkd.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 先进个人表
 */
@Entity
@Table(name="advancedperson")
public class AdvancedPerson 
{
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String username;
	
	@Column
	private String name;
	
	@Column
	private Integer title;
	
	@Column
	private Integer state;//称号状态
	
	@Column
	private Integer treatment;//待遇
	
	@Column
	private Integer  year;
	
	@Column
	private String bodycondition;//身体状态
	
	@Column
	private String difficulty;//家庭困难
	
	@Column
	private String workcondition;//就业情况
	
	@Column
	private String achievement;//突出事迹
	
	@Column
	private Date createDate;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getTreatment() {
		return treatment;
	}

	public void setTreatment(Integer treatment) {
		this.treatment = treatment;
	}

	public Integer  getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
