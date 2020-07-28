package com.packsendme.microservice.roadway.component;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.packsendme.exchange.bre.model.ExchangeBRE_Model;
import com.packsendme.financecostdelivery.bre.model.FinanceCostDeliveryBRE_Model;
import com.packsendme.lib.common.constants.generic.HttpExceptionPackSend;
import com.packsendme.lib.common.response.Response;
import com.packsendme.lib.common.response.dto.api.GoogleAPITrackingResponse_Dto;
import com.packsendme.roadway.bre.rule.model.RoadwayBRE_Model;
import com.packsendme.truck.bre.model.TruckBRE_Model;

@Component
public class RoadwayParserData_Component {

	Gson gson = new Gson();
	
	public GoogleAPITrackingResponse_Dto getParseRoadwayResponseAPI(ResponseEntity<?> googleAPIResponse) {
		GoogleAPITrackingResponse_Dto trackingAPI_Dto = null;
		try{
			if(googleAPIResponse.getStatusCode() == HttpStatus.ACCEPTED) {
					String jsonPayload = googleAPIResponse.getBody().toString();
					Response<Object> response = gson.fromJson(jsonPayload, Response.class);
					if(response.getResponseCode() == HttpExceptionPackSend.GOOGLEAPI_PLACE.value()) {
						System.out.println(" MY OBJECT  "+ response.getBody().toString());
						String jsonObject = response.getBody().toString();
						trackingAPI_Dto = gson.fromJson(jsonObject, GoogleAPITrackingResponse_Dto.class);
						System.out.println(" ");
						System.out.println(" ");
						System.out.println("===============================================================================");
						System.out.println("GOOGLE API - MAP "+ trackingAPI_Dto.trackingRoadway.size());
						System.out.println("GOOGLE API - trackingAPI_STATUS  "+ trackingAPI_Dto.status);
						System.out.println("===============================================================================");
						System.out.println(" ");
						System.out.println(" ");
					}
			}
			return trackingAPI_Dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Parse Response HTTP the service BussinesRuleManager
	public RoadwayBRE_Model getParseRoadwayResponseCache(ResponseEntity<?> cacheResponse) {
		RoadwayBRE_Model roadwayBRE_Dto = null;
		try{
			if(cacheResponse.getStatusCode() == HttpStatus.ACCEPTED) {
					String jsonPayload = cacheResponse.getBody().toString();
					Response<Object> response = gson.fromJson(jsonPayload, Response.class);
					if(response.getResponseCode() == HttpExceptionPackSend.FOUND_BUSINESS_RULE.value()) {
						System.out.println(" MY OBJECT  "+ response.getBody().toString());
						String jsonObject = response.getBody().toString();
						roadwayBRE_Dto = gson.fromJson(jsonObject, RoadwayBRE_Model.class);
						System.out.println(" ");
						System.out.println(" ");
						System.out.println("===============================================================================");
						System.out.println("RoadwayBRE - name "+ roadwayBRE_Dto.name_rule);
						System.out.println("RoadwayBRE - roadwayBRE_Dto  "+ roadwayBRE_Dto.status);
						System.out.println("===============================================================================");
						System.out.println(" ");
						System.out.println(" ");
					}
			}
			return roadwayBRE_Dto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	// Parse Response HTTP the service FinanceCostDeliveryBRE (Percentual Costs to PakcSendMe)
	public FinanceCostDeliveryBRE_Model getParseFinanceCostDeliveryResponseCache(ResponseEntity<?> cacheResponse) {
		FinanceCostDeliveryBRE_Model packSendFinanceCostBRE = null;
		try{
			if(cacheResponse.getStatusCode() == HttpStatus.ACCEPTED) {
					String jsonPayload = cacheResponse.getBody().toString();
					Response<Object> response = gson.fromJson(jsonPayload, Response.class);
					if(response.getResponseCode() == HttpExceptionPackSend.FOUND_BUSINESS_RULE.value()) {
						System.out.println(" MY OBJECT  "+ response.getBody().toString());
						String jsonObject = response.getBody().toString();
						packSendFinanceCostBRE = gson.fromJson(jsonObject, FinanceCostDeliveryBRE_Model.class);
						System.out.println(" ");
						System.out.println(" ");
						System.out.println("===============================================================================");
						System.out.println("RoadwayBRE - name "+ packSendFinanceCostBRE.percentage_packsend);
						System.out.println("RoadwayBRE - roadwayBRE_Dto  "+ packSendFinanceCostBRE.status);
						System.out.println("===============================================================================");
						System.out.println(" ");
						System.out.println(" ");
					}
			}
			return packSendFinanceCostBRE;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Parse Response HTTP the service ExchangeRateBRE 
	public ExchangeBRE_Model getParseExchangeResponseCache(ResponseEntity<?> cacheResponse) {
		ExchangeBRE_Model exchangeBRE = null;
		try{
			if(cacheResponse.getStatusCode() == HttpStatus.ACCEPTED) {
				String jsonPayload = cacheResponse.getBody().toString();
				Response<Object> response = gson.fromJson(jsonPayload, Response.class);
				if(response.getResponseCode() == HttpExceptionPackSend.FOUND_EXCHANGE.value()) {
					System.out.println(" MY OBJECT  "+ response.getBody().toString());
					String jsonObject = response.getBody().toString();
				}
			}
			return exchangeBRE;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// Parse Response HTTP the service TruckBRE 
	public TruckBRE_Model getParseTruckResponseCache(ResponseEntity<?> cacheResponse) {
		TruckBRE_Model truckBRE = null;
			try{
				if(cacheResponse.getStatusCode() == HttpStatus.ACCEPTED) {
					String jsonPayload = cacheResponse.getBody().toString();
					Response<Object> response = gson.fromJson(jsonPayload, Response.class);
					if(response.getResponseCode() == HttpExceptionPackSend.FOUND_BUSINESS_RULE.value()) {
						System.out.println(" MY OBJECT  "+ response.getBody().toString());
						String jsonObject = response.getBody().toString();
					}
				}
				return truckBRE;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	

	
}
