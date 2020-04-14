package com.packsendme.microservice.sa.roadway.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;

@FeignClient(name="packsendme-google-api")
public interface APIClient {

	@GetMapping("/api/google/tracking")
	public ResponseEntity<?> getTracking(@Validated @RequestBody SimulationRequest_Dto simulation);
}
