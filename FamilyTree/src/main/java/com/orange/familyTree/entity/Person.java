package com.orange.familyTree.entity;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.RelationshipEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@NodeEntity(label="Person")
public class Person{
	
	//有参构造器
	public Person(String name, String birthTime, String deathTime,
			String majorAchievements) {
		super();
		this.name = name;
		this.birthTime = birthTime;
		this.deathTime = deathTime;
		this.majorAchievements = majorAchievements;
	}

	//无参构造器
	public Person() {
	}

	
	//以下为节点属性
	@Id @GeneratedValue
	private Long uuid;

	@Property(name = "name")
	private String name;
	
	@Property(name = "firstName")
	private String firstName;
	
	@Property(name = "birthTime")
	private String birthTime;
	
	@Property(name = "deathTime")
	private String deathTime;
	
	@Property(name = "majorAchievements")
	private String majorAchievements;
	
	//以下为一系列get、set操作。
	public Long getUuid() {
		return uuid;
	}
	
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}
	
	public String getBirthTime() {
		return this.birthTime;
	}
	
	public void setDeathTime(String deathTime) {
		this.deathTime = deathTime;
	}
	
	public String getDeathTime() {
		return this.deathTime;
	}
	
	public void setMajorAchievements(String majorAchievements) {
		this.majorAchievements = majorAchievements;
	}
	
	public String getMajorAchievements() {
		return this.majorAchievements;
	}
}
