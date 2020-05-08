package com.packsendme.microservice.roadway.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.lib.roadwaycalculate.component.RoadwayInstanceCosts;
import com.packsendme.lib.simulation.http.SimulationRequest_Dto;
import com.packsendme.lib.simulation.roadway.SimulationRoadwayResponse_Dto;
import com.packsendme.microservice.roadway.component.loader.LoadDataSouthAmerica_Component;
import com.packsendme.microservice.roadway.dto.LoadDataSouthAmerica_Dto;

@Service
@ComponentScan("com.packsendme.microservice.roadway.component.loader")
public class Roadway_Service {
	
	@Autowired(required=true)
	private LoadDataSouthAmerica_Component loadDataSA_component;
	 	
	
	public ResponseEntity<?> getSimulationCosts(SimulationRequest_Dto simulationData, Map<String, String> isoInformation) {
		Response<SimulationRoadwayResponse_Dto> responseObj = null;
		try {
			LoadDataSouthAmerica_Dto loadDataSA_dto = loadDataSA_component.getDataSouthAmerica(simulationData, isoInformation);
			
			RoadwayInstanceCosts roadwayInstance = new RoadwayInstanceCosts();
			SimulationRoadwayResponse_Dto simulationRoadwayResp = roadwayInstance.instanceRulesCosts(loadDataSA_dto.simulationTrackingAPI, loadDataSA_dto.simulationData);
			
			responseObj = new Response<SimulationRoadwayResponse_Dto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), simulationRoadwayResp);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<SimulationRoadwayResponse_Dto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
