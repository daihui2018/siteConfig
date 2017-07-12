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
	private String name;
	private String type;
	
	private String calMethod;	// =, sum, avg, max, min
	private String calParam;	// {"KT01"}
	
	@ManyToOne
	@JoinColumn(name="dev")
	private Dev dev;
	
	protected Var() {}

	public Var(Long id, String name, String type, Dev dev) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.dev = dev;
		
		this.calMethod = "=";
		this.calParam = "";
	}

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

	public String getCalMethod() {
		return calMethod;
	}

	public void setCalMethod(String calMethod) {
		this.calMethod = calMethod;
	}

	public String getCalParam() {
		return calParam;
	}

	public void setCalParam(String calParam) {
		this.calParam = calParam;
	}

		
}
