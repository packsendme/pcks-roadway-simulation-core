package com.packsendme.microservice.sa.roadway.component.rule;

public interface ICosts<S,R,B> {
	
	public S CostAnalysis(R r, B b);
	
	public Double generateCost(R r, B b);
	

}
