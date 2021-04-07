package com.packsendme.roadway.simulation.service;

import java.util.List;
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
import com.packsendme.roadway.simulation.dao.Simulation_Dao;
import com.packsendme.roadway.simulation.dto.SimulationResponse_Dto;

@Service
@ComponentScan("com.packsendme.roadway.simulation.component")
public class SimulationRoadway_Service {
	
	@Autowired(required=true)
	private LoadDataFacadeImpl roadwayLoadData;
	
	@Autowired(required=true)
	private Simulation_Dao simulationDAO;

	
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
	
	public ResponseEntity<?> postSimulationResponse(SimulationRoadwayResponse entity, Map header) {
		Response<SimulationRoadwayResponse> responseObj = null;
		try {
			// Save SimulationDAO
			simulationDAO.save(entity);
			responseObj = new Response<SimulationRoadwayResponse>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<SimulationRoadwayResponse>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> getSimulation() {
		Response<SimulationResponse_Dto> responseObj = null;
		try {
			// Save SimulationDAO
			List<SimulationRoadwayResponse> simulationL = simulationDAO.findAll();
			SimulationResponse_Dto simulationResponse = new SimulationResponse_Dto(simulationL);
			responseObj = new Response<SimulationResponse_Dto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), simulationResponse);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<SimulationResponse_Dto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}

	
	
}
