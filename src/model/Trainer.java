package model;

import java.util.Map;

public class Trainer {

	private String code;
	private String name;
	private String email;
	private String license;
	private String tel;
	public Trainer(String code, String name, String email, String license, String tel) {
		this.code = code;
		this.name = name;
		this.email = email;
		this.license = license;
		this.tel = tel;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	

	

	
	
	
	
}
