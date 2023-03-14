package com.etr.toll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etr.toll.model.LocationList;
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
	
}
