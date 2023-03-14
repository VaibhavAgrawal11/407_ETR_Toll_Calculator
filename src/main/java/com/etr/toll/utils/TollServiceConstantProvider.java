package com.etr.toll.utils;

public class TollServiceConstantProvider {
	
	public static final String FILE_PATH = "src/main/resources/static/interchanges.json";
	public static final double TOLL_RATE_PER_KM = 0.25;
	public static final String JSON_LOCATION_NODE = "locations";
	
	//Error Messages
	public static final String FILE_NOT_FOUND_ERROR_MSG = "Invalid file path";
	public static final String INVALID_LOCATION_ID_MSG = "Invalid Location id";
	public static final String IDENTICAL_SOURCE_DESTINATION_MSG = "Source Id and Destination Id are same";
	public static final String LOCATION_NOT_FOUND_MSG = "Invalid location id: ";
	public static final String NO_ROUTE_FOUND_MSG = "No route found";

}
