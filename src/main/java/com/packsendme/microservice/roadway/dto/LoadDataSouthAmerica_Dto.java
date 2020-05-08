package com.packsendme.microservice.roadway.dto;

import com.packsendme.lib.common.response.dto.api.GoogleAPITrackingResponse_Dto;
import com.packsendme.lib.simulation.request.dto.SimulationDataForCalculateRequest_Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoadDataSouthAmerica_Dto {
	
	public GoogleAPITrackingResponse_Dto simulationTrackingAPI;
	public SimulationDataForCalculateRequest_Dto simulationData;
	
	
	public LoadDataSouthAmerica_Dto(GoogleAPITrackingResponse_Dto simulationTrackingAPI,
			SimulationDataForCalculateRequest_Dto simulationData) {
		super();
		this.simulationTrackingAPI = simulationTrackingAPI;
		this.simulationData = simulationData;
	}

	

}
