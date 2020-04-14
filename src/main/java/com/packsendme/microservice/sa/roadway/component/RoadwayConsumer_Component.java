package com.packsendme.microservice.sa.roadway.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packsendme.roadway.bre.rule.model.RoadwayBRE_Model;

@Component
public class RoadwayConsumer_Component implements IConsumer_Component<Object> {

	private String msg;
	
	@KafkaListener(topics = "${kafka.topic.roadwayBRE}")
	public void receive(String data) {
		this.msg = data;
		System.out.println(" ------------------------------- ");
		System.out.println(" topic_roadway_sa "+ data);
		System.out.println(" ------------------------------- ");
	}

	@Override
	public Object consumerTopic() {
 		RoadwayBRE_Model roadway = null;
		try {
			if(!msg.equals(null)) {
				roadway = new ObjectMapper().readValue(msg, RoadwayBRE_Model.class);
			}
			return roadway;
		}
		catch (Exception e) {
			System.out.println(" ------------------------------- ");
			System.out.println(" topic_roadway_sa "+ e);
			System.out.println(" ------------------------------- ");
			return null;
		}
	}
	

}
