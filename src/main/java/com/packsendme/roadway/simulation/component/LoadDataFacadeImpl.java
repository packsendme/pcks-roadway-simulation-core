package com.packsendme.roadway.simulation.component;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.packsendme.lib.common.response.dto.api.GoogleAPITrackingResponse_Dto;
import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest_Dto;
import com.packsendme.roadbrewa.entity.Roadway;
import com.packsendme.roadway.simulation.controller.IGoogleAPI_Client;
import com.packsendme.roadway.simulation.dao.RoadwayCacheImpl_Dao;
import com.packsendme.roadway.simulation.dto.LoadData_Dto;

@Component
@EnableFeignClients(basePackages="com.packsendme.microservice.roadway.controller")
@ComponentScan("com.packsendme.microservice.roadway.config")
public class LoadDataFacadeImpl implements IRoadway {
	
	@Autowired
	private IGoogleAPI_Client googleClient;

	@Autowired
	private RoadwayParserData roadwayParserData;
	
	@Autowired
	private RoadwayCacheImpl_Dao roadwayCacheDAO;

	
	@Override
	public LoadData_Dto getData(SimulationRoadwayRequest_Dto simulationData, Map header) {
		try {
			//(1) LOAD INSTANTE - GoogleMaps-API
			ResponseEntity<?> googleAPIResponse = getGoogleMap_API(header, simulationData);
			GoogleAPITrackingResponse_Dto simulationGoogleAPI = roadwayParserData.getParseRoadwayResponseAPI(googleAPIResponse);
	
			//(2) LOAD INSTANTE CACHE RULE BRE-TRANSPORT_TYPE
			Roadway roadwayRuleBRE = getTransport_BRE(simulationData.type_transport);
			LoadData_Dto loadDataDto_Obj = new LoadData_Dto(simulationGoogleAPI, roadwayRuleBRE);
			return loadDataDto_Obj;
		}
		catch (Exception e) {
			e.printStackTrace();	
			return null;
		}
	}

	
	/* -----  L O A D s  -  D A T A  ------- */
	
	@Override
	public ResponseEntity<?> getGoogleMap_API(Map header, SimulationRoadwayRequest_Dto simulation_dto) {
		return googleClient.getTracking(header.get("isoLanguageCode").toString(), header.get("isoCountryCode").toString(),
				header.get("isoCurrencyCode").toString(),header.get("originApp").toString(),simulation_dto.address_origin,
				simulation_dto.address_destination, simulation_dto.unity_weight);
	}

	// Load Rule Transport Type
	@Override
	public Roadway getTransport_BRE(String key) {
		try {
			return roadwayCacheDAO.findOne(key);
		} catch (Exception e) {
			e.printStackTrace();
 		}
		return null;
	}
	
	


}
