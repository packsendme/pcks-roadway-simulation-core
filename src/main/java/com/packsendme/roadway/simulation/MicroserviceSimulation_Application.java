package com.packsendme.roadway.simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceSimulation_Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSimulation_Application.class, args);
	}
}

