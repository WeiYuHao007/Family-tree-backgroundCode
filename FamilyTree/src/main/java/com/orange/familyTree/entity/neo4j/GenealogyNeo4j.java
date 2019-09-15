package com.orange.familyTree.entity.neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label="GenealogyMySQL")
public class GenealogyNeo4j {

	public GenealogyNeo4j() {}

	public GenealogyNeo4j(Long uuid, Long genealogyId, String name) {
		super();
		this.uuid = uuid;
		this.genealogyId = genealogyId;
		this.name = name;
	}


	//属性
	@Id @GeneratedValue
	private Long uuid;

	@Property(name = "genealogyId")
	private Long genealogyId;

	@Property(name = "name")
	private String name;
	
	
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

	public Long getGenealogyId() {
		return genealogyId;
	}

	public void setGenealogyId(Long genealogyId) {
		this.genealogyId = genealogyId;
	}
}
