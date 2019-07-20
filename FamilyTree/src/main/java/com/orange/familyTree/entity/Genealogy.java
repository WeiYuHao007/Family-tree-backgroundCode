package com.orange.familyTree.entity;

import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label="Genealogy")
public class Genealogy {

	public Genealogy(Long uuid, String name, Set<Integer> followers) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.followers = followers;
	}

	public Genealogy() {
		
	}


	//属性
	@Id @GeneratedValue
	private Long uuid;
	
	@Property(name = "name")
	private String name;
	
	@Property(name = "followers")
	private Set<Integer> followers;
	
	
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

	public Set<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Integer> followers) {
		this.followers = followers;
	}
	
}
