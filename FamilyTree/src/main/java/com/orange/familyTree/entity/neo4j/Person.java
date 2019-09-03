package com.orange.familyTree.entity.neo4j;


import com.orange.familyTree.pojo.PersonVO;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label="Person")
public class Person{

	public Person() {}
	
	//有参构造器
	public Person(String name, String birthTime, String deathTime,
			String majorAchievements) {
		super();
		this.name = name;
		this.birthTime = birthTime;
		this.deathTime = deathTime;
		this.majorAchievements = majorAchievements;
	}

	//以下为节点属性
	@Id @GeneratedValue
	private Long uuid;

	@Property(name = "name")
	private String name;
	
	@Property(name = "birthTime")
	private String birthTime;
	
	@Property(name = "deathTime")
	private String deathTime;
	
	@Property(name = "majorAchievements")
	private String majorAchievements;

	public static PersonVO changeToVO(Person person) {
		if(person != null) {
			PersonVO personVO = new PersonVO(person.name, person.birthTime, person.deathTime,
					person.majorAchievements);
			return personVO;
		}
		else {
			return null;
		}
	}

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
