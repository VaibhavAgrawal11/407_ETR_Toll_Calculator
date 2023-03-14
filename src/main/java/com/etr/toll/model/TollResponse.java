package com.etr.toll.model;

public class TollResponse {

	private String source;
	private String destination;
	private double cost;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "TollResponse [source=" + source + ", destination=" + destination + ", cost=" + cost + "]";
	}

	
}
