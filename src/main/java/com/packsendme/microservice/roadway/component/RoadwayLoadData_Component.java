package com.packsendme.microservice.roadway.component;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.packsendme.exchange.bre.model.ExchangeBRE_Model;
import com.packsendme.financecostdelivery.bre.model.FinanceCostDeliveryBRE_Model;
import com.packsendme.lib.common.constants.generic.MetricUnitMeasurement_Constants;
import com.packsendme.lib.common.response.dto.api.GoogleAPITrackingResponse_Dto;
import com.packsendme.lib.simulation.http.SimulationDataForCalculateRequest_Dto;
import com.packsendme.lib.simulation.http.SimulationRequest_Dto;
import com.packsendme.lib.utility.WeightConvert_Utility;
import com.packsendme.microservice.roadway.config.Cache_Config;
import com.packsendme.microservice.roadway.controller.IBusinessManager_SA_Client;
import com.packsendme.microservice.roadway.controller.IExchangeRate_Client;
import com.packsendme.microservice.roadway.controller.IGoogleAPI_Client;
import com.packsendme.microservice.roadway.dto.LoadDataSouthAmerica_Dto;
import com.packsendme.roadway.bre.rule.model.RoadwayBRE_Model;

@Component
@EnableFeignClients(basePackages="com.packsendme.microservice.roadway.controller")
@ComponentScan("com.packsendme.microservice.roadway.config")
public class RoadwayLoadData_Component {
	
	@Autowired
	private IGoogleAPI_Client googleClient;

	@Autowired
	private IBusinessManager_SA_Client businessRule_Client;
	
	@Autowired
	private IExchangeRate_Client exchangeRate_Client;
	
	@Autowired
	private RoadwayParserData_Component roadwayParserData;
	
	WeightConvert_Utility weightUtility = new WeightConvert_Utility(); 
			
	@Autowired
	private Cache_Config cache;

	
	public LoadDataSouthAmerica_Dto getDataSouthAmerica(SimulationRequest_Dto simulationData, Map header) {
		SimulationDataForCalculateRequest_Dto simulationReqCustomer_dto = null;
		
		try {
			//(1) Instance Google-API
			ResponseEntity<?> googleAPIResponse = googleClient.getTracking(header.get("isoLanguageCode").toString(), header.get("isoCountryCode").toString(),
					header.get("isoCurrencyCode").toString(),header.get("originApp").toString(),simulationData);
			
			GoogleAPITrackingResponse_Dto simulationGoogleAPI = roadwayParserData.getParseRoadwayResponseAPI(googleAPIResponse);
	
			//(2) Instance Roadway-Cache  BusinessManager/Rule
			ResponseEntity<?> roadayCacheResponse = businessRule_Client.getRoadwayBRE_SA(header.get("isoLanguageCode").toString(), header.get("isoCountryCode").toString(),
					header.get("isoCurrencyCode").toString(),header.get("originApp").toString(),cache.roadwayBRE_SA);
			RoadwayBRE_Model roadwayBRE = roadwayParserData.getParseRoadwayResponseCache(roadayCacheResponse);
			
			//(2.1) Instance PackSendPercentage-Cache  BusinessManager/Rule
			ResponseEntity<?> financeCacheResponse = businessRule_Client.getFinanceCostDeliveryBRE_SA(header.get("isoLanguageCode").toString(), header.get("isoCountryCode").toString(),
					header.get("isoCurrencyCode").toString(),header.get("originApp").toString(),cache.financeCostDeliveryBRE_SA);
			FinanceCostDeliveryBRE_Model packSendPercentage = roadwayParserData.getParseFinanceCostDeliveryResponseCache(financeCacheResponse);
	
			// (3) Instance RateExchange-API
			ResponseEntity<?> exchangeResponse = exchangeRate_Client.getExchange(header.get("isoCurrencyCode").toString());
			ExchangeBRE_Model exchangeBRE = roadwayParserData.getParseExchangeResponseCache(exchangeResponse);
	
			
			// (4) Convert Weight in Gram Unit Measurement
			double weight_productGR = 0.0;
			
			if(simulationData.unity_measurement_weight.equals(MetricUnitMeasurement_Constants.tonelada_UnitMeasurement)) {
				weight_productGR = weightUtility.ToneladaToGrama(simulationData.weight_product);
			}
			else if(simulationData.unity_measurement_weight.equals(MetricUnitMeasurement_Constants.kilograma_UnitMeasurement)) {
				weight_productGR = weightUtility.kilogramoToGrama(simulationData.weight_product);
			}
			
			simulationReqCustomer_dto = new SimulationDataForCalculateRequest_Dto(
					simulationData.weight_product, 
					weight_productGR,
					simulationData.unity_measurement_weight, 
					simulationData.type_delivery, 
					header.get("isoLanguageCode").toString(), 
					header.get("isoCountryCode").toString(), 
					exchangeBRE.value, 
					packSendPercentage.percentage_packsend, 
					roadwayBRE);
			
			LoadDataSouthAmerica_Dto loadDataSouthAmerica_Dto = new LoadDataSouthAmerica_Dto(simulationGoogleAPI,simulationReqCustomer_dto);
			return loadDataSouthAmerica_Dto;
		}
		catch (Exception e) {
			e.printStackTrace();	
			return null;
		}
	}

}
