package com.johnjadd.id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="id_gen")
public class IdGen{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String entityName;
	private short entityType;
	private Long freeId;
	private boolean isMax;
	
	protected IdGen() {
	}
	
	public IdGen(Long id, String entityName, short entityType, Long freeId, boolean isMax) {
		super();
		this.id = id;
		this.entityName = entityName;
		this.entityType = entityType;
		this.freeId = freeId;
		this.isMax = isMax;
	}

	public void incFreeId() {
		this.freeId = this.freeId + 1; 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getFreeId() {
		return freeId;
	}

	public void setFreeId(Long freeId) {
		this.freeId = freeId;
	}

	public boolean isMax() {
		return isMax;
	}

	public void setMax(boolean isMax) {
		this.isMax = isMax;
	}

	public short getEntityType() {
		return entityType;
	}

	public void setEntityType(short entityType) {
		this.entityType = entityType;
	}
	
	
}
