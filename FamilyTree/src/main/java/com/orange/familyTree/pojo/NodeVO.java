package com.orange.familyTree.pojo;

public class NodeVO {
	
	public NodeVO() {}
	
	public NodeVO(String name, Integer x, Integer y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	private String name;
	private Integer x;
	private Integer y;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
}
