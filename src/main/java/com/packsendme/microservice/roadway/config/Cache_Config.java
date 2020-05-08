package com.packsendme.microservice.roadway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Cache_Config {

	// Roadway
	@Value(value = "${redis.cache.roadwayBRE_SA}")
	public String roadwayBRE_SA;

	@Value(value = "${redis.cache.roadwayBRE_EURO}")
	public String roadwayBRE_EURO;

	@Value(value = "${redis.cache.roadwayBRE_NA}")
	public String roadwayBRE_NA;

	
	// TollsFuel
	@Value(value = "${redis.cache.tollsfuelBRE_SA}")
	public String tollsfuelBRE_SA;

	@Value(value = "${redis.cache.tollsfuelBRE_EURO}")
	public String tollsfuelBRE_EURO;

	@Value(value = "${redis.cache.tollsfuelBRE_NA}")
	public String tollsfuelBRE_NA;

	
	// FinanceCostsDelivery	
	@Value(value = "${redis.cache.financeCostDeliveryBRE_SA}")
	public String financeCostDeliveryBRE_SA;

	@Value(value = "${redis.cache.financeCostDeliveryBRE_EURO}")
	public String financeCostDeliveryBRE_EURO;

	@Value(value = "${redis.cache.financeCostDeliveryBRE_NA}")
	public String financeCostDeliveryBRE_NA;

}
