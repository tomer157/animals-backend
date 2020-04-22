package com.thomas.findlocation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thomas.findlocation.dto.MarkerDataRequest;
import com.thomas.findlocation.entities.Marker;

import com.thomas.findlocation.repos.MarkersRepository;

@RestController
@RequestMapping("/marker")
@CrossOrigin()
public class locationController {
	private MarkersRepository repository;

	@Autowired
	locationController(MarkersRepository repo) {
		this.repository = repo;

	}

	@RequestMapping(value = "/addmarker", method = RequestMethod.POST)
	public Marker saveMarker(@RequestBody Marker dataRequest) {

		return repository.save(dataRequest);

	}

	@RequestMapping(value = "/getmarkers", method = RequestMethod.GET)
	public List<Marker> getMarkers() {
		return repository.findAll();
	}

	@RequestMapping(value = "/getmarker/{id}", method = RequestMethod.GET)
	public Marker getMarker(@PathVariable("id") int id) {
		Marker marker = repository.findById(id).get();

		return marker;  
	}

	@RequestMapping(value = "/deleteall", method = RequestMethod.DELETE)
	public void deleteAll() {
		repository.deleteAll();
	}
	

}
