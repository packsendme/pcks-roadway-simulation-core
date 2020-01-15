package com.packsendme.microservice.roadways;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceLandSAServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceLandSAServerApplication.class, args);
	}
}

