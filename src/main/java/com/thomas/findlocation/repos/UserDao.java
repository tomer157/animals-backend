package com.thomas.findlocation.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thomas.findlocation.entities.Users;

@Repository
public interface UserDao extends CrudRepository<Users, String> {
	Users findByUsername(String username);

}
