package com.packsendme.roadway.simulation.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.packsendme.roadway.commons.response.SimulationRoadwayResponse;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class SimulationResponse_Dto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<SimulationRoadwayResponse> simulationResponseL = new ArrayList<SimulationRoadwayResponse>();

	public SimulationResponse_Dto(List<SimulationRoadwayResponse> simulationResponseL) {
		super();
		this.simulationResponseL = simulationResponseL;
	}
}
