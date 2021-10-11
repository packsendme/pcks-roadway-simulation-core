package com.packsendme.roadway.simulation.component;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.packsendme.roadway.commons.request.SimulationRoadwayRequest;
import com.packsendme.roadway.commons.dto.SimulationRoadwayDto;
import com.packsendme.roadway.commons.entity.Roadway;

public interface IRoadway {
	
	public SimulationRoadwayDto getData(SimulationRoadwayRequest simulationData, Map header);
	
	ResponseEntity<?> getGoogleMap_API(Map header, SimulationRoadwayRequest simulationData);

	Roadway getTransport_BRE(String key);

	ResponseEntity<?> getRateExchange(Map header, String current);
	
	ResponseEntity<?> getAntt_BRE();
}
