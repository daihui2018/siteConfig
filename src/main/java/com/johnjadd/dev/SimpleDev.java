package com.johnjadd.dev;

public class SimpleDev {
	private Long id;
	private String localId;
	private String name;
	
	public SimpleDev(Long id, String localId, String name) {
		super();
		this.id = id;
		this.localId = localId;
		this.name = name;
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
}
