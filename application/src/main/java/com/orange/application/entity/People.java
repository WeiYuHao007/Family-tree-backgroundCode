package com.orange.application.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label="student")
public class People {
	//标明主健
	@Id
	//主健策略
	@GeneratedValue
	private Long id;

	@Property(name="name")
	private String name;
	@Property(name="location")
	private String location;


	public People(Long id, String name, String location) {
		this.id = id;
		this.name = name;
		this.location = location;
	}


	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocation() {
		return this.location;
	}
	

}
