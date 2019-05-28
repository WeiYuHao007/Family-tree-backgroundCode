package com.orange.familyTree.entity;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity(label="Genealogy")
public class Genealogy {
	
	//有参构造器
	public Genealogy(Long uuid, String name, List<String> admin, List<String> topAdmin,
			List<Person> ownsNode) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.admin = admin;
		this.topAdmin = topAdmin;
		this.ownsNode = ownsNode;
	}

	//无参构造器
	public Genealogy() {
		
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


	@JsonIgnoreProperties("person")
	@Relationship(type = "OWNS", direction = Relationship.OUTGOING)
	private List<Person> ownsNode;
	
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

	public List<Person> getOwnsNode() {
		return ownsNode;
	}

	public void setOwnsNode(List<Person> ownsNode) {
		this.ownsNode = ownsNode;
	}
}
