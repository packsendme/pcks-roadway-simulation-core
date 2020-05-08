package com.packsendme.microservice.roadway.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.packsendme.lib.simulation.http.SimulationRequest_Dto;
import com.packsendme.microservice.roadway.service.Roadway_Service;


@RestController
@RequestMapping("/roadway")
public class RoadwayController {

	@Autowired
	private Roadway_Service roadwayService; 	
	
	@GetMapping("/simulation")
	public ResponseEntity<?> getSimulation(
			@RequestHeader(value = "isoLanguageCode") String isoLanguageCode, 
			@RequestHeader(value = "isoCountryCode") String isoCountryCode,
			@RequestHeader(value = "isoCurrencyCode") String isoCurrencyCode, 
			@Validated @RequestBody SimulationRequest_Dto simulationReqDto) throws JsonProcessingException, IOException {	
		
		Map<String, String> isoInformation = new HashMap<String, String>();
		isoInformation.put("isoLanguageCode", isoLanguageCode);
		isoInformation.put("isoCountryCode", isoCountryCode);
		isoInformation.put("isoCurrencyCode", isoCurrencyCode);
		
		return roadwayService.getSimulationCosts(simulationReqDto,isoInformation);
	}

}
