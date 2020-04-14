package com.packsendme.microservice.sa.roadway.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.packsendme.lib.simulation.request.dto.SimulationRequest_Dto;
import com.packsendme.lib.simulation.way.response.dto.SimulationRoadwayResponse_Dto;
import com.packsendme.microservice.sa.roadway.component.rule.BicycleCosts_Component;
import com.packsendme.microservice.sa.roadway.component.rule.CarCosts_Component;
import com.packsendme.microservice.sa.roadway.component.rule.MotorcycleCosts_Component;
import com.packsendme.microservice.sa.roadway.component.rule.TruckCosts_Component;
import com.packsendme.microservice.sa.roadway.component.rule.WalkCosts_Component;
import com.packsendme.roadway.bre.rule.model.RoadwayBRE_Model;

@Component
public class RoadwayCostsFacade_Component {
	
	private SimulationRoadwayResponse_Dto roadwayResponseDto; 
	
	@Autowired
	private BicycleCosts_Component bicycleCosts;
	
	@Autowired
	private CarCosts_Component carCosts;
	
	@Autowired
	private MotorcycleCosts_Component motorcycleCosts;
	
	@Autowired
	private TruckCosts_Component trunkCosts;
	
	@Autowired
	private WalkCosts_Component walkCosts;
	
	@Autowired
	private RoadwayConsumer_Component roadwayConsumerComponent;
	
			
	public SimulationRoadwayResponse_Dto generationCosts(SimulationRequest_Dto requestSimulation) {
		// (1) Consumer Topic - Roadway
		RoadwayBRE_Model roadwayModel = (RoadwayBRE_Model) roadwayConsumerComponent.consumerTopic();
		
		// Call Component to Transport
		roadwayResponseDto.simulationBicycle = bicycleCosts.CostAnalysis(requestSimulation, roadwayModel.getBicycle());
		roadwayResponseDto.simulationCar = carCosts.CostAnalysis(requestSimulation, roadwayModel.getCar());
		roadwayResponseDto.simulationMotorcyle = motorcycleCosts.CostAnalysis(requestSimulation, roadwayModel.getMotorcycle());
		roadwayResponseDto.simulationTruck = trunkCosts.CostAnalysis(requestSimulation, roadwayModel.getTruck());
		roadwayResponseDto.simulationWalk = walkCosts.CostAnalysis(requestSimulation, roadwayModel.getWalking());
		return roadwayResponseDto;
	}
	

}
