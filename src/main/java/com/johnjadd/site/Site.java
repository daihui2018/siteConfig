package com.johnjadd.site;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.johnjadd.MyEntity;
import com.johnjadd.dev.Dev;

@Entity
@Table(name="site_config")
public class Site implements MyEntity{
	@Id
	private Long id;
	private String name;
	
	@OneToOne//(cascade=CascadeType.ALL)
	private Dev dev;
	
	protected Site() {
	}

	public Site(Long id, String name, Dev dev) {
		super();
		this.id = id;
		this.name = name;
		this.dev = dev;
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

	public Dev getDev() {
		return dev;
	}

	public void setDev(Dev dev) {
		this.dev = dev;
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
