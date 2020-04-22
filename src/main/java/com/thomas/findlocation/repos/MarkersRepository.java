package com.thomas.findlocation.repos;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thomas.findlocation.entities.Marker;

public interface MarkersRepository extends JpaRepository<Marker, Integer> {

}
