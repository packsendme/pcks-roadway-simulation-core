package com.packsendme.microservice.roadways.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.packsendme.lib.common.constants.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.microservice.roadways.config.Configuration;
import com.packsendme.microservice.roadways.dto.LocationDto;
 

@Service
public class RoadwayService {
	
	@Autowired
	private Configuration configuration;

	public ResponseEntity<?> findDistancesByCity(String origin, String destination) {
		Response<LocationDto> responseObj = null;
		LocationDto location = new LocationDto();
		try {
			
			System.out.println(" -----------------------------------------------");

			System.out.println("  ");

			System.out.println("origin / destination >> "+ origin +"/"+ destination);
			RestTemplate restTemplate = new RestTemplate();
			int distanceInt = 0;
			String distanceS = "";
			
			location.setOrigin(origin);
			location.setDestination(destination);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity(headers);
			
			 Map<String, String> urlParameters = new HashMap<>();
			 urlParameters.put("origins", origin);
			 urlParameters.put("destinations", destination);
			 urlParameters.put("key", configuration.placeAPIKey);
			 
				System.out.println("GOOGLE-key  >> "+ configuration.placeAPIKey);
				System.out.println("GOOGLE-placeGoogleAPI  >> "+ configuration.placeGoogleAPI);


			ResponseEntity<String> response = restTemplate.exchange(configuration.placeGoogleAPI, HttpMethod.GET, entity, String.class, origin, destination);
			if (response.getStatusCode() == HttpStatus.OK) {
				
				System.out.println(" RESULT OK  >> "+ response.getStatusCode());
				
				byte[] jsonData = response.getBody().getBytes(); 
				//create ObjectMapper instance
				ObjectMapper objectMapper = new ObjectMapper();

				//read JSON like DOM Parser
				JsonNode rootNode = objectMapper.readTree(jsonData);
				JsonNode rowsNode = rootNode.path("rows");
	            if (rowsNode.isArray()) {
					System.out.println(" RESULT rowsNode isArray - FULL");

	            	for (JsonNode rowObj : rowsNode) {
						JsonNode elementsNode = rowObj.path("elements");
						for (JsonNode elementObj : elementsNode) {
							String status = elementObj.path("status").asText();
							System.out.println(" RESULT status  >> "+ status);

							if(status != "OK") {
					            location.setDistanceInt(0);
					            location.setDistanceText("0");
					            location.setStatus(status);
							}
							else if(status == "OK") {
					            JsonNode distanceNode = elementObj.path("distance");
					            distanceInt = distanceNode.path("value").asInt();
					            distanceS = distanceNode.path("text").asText();
					            location.setDistanceInt(distanceInt);
					            location.setDistanceText(distanceS);
					            location.setStatus(status);
							}
						}	
					}
	            }
				responseObj = new Response<LocationDto>(0,HttpExceptionPackSend.GOOGLEAPI_PLACE.getAction(), location);
				return new ResponseEntity<>(responseObj, HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<>(responseObj, HttpStatus.NOT_FOUND);
			}

		}
		catch (IOException e ) {
			e.printStackTrace();
			responseObj = new Response<LocationDto>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
