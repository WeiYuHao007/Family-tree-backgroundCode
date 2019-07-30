package com.orange.familyTree.pojo;

public class RelationshipVO {
	
	public RelationshipVO() {}
	
	public RelationshipVO(String source, String target, String relationshipName) {
		super();
		this.source = source;
		this.target = target;
		this.relationshipName = relationshipName;
	}
	
	private String source;
	
	private String target;
	
	private String relationshipName;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getRelationshipName() {
		return relationshipName;
	}
	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}

}
