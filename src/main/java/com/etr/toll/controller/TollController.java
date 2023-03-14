package com.etr.toll.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.etr.toll.model.LocationList;
import com.etr.toll.model.TollRequest;
import com.etr.toll.model.TollResponse;
import com.etr.toll.service.ITollService;

@RestController
public class TollController {
	
	@Autowired
	ITollService service;
	
	@GetMapping("/")
	public String welcomeMessage() {
		return "Welcome to 407 ETR Toll calculator assiginment!";
	}
	
	@GetMapping("/loc")
	public List<LocationList> listLocations(){
		return service.getAllLocations();
	}
	
	@GetMapping("/toll")
	public TollResponse calculateToll(@RequestBody TollRequest request) throws IOException{
		return service.calculateToll(request);
	}
	
}
