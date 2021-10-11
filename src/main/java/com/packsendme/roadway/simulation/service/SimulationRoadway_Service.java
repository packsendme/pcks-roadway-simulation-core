package com.packsendme.roadway.simulation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.packsendme.cross.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.cross.common.response.Response;
import com.packsendme.roadway.commons.request.SimulationRoadwayRequest;
import com.packsendme.roadway.calculate.component.InstanceRuleCosts;
import com.packsendme.roadway.commons.dto.SimulationRoadwayDto;
import com.packsendme.roadway.commons.response.SimulationRoadwayResponse;
import com.packsendme.roadway.simulation.component.LoadDataFacadeImpl;
import com.packsendme.roadway.simulation.dao.SimulationDBImpl_Dao;
import com.packsendme.roadway.simulation.dto.SimulationResponse_Dto;

@Service
@ComponentScan("com.packsendme.roadway.simulation.component")
public class SimulationRoadway_Service {
	
	@Autowired(required=true)
	private LoadDataFacadeImpl roadwayLoadData;
	
	@Autowired(required=true)
	private SimulationDBImpl_Dao simulationDAO;

	
	public ResponseEntity<?> getSimulationTransport(SimulationRoadwayRequest simulationDataObj, Map header) {
		Response<SimulationRoadwayResponse> responseObj = null;
		try {
			// Load Data From API or Cache
			SimulationRoadwayDto simulationDataDto_Obj = roadwayLoadData.getData(simulationDataObj, header);
			InstanceRuleCosts instanceRulesObj = new  InstanceRuleCosts();
			
			// Instance JAR - Costs Calculate 
			SimulationRoadwayResponse simulationRoadwayResp = instanceRulesObj.instanceRulesCosts(simulationDataDto_Obj);
			
			responseObj = new Response<SimulationRoadwayResponse>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), simulationRoadwayResp);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			responseObj = new Response<SimulationRoadwayResponse>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> postSimulationResponse(SimulationRoadwayResponse entity) {
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
			System.out.println(e);
			responseObj = new Response<SimulationResponse_Dto>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> deleteSimulation(String id) {
		Response<SimulationRoadwayResponse> responseObj = null;
		try {
			// Delete Simulation
			simulationDAO.delete(id);
			responseObj = new Response<SimulationRoadwayResponse>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.OK);
		}
		catch (Exception e) {
			// TODO: handle exception
			responseObj = new Response<SimulationRoadwayResponse>(0,HttpExceptionPackSend.SIMULATION_ROADWAY.getAction(), null);
			return new ResponseEntity<>(responseObj, HttpStatus.BAD_REQUEST);
		}
	}

	
	
}
