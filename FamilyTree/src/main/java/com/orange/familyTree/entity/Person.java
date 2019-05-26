package com.orange.familyTree.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@NodeEntity(label="Person")
public class Person{
	
	//无参构造器
	public Person() {
	}
	
	//有参构造器
	public Person(Long uuid,String name,String firstName,String birthTime,
					String deathTime,String majorAchievements) {
		this.uuid = uuid;
		this.name = name;
		this.firstName = firstName;
		this.birthTime = birthTime;
		this.deathTime = deathTime;
		this.majorAchievements = majorAchievements;
	}

	//节点属性
	/*官方文档提示：对于长时间运行的应用程序，请不要依赖此ID。
	 * Neo4j将重用已删除的节点ID。建议用户为其域对象提供自己的唯一标识符（或使用UUID）。*/
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

	@Labels
	private List<String> labels = new ArrayList<>();
	
	
	//关系
	//节点属于的族谱
	//大小写可能存在问题
	//@JsonIgnoreProperties("Person")
	@Relationship(type = "BELONG", direction = Relationship.OUTGOING)
	private Genealogy belongGenealogy;
	
	//拥有该节点的族谱
	@Relationship(type = "OWNS", direction = Relationship.INCOMING)
	private Genealogy ownsGenealogy;
	
	//该节点的父亲
	@Relationship(type = "IS_FARTHER", direction = Relationship.INCOMING)
	private Person farther;
	
	//该节点的儿子
	@Relationship(type = "IS_SON", direction = Relationship.OUTGOING)
	private Set<Person> sons;
	
	//该节点的兄弟
	@Relationship(type = "IS_BROTHER", direction = Relationship.UNDIRECTED)
	private Set<Person> brothers;
	
	
	//以下为一系列get|set操作。
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
	
	public void setLabels(String label){
		this.labels.add(label);
	}
	
	public List<String> getLabels(){
		return this.labels;
	}
	
	public void setGenealogy(Genealogy belongGenealogy){
		this.belongGenealogy = belongGenealogy;
	}

	public Genealogy getBelongGenealogy() {
		return belongGenealogy;
	}

	public void setBelongGenealogy(Genealogy belongGenealogy) {
		this.belongGenealogy = belongGenealogy;
	}

	public Genealogy getOwnsGenealogy() {
		return ownsGenealogy;
	}

	public void setOwnsGenealogy(Genealogy ownsGenealogy) {
		this.ownsGenealogy = ownsGenealogy;
	}

	public Person getFarther() {
		return farther;
	}

	public void setFarther(Person farther) {
		this.farther = farther;
	}

	public Set<Person> getSons() {
		return sons;
	}

	public void setSons(Set<Person> sons) {
		this.sons = sons;
	}

	public Set<Person> getBrothers() {
		return brothers;
	}

	public void setBrothers(Set<Person> brothers) {
		this.brothers = brothers;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
}
