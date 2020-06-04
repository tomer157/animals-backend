package com.thomas.findlocation.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.thomas.findlocation.entities.Marker;


public interface CustomerRepository extends MongoRepository<Marker, String> {
//	@Query(value="{'_id' : $0}", delete = true)
	public void deleteById (String id);
}
