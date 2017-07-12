package com.johnjadd.dev;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.johnjadd.MyEntity;
import com.johnjadd.site.Site;
import com.johnjadd.var.Var;

@Entity
@Table(name="dev_config")
@JsonIgnoreProperties({"site", "parent"})
public class Dev implements MyEntity{
	@Id
	private Long id;
	private String name;
	private String localId;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Site site;
	
	@OneToMany(mappedBy="dev", cascade=CascadeType.ALL)
	private List<Var> vars;
	
	@ManyToOne
	//@JoinColumn(name="parent")
	private Dev parent;
	
	@OneToMany(mappedBy="parent")
	private List<Dev> children;
	
	
	protected Dev() {
	}

	public Dev(Long id, String localId, String name, Dev parent) {
		super();
		this.id = id;
		this.localId = localId;
		this.name = name;
		this.parent = parent;
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

	public List<Dev> getChildren() {
		return children;
	}

	public void setChildren(List<Dev> children) {
		this.children = children;
	}

	public List<Var> getVars() {
		return vars;
	}

	public void setVars(List<Var> vars) {
		this.vars = vars;
	}

	public Dev getParent() {
		return parent;
	}

	public void setParent(Dev parent) {
		this.parent = parent;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	public boolean hasOffSpring(Long devId) {
		for(Dev dd : this.getChildren()) {
			if(dd.id.equals(devId)) {
				return true;
			}
		}
		
		for(Dev dd : this.getChildren()) {
			if(dd.hasOffSpring(devId)==true) {
				return true;
			}
		}
		
		return false;
	}

}

	