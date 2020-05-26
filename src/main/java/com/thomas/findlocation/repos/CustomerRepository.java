package com.thomas.findlocation.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.thomas.findlocation.entities.Marker;


public interface CustomerRepository extends MongoRepository<Marker, String> {

}
