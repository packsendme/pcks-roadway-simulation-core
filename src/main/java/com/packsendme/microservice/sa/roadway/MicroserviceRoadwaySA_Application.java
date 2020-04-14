package com.packsendme.microservice.sa.roadway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceRoadwaySA_Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRoadwaySA_Application.class, args);
	}
}

