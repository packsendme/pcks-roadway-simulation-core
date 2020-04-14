package com.packsendme.microservice.sa.roadway.component.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;
import com.packsendme.lib.simulation.way.roadway.response.dto.SimulationBicycleResponse_Dto;
import com.packsendme.roadway.bre.rule.instance.model.BicycleInstance_Model;

@Component
public class BicycleCosts_Component implements ICosts<SimulationBicycleResponse_Dto, SimulationRequest_Dto, BicycleInstance_Model> {

	@Autowired
	private SimulationBicycleResponse_Dto simulationBicycleRespDto;
	
	@Override
	public SimulationBicycleResponse_Dto CostAnalysis(SimulationRequest_Dto request, BicycleInstance_Model business) {
		try {
			//SimulationBicycleResponse_Dto simulationBicycleRespDto = new SimulationBicycleResponse_Dto();
			if((request.weight_product <= business.weight_max_bicycle) &&  (request.distances_places <= business.distance_max_bicycle)){
				simulationBicycleRespDto.cost_bicycle = generateCost(request,business);
				simulationBicycleRespDto.status_bicycle = true;
			}
			else if((request.weight_product > business.weight_max_bicycle) || (request.distances_places > business.distance_max_bicycle)){
				simulationBicycleRespDto.status_bicycle = false;
			}
			return simulationBicycleRespDto;
		}
		catch (Exception e) {
			e.printStackTrace();
			return simulationBicycleRespDto;
		}
	}

	@Override
	public Double generateCost(SimulationRequest_Dto request, BicycleInstance_Model business) {
		try {
			Double calcCostWeight = request.weight_product * business.bicycleCosts.cost_weight_bicycle;
			Double calcCostDistance = request.distances_places * business.bicycleCosts.cost_distance_bicycle;
			Double costTotal = calcCostWeight + calcCostDistance * business.bicycleCosts.cost_variable_bicycle;			
			return costTotal;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	 
 

}
