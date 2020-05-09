package com.packsendme.microservice.roadway.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="packsendme-exchange-api")
public interface IExchangeRate_Client {

	@GetMapping("/api/exchange/{current}")
	public ResponseEntity<?> getExchange(@Validated  @PathVariable ("current") String current);		

}
