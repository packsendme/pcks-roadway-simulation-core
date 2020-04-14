package com.packsendme.microservice.sa.roadway.component.rule;

import org.springframework.stereotype.Component;

import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;
import com.packsendme.lib.simulation.way.roadway.response.dto.SimulationTruckResponse_Dto;
import com.packsendme.roadway.bre.rule.instance.model.TruckInstance_Model;

 
@Component
public class TruckCosts_Component implements ICosts<SimulationTruckResponse_Dto, SimulationRequest_Dto, TruckInstance_Model> {

	@Override
	public SimulationTruckResponse_Dto CostAnalysis(SimulationRequest_Dto t, TruckInstance_Model r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double generateCost(SimulationRequest_Dto t) {
		// TODO Auto-generated method stub
		return null;
	}
 

}
