package com.packsendme.microservice.roadway.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="packsendme-businessrule-sa-server")
public interface IBusinessManager_SA_Client {

	@GetMapping("/businessrule/sa/roadway")
	public ResponseEntity<?> getRoadwayBRE_SA(@Validated  @RequestParam ("name_rule") String name_rule);
	
	@GetMapping("/businessrule/sa/financecostdelivery")
	public ResponseEntity<?> getFinanceCostDeliveryBRE_SA(@Validated @RequestParam ("rule_type") String rule_type);		

}
