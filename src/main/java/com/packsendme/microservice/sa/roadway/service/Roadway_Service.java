package com.packsendme.microservice.sa.roadway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.packsendme.lib.common.constants.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;
import com.packsendme.lib.simulation.roadway.response.dto.RoadwayCostsResponse_Dto;
import com.packsendme.lib.simulation.way.response.dto.SimulationRoadwayResponse_Dto;
import com.packsendme.microservice.sa.roadway.component.RoadwayConsumer_Component;
import com.packsendme.microservice.sa.roadway.controller.APIClient;

@Service
public class Roadway_Service {
	
	@Autowired
	private RoadwayConsumer_Component roadwayConsumerComponent;
	
	@Autowired
	private APIClient apiClient;
	
	
	public ResponseEntity<?> getSimulationCosts(SimulationRequest_Dto simulation) {
		Response<SimulationRoadwayResponse_Dto> responseObj = null;
		Gson gson = new Gson();
		try {
				//(1) Instance Google-API to get data (Roadway-Costs).
				ResponseEntity<?> costsResponse = apiClient.getSimulationCosts(simulation);
				if(costsResponse.getStatusCode() == HttpStatus.ACCEPTED) {
					String json = costsResponse.getBody().toString();
					RoadwayCostsResponse_Dto roadwayCosts_Dto = gson.fromJson(json, RoadwayCostsResponse_Dto.class);
					if (roadwayCosts_Dto.status_tolls == true) {
						
					}
				}	
			
				responseObj = new Response<SimulationRoadwayResponse_Dto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
				return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e ) {
			e.printStackTrace();
			responseObj = new Response<SimulationRoadwayResponse_Dto>(0,HttpExceptionPackSend.FAIL_EXECUTION.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
