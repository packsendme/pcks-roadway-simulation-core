package com.packsendme.microservice.roadway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceRoadway_Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRoadway_Application.class, args);
	}
}

