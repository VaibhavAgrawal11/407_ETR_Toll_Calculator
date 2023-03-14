package com.etr.toll.model;

import java.util.List;

public class LocationDetails {

	private String name;
	private double lat;
	private double lng;
	private List<Route> routes;
	private String devcomment;
	
	public String getDevcomment() {
		return devcomment;
	}
	public void setDevcomment(String devcomment) {
		this.devcomment = devcomment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	@Override
	public String toString() {
		return "LocationDetails [name=" + name + ", lat=" + lat + ", lng=" + lng + ", routes=" + routes
				+ ", devcomment=" + devcomment + "]";
	}

	

}
