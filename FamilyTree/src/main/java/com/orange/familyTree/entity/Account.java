package com.orange.familyTree.entity;

import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import com.orange.familyTree.pojo.AccountDetail;


@NodeEntity(label="Account")
public class Account {
	
	//有参构造器
	public Account(String nickName, String email, String password, String telephoneNumber) {
		super();
		this.nickName = nickName;
		this.email = email;
		this.telephoneNumber = telephoneNumber;
		this.password = password;
	}
	
	//无参构造器
	public Account() {
		
	}
	
	
	//属性
	@Id @GeneratedValue
	private Long uuid;
	
	@Property(name="nickName")
	private String nickName;
	
	@Property(name="email")
	private String email;
	
	@Property(name="password")
	private String password;
	
	@Property(name="telephoneNumber")
	private String telephoneNumber;
	
	@Property(name="privilegeRole")
	private List<String> privilegeRole;
	
	@Property(name="registrationTime")
	private String registrationTime;
	
	
	//该账号关注的图谱
	//映射的使用有待后续考虑
	@Relationship(type="FOCUS_ON", direction= Relationship.OUTGOING)
	private Set<Genealogy> focusOn;
	
	
	//一系列getter、setter
	public Long getUuid() {
		return uuid;
	}
	
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public List<String> getPrivilegeRole() {
		return privilegeRole;
	}
	
	public void setPrivilegeRole(List<String> privilegeRole) {
		this.privilegeRole = privilegeRole;
	}
	
	public String getRegistrationTime() {
		return registrationTime;
	}
	
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	public Set<Genealogy> getFocusOn() {
		return focusOn;
	}

	public void setFocusOn(Set<Genealogy> focusOn) {
		this.focusOn = focusOn;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
