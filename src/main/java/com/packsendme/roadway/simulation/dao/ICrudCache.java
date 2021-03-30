package com.packsendme.roadway.simulation.dao;

public interface ICrudCache<T> {
	
	public T findOne(String value);

}
