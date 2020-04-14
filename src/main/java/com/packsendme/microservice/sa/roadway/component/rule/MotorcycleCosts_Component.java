package com.packsendme.microservice.sa.roadway.component.rule;

import org.springframework.stereotype.Component;

import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;
import com.packsendme.lib.simulation.way.roadway.response.dto.SimulationMotorcycleResponse_Dto;
import com.packsendme.roadway.bre.rule.instance.model.MotorcycleInstance_Model;


@Component
public class MotorcycleCosts_Component implements ICosts<SimulationMotorcycleResponse_Dto, SimulationRequest_Dto, MotorcycleInstance_Model> {

	@Override
	public SimulationMotorcycleResponse_Dto CostAnalysis(SimulationRequest_Dto t, MotorcycleInstance_Model r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double generateCost(SimulationRequest_Dto t) {
		// TODO Auto-generated method stub
		return null;
	}


}
