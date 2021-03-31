package com.packsendme.roadway.simulation.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.lib.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest;
import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest_Dto;
import com.packsendme.lib.roadway.simulation.response.SimulationRoadwayResponse;
import com.packsendme.lib.roadwaycalculate.rulesinstance.InstanceRuleCosts;
import com.packsendme.roadway.simulation.component.LoadDataFacadeImpl;

@Service
@ComponentScan("com.packsendme.roadway.simulation.component")
public class SimulationRoadway_Service {
	
	@Autowired(required=true)
	private LoadDataFacadeImpl roadwayLoadData;

	
	public ResponseEntity<?> getCostsTransport(SimulationRoadwayRequest simulationDataObj, Map header) {
		Response<SimulationRoadwayResponse> responseObj = null;
		try {
			// Load Data From API or Cache
			SimulationRoadwayRequest_Dto simulationDataDto_Obj = roadwayLoadData.getData(simulationDataObj, header);
			InstanceRuleCosts instanceRulesObj = new  InstanceRuleCosts();
			
			// Instance JAR - Costs Calculate 
			SimulationRoadwayResponse simulationRoadwayResp = instanceRulesObj.instanceRulesCosts(simulationDataDto_Obj);
			
			responseObj = new Response<SimulationRoadwayResponse>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), simulationRoadwayResp);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<SimulationRoadwayResponse>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
