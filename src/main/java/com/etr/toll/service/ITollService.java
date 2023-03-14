package com.etr.toll.service;

import java.util.List;

import com.etr.toll.exception.TollException;
import com.etr.toll.model.LocationList;
import com.etr.toll.model.TollRequest;
import com.etr.toll.model.TollResponse;

public interface ITollService {

	List<LocationList> getAllLocations() throws TollException;
	
	TollResponse calculateToll(TollRequest request) throws TollException;

}
