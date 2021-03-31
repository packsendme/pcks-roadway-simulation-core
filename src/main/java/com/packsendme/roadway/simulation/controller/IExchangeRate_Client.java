package com.packsendme.roadway.simulation.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="packsendme-exchange-api")
public interface IExchangeRate_Client {

	@GetMapping("/api/exchange/rate/{current}")
	public ResponseEntity<?> getExchange(@Validated  @RequestParam ("current") String current);		

}
