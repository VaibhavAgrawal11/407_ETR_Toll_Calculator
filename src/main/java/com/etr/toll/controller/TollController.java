package com.etr.toll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.etr.toll.exception.TollException;
import com.etr.toll.model.LocationList;
import com.etr.toll.model.TollRequest;
import com.etr.toll.model.TollResponse;
import com.etr.toll.service.ITollService;

/**
 * This is Rest Controller which creates 
 * end-points to get list of all the locations 
 * and get toll cost
 * 
 * @author Vaibhav Agrawal
 *
 */
@RestController
public class TollController {

	@Autowired
	ITollService service;

	/**
	 * WelcomeMessage: Welcome API
	 * @return welcome message
	 */
	@GetMapping("/")
	public String welcomeMessage() {
		return "Welcome to 407 ETR Toll calculator assiginment!";
	}

	/**
	 * List all Location: This end-point gives list of location
	 * @return List of Locations
	 * @throws TollException
	 */
	@GetMapping("/loc")
	public ResponseEntity<List<LocationList>> listLocations() throws TollException{
		return new ResponseEntity<>(service.getAllLocations(),HttpStatus.MULTI_STATUS);
	}
	
	/**
	 * Calculate Toll: Provide source Id and Destination ID in request
	 * @param request
	 * @return Toll details along with toll cost
	 * @throws TollException
	 */
	@GetMapping("/toll")
	public ResponseEntity<TollResponse> calculateToll(@RequestBody TollRequest request) throws TollException{
		return new ResponseEntity<>(service.calculateToll(request), HttpStatus.OK);
	}

}
