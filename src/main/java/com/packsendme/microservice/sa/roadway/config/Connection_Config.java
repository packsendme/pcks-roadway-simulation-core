package com.packsendme.microservice.sa.roadway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix="packsendme-roadwaysa-server")
public class Connection_Config {

	@Value(value = "${kafka.topic.roadwayBRE}")
	public String topic_roadway_sa;
		
	
}
