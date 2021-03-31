package com.packsendme.roadway.simulation.component;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest;
import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest_Dto;
import com.packsendme.roadbrewa.entity.Roadway;

public interface IRoadway {
	
	public SimulationRoadwayRequest_Dto getData(SimulationRoadwayRequest simulationData, Map header);
	
	ResponseEntity<?> getGoogleMap_API(Map header, SimulationRoadwayRequest simulationData);

	Roadway getTransport_BRE(String key);

	ResponseEntity<?> getRateExchange(String current);
	
	ResponseEntity<?> getAntt_BRE();
}
