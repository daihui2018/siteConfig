package com.johnjadd.site;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.johnjadd.MyEntity;
import com.johnjadd.dev.Dev;

@Entity
@Table(name="site_config")
public class Site implements MyEntity{
	@Id
	private Long id;
	private String name;
	
	@OneToMany(mappedBy="site", cascade=CascadeType.ALL)
	private List<Dev> devs;
	
	protected Site() {
	}

	public Site(Long id, String name, List<Dev> devs) {
		super();
		this.id = id;
		this.name = name;
		this.devs = devs;
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

	public List<Dev> getDevs() {
		return devs;
	}

	public void setDevs(List<Dev> devs) {
		this.devs = devs;
	}
	
}


/*
 * {
        "id": 1,
        "name": "sx college",
        "devs": [
            {
                "id": 1,
                "localId": "wsd01",
                "name": "temp_humi 01",
                "vars": [
	                {
	                "id" : 1,
	                "localdId" : "wsd0101",
	                "name" : "temperature",
	                "type" : "double"
	                },
	                {
	                "id" : 2,
	                "localdId" : "wsd0102",
	                "name" : "humility",
	                "type" : "double"
	                }
                ]
            },
            {
                "id": 2,
                "localId": "wsd02",
                "name": "temp_humi 02",
                "vars": [
                	{
	                "id" : 1,
	                "localdId" : "wsd0201",
	                "name" : "temperature",
	                "type" : "double"
	                },
	                {
	                "id" : 2,
	                "localdId" : "wsd0202",
	                "name" : "humility",
	                "type" : "double"
	                }                
                ]
            }
        ]
    }
    */
