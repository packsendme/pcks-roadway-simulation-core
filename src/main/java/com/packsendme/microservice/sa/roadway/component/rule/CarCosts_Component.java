package com.packsendme.microservice.sa.roadway.component.rule;

import org.springframework.stereotype.Component;

import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;
import com.packsendme.lib.simulation.way.roadway.response.dto.SimulationCarResponse_Dto;
import com.packsendme.roadway.bre.rule.instance.model.CarInstance_Model;

@Component
public class CarCosts_Component implements ICosts<SimulationCarResponse_Dto, SimulationRequest_Dto, CarInstance_Model> {

	@Override
	public SimulationCarResponse_Dto CostAnalysis(SimulationRequest_Dto t, CarInstance_Model r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double generateCost(SimulationRequest_Dto t) {
		// TODO Auto-generated method stub
		return null;
	}

}
