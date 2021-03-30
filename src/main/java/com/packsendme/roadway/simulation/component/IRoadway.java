package com.packsendme.roadway.simulation.component;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest_Dto;
import com.packsendme.roadbrewa.entity.Roadway;
import com.packsendme.roadway.simulation.dto.LoadData_Dto;

public interface IRoadway {
	
	public LoadData_Dto getData(SimulationRoadwayRequest_Dto simulationData, Map header);
	
	ResponseEntity<?> getGoogleMap_API(Map header, SimulationRoadwayRequest_Dto simulation_dto);

	Roadway getTransport_BRE(String key);


}
