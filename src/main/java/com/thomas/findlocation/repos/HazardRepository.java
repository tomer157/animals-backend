package com.thomas.findlocation.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thomas.findlocation.entities.Hazard;



public interface HazardRepository extends MongoRepository<Hazard, String> {
	public void deleteById(String id);

  } 


