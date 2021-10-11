package com.packsendme.roadway.simulation.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.MongoClientException;
import com.packsendme.roadway.commons.response.SimulationRoadwayResponse;
import com.packsendme.roadway.simulation.repository.IRoadway_Repository;

@Repository
@Transactional
public class SimulationDBImpl_Dao implements ICrud<SimulationRoadwayResponse> {

	@Autowired
	IRoadway_Repository simulation_Rep;

	@Override
	public SimulationRoadwayResponse findOne(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimulationRoadwayResponse save(SimulationRoadwayResponse entity) {
		try {
			return entity = simulation_Rep.insert(entity);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<SimulationRoadwayResponse> findAll() {
		try {
			return simulation_Rep.findAll();
		}
		catch (MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(String id) {
		try {
			simulation_Rep.deleteById(id);
		}
		catch (MongoClientException e) {
			e.printStackTrace();
		}
	} 

}
