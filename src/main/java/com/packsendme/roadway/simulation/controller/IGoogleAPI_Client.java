package com.packsendme.roadway.simulation.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="pcks-google-api")
public interface IGoogleAPI_Client {

	@GetMapping("/distance/{from}/{to}/{measurement}")
	public ResponseEntity<?> getTracking(
			@RequestHeader("isoLanguageCode") String isoLanguageCode, 
			@RequestHeader("isoCountryCode") String isoCountryCode,
			@RequestHeader("isoCurrencyCode") String isoCurrencyCode,
			@RequestHeader("originApp") String originApp,
			@PathVariable("from") String from,
			@PathVariable("to") String to,
			@PathVariable("measurement") String measurement);
	
}
