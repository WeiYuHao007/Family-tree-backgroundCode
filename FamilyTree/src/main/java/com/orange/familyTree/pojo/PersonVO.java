package com.orange.familyTree.pojo;

public class PersonVO {

	public PersonVO() {}

	public PersonVO(String name, String gender, String birthTime, String deathTime, String majorAchievements) {
		this.name = name;
		this.gender = gender;
		this.birthTime = birthTime;
		this.deathTime = deathTime;
		this.majorAchievements = majorAchievements;
	}

	private String name;

	private String gender;

	private String birthTime;

	private String deathTime;

	private String majorAchievements;

	private String commit;

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

	public String getCommit() {
		return commit;
	}

	public void setCommit(String commit) {
		this.commit = commit;
	}
}
