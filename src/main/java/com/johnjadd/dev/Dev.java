package com.johnjadd.dev;

import java.nio.charset.Charset;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.johnjadd.MyEntity;
import com.johnjadd.site.Site;
import com.johnjadd.var.Var;

@Entity
@Table(name="dev_config")
@JsonIgnoreProperties({"site"})
public class Dev implements MyEntity{
	@Id
	private Long id;
	private String localId;
	private String name;
	private Boolean dead;
	
	@ManyToOne
	@JoinColumn(name="site")
	private Site site;
	
	@OneToMany(mappedBy="dev", cascade=CascadeType.ALL)
	private List<Var> vars;
	
	protected Dev() {
	}

	public Dev(Long id, String localId, String name, Site site) {
		super();
		this.id = id;
		this.localId = localId;
		this.name = name;
		this.site = site;
		this.dead = false;
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

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<Var> getVars() {
		return vars;
	}

	public void setVars(List<Var> vars) {
		this.vars = vars;
	}

	public Boolean getDead() {
		return dead;
	}

	public void setDead(Boolean dead) {
		this.dead = dead;
	}
}

	