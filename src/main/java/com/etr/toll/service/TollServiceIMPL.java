package com.etr.toll.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.etr.toll.model.Location;
import com.etr.toll.model.LocationDetails;
import com.etr.toll.model.LocationList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TollServiceIMPL implements ITollService {
	
	private List<Location> locations = new ArrayList<>();
	private List<LocationList> locationListReponse =  new ArrayList<>();
	
	
	public List<LocationList> getAllLocations(){
		try {
			locations = getLocationsFromJsonFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Location location : locations) {
			LocationList loc = new LocationList();
			loc.setCode(location.getId());
			loc.setDescription(location.getDetails().getName());
			locationListReponse.add(loc);
	    }
	    
		return locationListReponse;
	}
	
	public List<Location> getLocationsFromJsonFile() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(new File("src/main/resources/static/interchanges.json"));
		JsonNode locationsNode = rootNode.get("locations");
		Iterator<String> stringIterator = locationsNode.fieldNames();
		for (JsonNode locationNode : locationsNode) {
			String id = stringIterator.next();
			LocationDetails locationDetails = objectMapper.treeToValue (locationNode, LocationDetails.class);
			Location loc = new Location(Integer.valueOf(id), locationDetails);
			locations.add(loc);
		}


		return locations;
	}

	

}
