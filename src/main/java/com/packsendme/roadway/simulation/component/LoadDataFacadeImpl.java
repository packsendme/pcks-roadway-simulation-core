package com.packsendme.roadway.simulation.component;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.packsendme.lib.common.constants.generic.MetricUnitMeasurement_Constants;
import com.packsendme.lib.common.exchange.Exchange;
import com.packsendme.lib.common.response.dto.api.GoogleAPITrackingResponse_Dto;
import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest;
import com.packsendme.lib.roadway.simulation.request.SimulationRoadwayRequest_Dto;
import com.packsendme.roadbrewa.entity.Roadway;
import com.packsendme.roadway.simulation.controller.IExchangeRate_Client;
import com.packsendme.roadway.simulation.controller.IGoogleAPI_Client;
import com.packsendme.roadway.simulation.dao.RoadwayCacheImpl_Dao;

@Component
@EnableFeignClients(basePackages="com.packsendme.roadway.simulation.controller")
@ComponentScan("com.packsendme.roadway.simulation.config")
public class LoadDataFacadeImpl implements IRoadway {
	
	@Autowired
	private IGoogleAPI_Client googleClient;
	
	@Autowired
	private IExchangeRate_Client exchangeClient;

	@Autowired
	private RoadwayParserData roadwayParserData;
	
	@Autowired
	private RoadwayCacheImpl_Dao roadwayCacheDAO;

	
	@Override
	public SimulationRoadwayRequest_Dto getData(SimulationRoadwayRequest simulationData, Map header) {
		try {
			//(1) LOAD INSTANTE API - GoogleMaps-API
			ResponseEntity<?> googleAPIResponse = getGoogleMap_API(header, simulationData);
			GoogleAPITrackingResponse_Dto googleTrackingAPI = roadwayParserData.getParseRoadwayResponseAPI(googleAPIResponse);
	
			//(2) LOAD INSTANTE CACHE RULE BRE-TRANSPORT_TYPE
			System.out.println("");
			System.out.println("======================================");
			System.out.println("type_transport "+simulationData.type_transport);
			System.out.println("======================================");
			
			Roadway roadwayRuleBRE = getTransport_BRE(simulationData.type_transport);
			String jsonObject = roadwayRuleBRE.toString();
			System.out.println("");
			System.out.println("======================================");
			System.out.println("Roadway BRE "+ jsonObject);
			System.out.println("======================================");

			
			//(3) LOAD INSTANTE CACHE RULE ANTT - TABLE
/*			if (roadwayRuleBRE.tariffPlan.antt_plan == true){
				getAntt_BRE();
			}*/
					
			//(4) LOAD INSTANTE EXCHANGE :: CACHE OR API
			System.out.println("");
			System.out.println("======================================");
			System.out.println("isoCurrencyCode "+header.get("isoCurrencyCode").toString());
			System.out.println("======================================");
			
			
			ResponseEntity<?> exchangeAPIResponse = getRateExchange(header, header.get("isoCurrencyCode").toString());
			Exchange exchangeObj = roadwayParserData.getParseExchangeResponse(exchangeAPIResponse);
			
			SimulationRoadwayRequest_Dto simulationData_Obj = new SimulationRoadwayRequest_Dto(
					simulationData.address_origin, simulationData.address_destination, simulationData.type_transport, simulationData.product_transport, 
					simulationData.people, simulationData.weight_max, simulationData.unity_weight, simulationData.height_max, simulationData.width_max, 
					simulationData.length_max, simulationData.delivery_type, header.get("isoLanguageCode").toString(), header.get("isoCountryCode").toString(),  
					exchangeObj, googleTrackingAPI, roadwayRuleBRE);
			return simulationData_Obj;
		}
		catch (Exception e) {
			e.printStackTrace();	
			return null;
		}
	}

	/* -----  L O A D s  -  D A T A  ------- */
	
	@Override
	public ResponseEntity<?> getGoogleMap_API(Map header, SimulationRoadwayRequest simulationData) {
		try {
			System.out.println("");
			System.out.println("======================================");
			System.out.println("address_origin "+simulationData.address_origin);
			System.out.println("address_destination "+simulationData.address_destination);
			System.out.println("unity_weight "+ MetricUnitMeasurement_Constants.kilometro_UnitMeasurement);
			System.out.println("======================================");
			System.out.println("");

			return googleClient.getTracking(header.get("isoLanguageCode").toString(), header.get("isoCountryCode").toString(),
					header.get("isoCurrencyCode").toString(),header.get("originApp").toString(),simulationData.address_origin,
					simulationData.address_destination, MetricUnitMeasurement_Constants.kilometro_UnitMeasurement);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
 		}
	}

 	@Override
	public Roadway getTransport_BRE(String key) {
		try {
			return roadwayCacheDAO.findOne(key);
		} catch (Exception e) {
			e.printStackTrace();
 		}
		return null;
	}
	
	
	@Override
	public ResponseEntity<?> getRateExchange(Map header, String current){
		try {
			return exchangeClient.getExchange(header.get("isoLanguageCode").toString(), header.get("isoCountryCode").toString(),
					header.get("isoCurrencyCode").toString(),header.get("originApp").toString(), current);
		} catch (Exception e) {
			e.printStackTrace();
 		}
		return null;
	}

	@Override
	public ResponseEntity<?> getAntt_BRE(){
		try {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
 		}
		return null;
	}


}
