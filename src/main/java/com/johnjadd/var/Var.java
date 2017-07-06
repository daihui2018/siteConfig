package com.johnjadd.var;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.johnjadd.MyEntity;
import com.johnjadd.dev.Dev;

@Entity
@Table(name="var_config")
@JsonIgnoreProperties("dev")
public class Var implements MyEntity{
	@Id
	private Long id;
	private String localId;
	private String name;
	private String type;
	private Boolean dead;
	
	@ManyToOne
	@JoinColumn(name="dev")
	private Dev   dev;
	
	protected Var() {}

	public Var(Long id, String localId, String name, String type, Dev dev) {
		super();
		this.id = id;
		this.localId = localId;
		this.name = name;
		this.type = type;
		this.dead = false;
	
		this.dev = dev;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Dev getDev() {
		return dev;
	}

	public void setDev(Dev dev) {
		this.dev = dev;
	}

	public Boolean getDead() {
		return dead;
	}

	public void setDead(Boolean dead) {
		this.dead = dead;
	}	
	
}
