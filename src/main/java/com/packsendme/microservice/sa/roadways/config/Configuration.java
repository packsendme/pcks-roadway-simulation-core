package com.packsendme.microservice.sa.roadways.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix="packsendme-roadwaysa-server")
public class Configuration {

	@Setter
	@Getter
	public String placeGoogleAPI;
	
	@Setter
	@Getter
	public String placeAPIKey;
		
	
}
