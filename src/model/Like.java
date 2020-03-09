package model;

import java.util.HashMap;
import java.util.Map;

public class Like {

	
	Map<Integer, String> like = new HashMap<Integer, String>();
	
	
	
	private int num;
	private String id;
	private int count;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
		
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Like(int num, String id) {
		
		this.num = num;
		this.id = id;
	}
	
	public Like(int num, int count) {
		
		this.num = num;
		this.count = count;
	}
	
	
	
	
}
