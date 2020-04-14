package com.packsendme.microservice.sa.roadway.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;
import com.packsendme.microservice.sa.roadway.service.Roadway_Service;


@RestController
@RequestMapping("/roadway")
public class RoadwayController {

	@Autowired
	private Roadway_Service roadwayService; 	
	
	@GetMapping("/simulation")
	public ResponseEntity<?> getSimulation(
			@Validated @PathVariable ("simulationReqDto") SimulationRequest_Dto simulationReqDto
			) throws JsonProcessingException, IOException {		
		return roadwayService.getSimulationCosts(simulationReqDto);
	}

}
