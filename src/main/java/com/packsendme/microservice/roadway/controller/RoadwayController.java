package com.packsendme.microservice.roadway.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.packsendme.lib.common.constants.generic.MetricUnitMeasurement_Constants;
import com.packsendme.lib.simulation.http.SimulationRequest_Dto;
import com.packsendme.microservice.roadway.service.Roadway_Service;


@RestController
@RequestMapping("/roadway")
public class RoadwayController {

	@Autowired
	private Roadway_Service roadwayService; 	
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/simulation")
	public ResponseEntity<?> getSimulation(
			@RequestHeader("isoLanguageCode") String isoLanguageCode, 
			@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,
			@Validated  @RequestParam ("address_origin") String address_origin,
			@Validated  @RequestParam ("address_destination") String address_destination,
			@Validated  @RequestParam ("type_product") String type_product,
			@Validated  @RequestParam ("weight_product") String weight_product,
			@Validated  @RequestParam ("type_delivery") String type_delivery,
			@Validated  @RequestParam ("unity_measurement_weight") String unity_measurement_weight) 
	throws JsonProcessingException, IOException 
	{	
		SimulationRequest_Dto simulationReqDto = new SimulationRequest_Dto();

		System.out.println(" ");
		System.out.println("====================================================");
		System.out.println(" - isoLanguageCode - "+ isoLanguageCode);
		System.out.println(" - isoCountryCode - "+ isoCountryCode);
		System.out.println(" - isoCurrencyCode - "+ isoCurrencyCode);
		System.out.println("====================================================");
		System.out.println("====================================================");
		System.out.println(" ");
		System.out.println(" - address_origin - "+ address_origin);
		System.out.println(" - address_destination - "+ address_destination);
		System.out.println(" - type_product - "+ type_product);
		System.out.println(" - weight_product - "+ weight_product);
		System.out.println(" - type_delivery - "+ type_delivery);
		System.out.println(" - unity_measurement_weight - "+ unity_measurement_weight);
		System.out.println("====================================================");
		System.out.println(" ");
		
		
		Map<String, String> isoInformation = new HashMap<String, String>();
		isoInformation.put("isoLanguageCode", isoLanguageCode);
		isoInformation.put("isoCountryCode", isoCountryCode);
		isoInformation.put("isoCurrencyCode", isoCurrencyCode);
		
		simulationReqDto.address_origin = address_origin;
		simulationReqDto.address_destination = address_destination;
		simulationReqDto.type_product = type_product;
		simulationReqDto.weight_product =  Double.parseDouble(weight_product);
		simulationReqDto.type_delivery = type_delivery;
		simulationReqDto.unity_measurement_weight = unity_measurement_weight;
		simulationReqDto.unity_measurement_distance_txt = MetricUnitMeasurement_Constants.kilometro_UnitMeasurement;
		return roadwayService.getSimulationCosts(simulationReqDto,isoInformation);
	}

}
