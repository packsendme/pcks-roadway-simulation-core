package com.packsendme.microservice.roadway.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.packsendme.lib.simulation.http.SimulationRequest_Dto;

@FeignClient(name="packsendme-google-api")
public interface IGoogleAPI_Client {

	@PostMapping("/api/google/tracking")
	public ResponseEntity<?> getTracking(
			@RequestHeader("isoLanguageCode") String isoLanguageCode, 
			@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,
			@RequestHeader("originApp") String originApp,
			@Validated @RequestBody SimulationRequest_Dto simulation);
}
