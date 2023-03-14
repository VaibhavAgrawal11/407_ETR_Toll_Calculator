package com.etr.toll.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

	@JsonProperty("id")
	private int id;
	private LocationDetails details;
	

	public Location() {}

	public Location(int id, LocationDetails details) {
		this.id = id;
		this.details =details;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public LocationDetails getDetails() {
		return details;
	}

	public void setDetails(LocationDetails details) {
		this.details = details;
	}
	

}

