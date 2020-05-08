package com.packsendme.microservice.roadway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.packsendme.microservice.roadway.component"})

public class MicroserviceRoadway_Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRoadway_Application.class, args);
	}
}

