package com.packsendme.roadway.simulation.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.packsendme.roadbrewa.entity.Roadway;
import com.packsendme.roadway.simulation.config.Redis_Config;

@Repository
@Transactional
public class RoadwayCacheImpl_Dao implements ICrud<Roadway>{
	
	@Autowired(required=true)
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private Redis_Config cacheConfig;
	

	@Override
	public Roadway findOne(String hashKey) {
		try {
			return (Roadway) redisTemplate.opsForHash().get(cacheConfig.NAME_CACHE, hashKey);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public Roadway save(Roadway entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Roadway> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(Roadway entity) {
		// TODO Auto-generated method stub
		
	}

 
}
