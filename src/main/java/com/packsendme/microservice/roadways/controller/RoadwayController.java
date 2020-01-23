package com.packsendme.microservice.roadways.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.packsendme.microservice.roadways.service.RoadwayService;


@RestController
public class RoadwayController {

	
	@Autowired
	private RoadwayService roadwayService; 
	
	
	@GetMapping("/roadway/sa/{origins}/{destinations}")
	public ResponseEntity<?> loadDistancesBetweenCity(
			@Validated @PathVariable ("origins") String origins,
			@Validated @PathVariable ("destinations") String destinations) throws JsonProcessingException, IOException {		
		return roadwayService.findDistancesByCity(origins, destinations);
	}

}
