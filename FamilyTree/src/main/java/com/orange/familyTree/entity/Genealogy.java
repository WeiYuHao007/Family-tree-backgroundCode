package com.orange.familyTree.entity;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label="Genealogy")
public class Genealogy {
	
	//无参构造器
	public Genealogy() {
		
	}
	
	//有参构造器
	public Genealogy(Long uuid, String name, List<String> admin, List<String> topAdmin) {
		this.uuid = uuid;
		this.admin = admin;
		this.topAdmin = topAdmin;
	}

	//属性
	@Id @GeneratedValue
	private Long uuid;
	
	@Property(name = "name")
	private String name;
	
	@Property(name = "admin")
	private List<String>  admin;
	
	@Property(name = "topAdmin")
	private List<String> topAdmin;

	
	//一系列getter,setter.
	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAdmin() {
		return admin;
	}

	public void setAdmin(List<String> admin) {
		this.admin = admin;
	}

	public List<String> getTopAdmin() {
		return topAdmin;
	}

	public void setTopAdmin(List<String> topAdmin) {
		this.topAdmin = topAdmin;
	}
}
