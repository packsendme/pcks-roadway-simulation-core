package com.packsendme.microservice.roadway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cache_Config {

	// Roadway
	@Value(value = "${redis.cache.roadwayBRE_SA}")
	public String roadwayBRE_SA;

	// TollsFuel
	@Value(value = "${redis.cache.tollsfuelBRE_SA}")
	public String tollsfuelBRE_SA;

	// FinanceCostsDelivery	
	@Value(value = "${redis.cache.financeCostDeliveryBRE_SA}")
	public String financeCostDeliveryBRE_SA;

}
