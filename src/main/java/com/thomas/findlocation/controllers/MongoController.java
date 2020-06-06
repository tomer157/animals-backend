package com.thomas.findlocation.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.thomas.findlocation.entities.Marker;
import com.thomas.findlocation.repos.*;
import com.thomas.findlocation.weather.WeatherApi;

@RestController
@RequestMapping("/markers")
@CrossOrigin()
public class MongoController {
	@Autowired
	private GridFsOperations gridOperations;

	private CustomerRepository repos;

	@Autowired
	MongoController(CustomerRepository repo) {
		this.repos = repo;

	}

	@RequestMapping(value = "/getmarkers", method = RequestMethod.GET)
	public List<Marker> getMarkers() {
		return repos.findAll();
	}

	String fileId = "";

	public String saveFile() throws FileNotFoundException {
		// defaine metadata
		DBObject metadata = new BasicDBObject();
		metadata.put("organization", "java");

		InputStream inputStream = new FileInputStream(
				"C:\\dev\\findLocation\\herelocation api\\images\\animalIcon.png");
		metadata.put("type", "image");

		// store to mongodb

		fileId = gridOperations.store(inputStream, "image/png", metadata).get().toString();

		System.out.println("file id stored: " + fileId);

		return "file stored succefully";

	}

	@RequestMapping(value = "/addmarkers", method = RequestMethod.POST)
	public Marker saveMarker(@RequestBody Marker dataRequest) throws FileNotFoundException {
//		String file = saveFile();
//		dataRequest.setFile(file);
		return repos.save(dataRequest);

	}

	@RequestMapping(value = "/deletemarkers/{id}", method = RequestMethod.DELETE)
	public Marker deleteMarker(@PathVariable("id") String id) {

		
		Marker marker = repos.findById(id).get();
		repos.deleteById(id);
		return marker;
	}
	
	
	
	@RequestMapping(value="/weatherapi",method = RequestMethod.GET)
	public Pair getWeather() {
		Pair<Double , Double>pair = null;
		try {
			pair =WeatherApi.parseWith();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pair;
	}
}
