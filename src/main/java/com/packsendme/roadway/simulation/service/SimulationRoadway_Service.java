package com.packsendme.roadway.simulation.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest_Dto;
import com.packsendme.roadway.simulation.component.LoadDataFacadeImpl;
import com.packsendme.roadway.simulation.dto.LoadData_Dto;

@Service
@ComponentScan("com.packsendme.microservice.roadway.component")
public class SimulationRoadway_Service {
	
	@Autowired(required=true)
	private LoadDataFacadeImpl roadwayLoadData;

	
	public ResponseEntity<?> getSimulationCosts(SimulationRoadwayRequest_Dto simulationData, Map header) {
		Response<SimulationRoadwayResponse_Dto> responseObj = null;
		try {
			LoadData_Dto loadDataDto_Obj = roadwayLoadData.getData(simulationData, header);
			
			RoadwayCalculateCosts roadwayCosts = new RoadwayCalculateCosts();
			SimulationRoadwayResponse_Dto simulationRoadwayResp = roadwayCosts.calculateCostsByCategory(loadDataSA_dto.simulationTrackingAPI, loadDataSA_dto.simulationData);
			
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
