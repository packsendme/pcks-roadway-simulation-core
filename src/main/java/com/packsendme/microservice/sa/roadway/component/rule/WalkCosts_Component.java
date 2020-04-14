package com.packsendme.microservice.sa.roadway.component.rule;

import org.springframework.stereotype.Component;

import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;
import com.packsendme.lib.simulation.way.roadway.response.dto.SimulationWalkResponse_Dto;
import com.packsendme.roadway.bre.rule.instance.model.WalkingInstance_Model;


@Component
public class WalkCosts_Component implements ICosts<SimulationWalkResponse_Dto, SimulationRequest_Dto, WalkingInstance_Model> {

	@Override
	public SimulationWalkResponse_Dto CostAnalysis(SimulationRequest_Dto t, WalkingInstance_Model r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double generateCost(SimulationRequest_Dto t) {
		// TODO Auto-generated method stub
		return null;
	}

}
