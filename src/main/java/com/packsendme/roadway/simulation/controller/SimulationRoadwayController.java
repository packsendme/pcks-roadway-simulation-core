package com.packsendme.roadway.simulation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest;
import com.packsendme.lib.roadway.simulation.response.SimulationRoadwayResponse;
import com.packsendme.roadway.simulation.service.SimulationRoadway_Service;


@RestController
@RequestMapping("/roadway")
public class SimulationRoadwayController {

	@Autowired
	private SimulationRoadway_Service roadwayService;

	private Map<String, String> header = new HashMap<String, String>();
	
	//			@ModelAttribute
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/simulation/transport")
	public ResponseEntity<?> getSimulation(
			@RequestHeader("isoLanguageCode") String isoLanguageCode, 
			@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,
			@RequestHeader("originApp") String originApp,
			@Validated @RequestBody  SimulationRoadwayRequest simulationObj)
		throws JsonProcessingException, IOException 
	{	
		header.put("isoLanguageCode", isoLanguageCode);
		header.put("isoCountryCode", isoCountryCode);
		header.put("isoCurrencyCode", isoCurrencyCode);
		header.put("originApp", originApp);
		return roadwayService.getSimulationTransport(simulationObj,header);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/simulation")
	public ResponseEntity<?> postSimulation(
			@RequestHeader("isoLanguageCode") String isoLanguageCode, 
			@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,
			@RequestHeader("originApp") String originApp,
			@Validated @RequestBody  SimulationRoadwayResponse simulationObj)
		throws JsonProcessingException, IOException 
	{	
		header.put("isoLanguageCode", isoLanguageCode);
		header.put("isoCountryCode", isoCountryCode);
		header.put("isoCurrencyCode", isoCurrencyCode);
		header.put("originApp", originApp);
		
		return roadwayService.postSimulationResponse(simulationObj);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@DeleteMapping("/simulation")
	public ResponseEntity<?> deleteSimulation(
			@RequestHeader("isoLanguageCode") String isoLanguageCode, 
			@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,
			@RequestHeader("originApp") String originApp,
			@Validated @RequestParam("id") String id)
		throws JsonProcessingException, IOException 
	{	
		header.put("isoLanguageCode", isoLanguageCode);
		header.put("isoCountryCode", isoCountryCode);
		header.put("isoCurrencyCode", isoCurrencyCode);
		header.put("originApp", originApp);
		
		return roadwayService.deleteSimulation(id);
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/simulation")
	public ResponseEntity<?> getSimulationAll(
			@RequestHeader("isoLanguageCode") String isoLanguageCode, 
			@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,
			@RequestHeader("originApp") String originApp)
		throws JsonProcessingException, IOException 
	{	
		header.put("isoLanguageCode", isoLanguageCode);
		header.put("isoCountryCode", isoCountryCode);
		header.put("isoCurrencyCode", isoCurrencyCode);
		header.put("originApp", originApp);
		
		return roadwayService.getSimulation();
	}


}
