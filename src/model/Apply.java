package model;

import java.util.Map;

public class Apply {
	private String id;
	private String name;
	private String code;
	private int grade;
	
	public Apply(String id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.grade=-1;
	}
	public Apply(String id, String name, String code,int grade) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.grade=grade;
	}

	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	

	
	
}
