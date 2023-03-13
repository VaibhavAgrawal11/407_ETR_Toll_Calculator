package com.etr.toll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TollController {
	@GetMapping
	public String welcomeMessage() {
		return "Welcome to 407 ETR Toll calculator assiginment!";
	}
}
