package com.packsendme.roadway.simulation.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CargoDistribution_Dto {

	public String bodyWork;
	public int axes;
	public String typeCargo;
	
	
	public CargoDistribution_Dto(String bodyWork, int axes, String typeCargo) {
		super();
		this.bodyWork = bodyWork;
		this.axes = axes;
		this.typeCargo = typeCargo;
	}


	public CargoDistribution_Dto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
