package org.xadzkd.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表
 */
@Entity
@Table(name="user_inf")
public class User {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true)
	private String username;//用户名
	
	@Column
	private String password;

	/**
	 * 权限 0管理员，1有审核权限用户，2普通用户
	 */
	@Column
	private Integer permission;
	
	@Column
	private String name;
	
	@Column(unique=true)
	private String idCard;//身份证

	@Column
	private Integer sex;

	@Column
	private String email;
	
	@Column
	private String address;//地址
	
	@Column
	private String birthPlace;//籍贯
	
	@Column
	private Integer nation;//民族
	
	@Column
	private Integer unionName;//隶属工会（省总工会、市州总工会、省产业(局)工会、企业集团工会）
	
	@Column
	private String post;//职位
	
	@Column
	private String phone;
	
	@Column
	private String degree;//学历

	@Column
	private Integer personcondition;//个人状态（在职/退休/死亡）
	
	@Column
	private Date createDate;//日期

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}

	public Integer getUnionName() {
		return unionName;
	}

	public void setUnionName(Integer unionName) {
		this.unionName = unionName;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Integer getPersoncondition() {
		return personcondition;
	}

	public void setPersoncondition(Integer personcondition) {
		this.personcondition = personcondition;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
