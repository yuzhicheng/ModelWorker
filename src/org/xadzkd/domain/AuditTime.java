package org.xadzkd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auditTime")
public class AuditTime 
{
	/**
	 * id有且仅有1
	 */
	@Id
	@Column
	private Long id;
	
	@Column
	private Boolean start;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStart() {
		return start;
	}

	public void setStart(Boolean start) {
		this.start = start;
	}

}
