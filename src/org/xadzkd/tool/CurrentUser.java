package org.xadzkd.tool;

import org.xadzkd.domain.Group;
import org.xadzkd.domain.User;

public class CurrentUser {
	private Boolean isPerson;//是个人用户
	private User personUser;
	private Group groupUser;
	public CurrentUser(){
		isPerson = true;
		personUser = null;
		groupUser = null;
	}
	public User getPersonUser() {
		return personUser;
	}
	public void setPersonUser(User personUser) {
		this.personUser = personUser;
		this.isPerson = true;
		this.groupUser = null;
	}
	public Group getGroupUser() {
		return groupUser;
	}
	public void setGroupUser(Group groupUser) {
		this.groupUser = groupUser;
		this.isPerson = false;
		this.personUser = null;
	}
	public Boolean getIsPerson() {
		return isPerson;
	}
	
	
	
	
	
	
	
	
	
	
}
