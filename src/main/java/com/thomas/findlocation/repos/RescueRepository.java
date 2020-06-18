package com.thomas.findlocation.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thomas.findlocation.entities.Marker;
import com.thomas.findlocation.entities.RescueEntity;

public interface RescueRepository extends MongoRepository<RescueEntity, String> {
	public void deleteById(String id);
}
