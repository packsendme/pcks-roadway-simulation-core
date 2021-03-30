package com.packsendme.roadway.simulation.dto;

import com.packsendme.lib.common.response.dto.api.GoogleAPITrackingResponse_Dto;
import com.packsendme.roadbrewa.entity.Roadway;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoadData_Dto {
	
	public GoogleAPITrackingResponse_Dto googleTracking;
	public Roadway roadwayRule;
	
	public LoadData_Dto(GoogleAPITrackingResponse_Dto googleTracking, Roadway roadwayRule) {
		super();
		this.googleTracking = googleTracking;
		this.roadwayRule = roadwayRule;
	}

}
