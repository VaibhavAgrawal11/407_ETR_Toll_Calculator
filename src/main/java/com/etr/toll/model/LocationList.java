package com.etr.toll.model;

public class LocationList {

	private int code;
	private String Description;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Override
	public String toString() {
		return "LocationList [code=" + code + ", Description=" + Description + "]";
	}
	
}
