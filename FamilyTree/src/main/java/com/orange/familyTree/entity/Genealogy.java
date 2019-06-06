package com.orange.familyTree.entity;

import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label="Genealogy")
public class Genealogy {
	
	//有参构造器
	public Genealogy(String name, String genealogySuperAdmin) {
		super();
		this.name = name;
		this.genealogySuperAdmin = genealogySuperAdmin;
	}

	//无参构造器
	public Genealogy() {
		
	}


	//属性
	@Id @GeneratedValue
	private Long uuid;
	
	@Property(name = "name")
	private String name;
	
	@Property(name = "genealogySuperAdmin")
	private String genealogySuperAdmin;
	
	@Property(name = "genealogyAdmin")
	private Set<String> genealogyAdmin;
	
	@Property(name = "followers")
	private Set<String> followers;
	
	
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

	public String getGenealogySuperAdmin() {
		return genealogySuperAdmin;
	}

	public void setGenealogySuperAdmin(String genealogySuperAdmin) {
		this.genealogySuperAdmin = genealogySuperAdmin;
	}

	public Set<String> getGenealogyAdmin() {
		return genealogyAdmin;
	}

	public void setGenealogyAdmin(Set<String> genealogyAdmin) {
		this.genealogyAdmin = genealogyAdmin;
	}

	public Set<String> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<String> followers) {
		this.followers = followers;
	}
	
}
