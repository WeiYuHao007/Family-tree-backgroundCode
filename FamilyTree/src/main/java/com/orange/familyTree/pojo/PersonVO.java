package com.orange.familyTree.pojo;

public class PersonVO {

	public PersonVO() {}
	
	//有参构造器
	public PersonVO(String name, String birthTime, String deathTime,
			String majorAchievements) {
		super();
		this.name = name;
		this.birthTime = birthTime;
		this.deathTime = deathTime;
		this.majorAchievements = majorAchievements;
	}

	
	private String name;

	private String birthTime;

	private String deathTime;

	private String majorAchievements;
	
	
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