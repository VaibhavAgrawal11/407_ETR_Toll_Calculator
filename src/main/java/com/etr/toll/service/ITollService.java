package com.etr.toll.service;

import java.io.IOException;
import java.util.List;

import com.etr.toll.model.LocationList;
import com.etr.toll.model.TollRequest;
import com.etr.toll.model.TollResponse;

public interface ITollService {

	List<LocationList> getAllLocations();
	
	TollResponse calculateToll(TollRequest request) throws IOException;

}
