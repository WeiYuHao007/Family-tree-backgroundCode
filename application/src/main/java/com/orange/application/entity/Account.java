package com.orange.application.entity;

import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label="user")
public class Account{
	@Id
	@GeneratedValue
	private Long id;
	
	@Property(name="name")
	private String name;
	@Property(name="password")
	private String password;
	@Property(name="role")
	private List<String> role;


	public Account(Long id, String name, String password, List<String> role) {
		this.id =id;
		this.name = name;
		this.password = password;
		this.role = role;
	}


	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	public List<String> getRole() {
		return this.role;
	}
}
