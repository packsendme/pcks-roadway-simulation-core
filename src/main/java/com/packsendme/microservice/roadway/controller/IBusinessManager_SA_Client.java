package com.packsendme.microservice.roadway.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="packsendme-businessrule-sa-server")
public interface IBusinessManager_SA_Client {

	@GetMapping("/roadway/{name_rule}")
	public ResponseEntity<?> getRoadwayBRE_SA(@Validated  @PathVariable ("name_rule") String name_rule);
	
	@GetMapping("/financecostdelivery/{rule_type}")
	public ResponseEntity<?> getFinanceCostDeliveryBRE_SA(@Validated @PathVariable ("rule_type") String rule_type);		

}
