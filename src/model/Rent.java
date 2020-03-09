package model;

import java.util.Date;
import java.util.Map;

public class Rent {

	private int no;
	private String id;
	private String name;
	private String location;
	private Date regDate;
	private String r_date;
	private String purpose;
	private String capacity;
	private int status;
	private String reason;
	

	// 전체 글

	public Rent(int no, String id, String name, String location, Date regDate, String r_date, String purpose,
			String capacity, int status, String reason) {

		this.no = no;
		this.id = id;
		this.name = name;
		this.location = location;
		this.regDate = regDate;
		this.r_date = r_date;
		this.purpose = purpose;
		this.capacity = capacity;
		this.status = status;
		this.reason = reason;
	}
	

	// insert할때
	public Rent(String id, String name, String location, String purpose, String capacity) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.purpose = purpose;
		this.capacity = capacity;
		
	}


	// list할때
	public Rent(int no, String name, String location, Date regDate, String r_date, int status) {
		this.no = no;
		this.name = name;
		this.location = location;
		this.regDate = regDate;
		this.r_date = r_date;
		this.status = status;
	}
	
	// 수정할 때
	public Rent(int no, String name, String location, 
			String purpose, String r_date, String capacity) {
		this.no = no;
		this.name = name;
		this.location = location;
		this.purpose = purpose;
		this.r_date = r_date;
		this.capacity = capacity;
	}



	public Rent() {
		// TODO Auto-generated constructor stub
	}


        //중복체크할때 
	public Rent(String location, String r_date) {
		this.location = location;
		this.r_date = r_date;
	}
	
	public Rent(int no, int status, String reason) {
		this.status = status;
		this.no = no;
		this.reason = reason;
	}

	// 대관 글쓰기 할떄
	public Rent(String id, String name, String location, String r_date, String purpose, String capacity) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.r_date = r_date;
		this.purpose = purpose;
		this.capacity = capacity;
	}






    public int getNo() {
		return no;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public Date getRegDate() {
		return regDate;
	}

	public String getR_date() {
		return r_date;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getCapacity() {
		return capacity;
	}

	public int getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	public void validate(Map<String, Boolean> errors) {
		statusEmpty(errors, location, "대관장소를 입력하십시오");
		statusEmpty(errors, r_date, "대관날짜를 입력하십시오");
		statusEmpty(errors, purpose, "대관목적을 입력하십시오");
		statusEmpty(errors, capacity, "인원수를 입력하십시오");

	}

	private void statusEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty())
			errors.put(fieldName, Boolean.TRUE);
	}


}
