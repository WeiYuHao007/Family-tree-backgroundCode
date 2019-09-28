package com.orange.familyTree.entity.neo4j;


import com.orange.familyTree.pojo.PersonVO;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label="Person")
public class Person{

	public Person() {}

	public Person(Long uuid, String name, String gender, String birthTime, String deathTime, String majorAchievements) {
		this.uuid = uuid;
		this.name = name;
		this.gender = gender;
		this.birthTime = birthTime;
		this.deathTime = deathTime;
		this.majorAchievements = majorAchievements;
	}

	//以下为节点属性
	@Id @GeneratedValue
	private Long uuid;

	@Property(name = "name")
	private String name;

	@Property(name = "gender")
	private String gender;
	
	@Property(name = "birthTime")
	private String birthTime;
	
	@Property(name = "deathTime")
	private String deathTime;
	
	@Property(name = "majorAchievements")
	private String majorAchievements;

	public static PersonVO changeToVO(Person person) {
		if(person != null) {
			PersonVO personVO = new PersonVO(person.name, person.gender, person.birthTime, person.deathTime,
					person.majorAchievements);
			return personVO;
		}
		else {
			return null;
		}
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(String birthTime) {
		this.birthTime = birthTime;
	}

	public String getDeathTime() {
		return deathTime;
	}

	public void setDeathTime(String deathTime) {
		this.deathTime = deathTime;
	}

	public String getMajorAchievements() {
		return majorAchievements;
	}

	public void setMajorAchievements(String majorAchievements) {
		this.majorAchievements = majorAchievements;
	}
}
