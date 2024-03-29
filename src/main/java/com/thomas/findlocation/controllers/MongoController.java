package com.thomas.findlocation.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.Spring;

import org.javatuples.Pair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.thomas.findlocation.entities.ClimateModel;
import com.thomas.findlocation.entities.Foster;
import com.thomas.findlocation.entities.Hazard;
import com.thomas.findlocation.entities.Marker;
import com.thomas.findlocation.entities.RescueEntity;
import com.thomas.findlocation.entities.RescueTuple;
import com.thomas.findlocation.entities.Status;
import com.thomas.findlocation.entities.Users;
import com.thomas.findlocation.repos.*;
import com.thomas.findlocation.weather.WeatherApi;

import service.UserServiceImpl;

@RestController
@RequestMapping("/markers")
@CrossOrigin()
public class MongoController {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private GridFsOperations gridOperations;

	private CustomerRepository repos;
	private HazardRepository hazardRepo;
	private RescueRepository rescueRepos;
	private UserDao userDao;

	@Autowired
	MongoController(CustomerRepository repo, RescueRepository resc, UserDao userDao, HazardRepository hazaRepo) {
		this.repos = repo;
		this.rescueRepos = resc;
		this.userDao = userDao;
		this.hazardRepo = hazaRepo;

	}

	@RequestMapping(value = "/getmarkers", method = RequestMethod.GET)
	public List<Marker> getMarkers() {
		return repos.findAll();
	}

	@RequestMapping(value = "/addmarkers", method = RequestMethod.POST)
	public Marker saveMarker(@RequestBody Marker dataRequest) throws FileNotFoundException {
		return repos.save(dataRequest);

	}

	@RequestMapping(value = "/addhazard", method = RequestMethod.POST)
	public Hazard saveHazard(@RequestBody Hazard dataRequest) throws FileNotFoundException {
		return hazardRepo.save(dataRequest);

	}

	@RequestMapping(value = "/deletehazards", method = RequestMethod.DELETE)
	public void deleteHazards() {
		hazardRepo.deleteAll();
	}

	@RequestMapping(value = "/gethazards", method = RequestMethod.GET)
	public List<Hazard> getHazards() {
		return hazardRepo.findAll();
	}
	
	
	@Async
	@Scheduled(cron = "0 0/5 *  * * ?")
	void scheduleUpdateJob() {
		try {
			List<Marker> list = getMarkers();
			LocalDateTime now = LocalDateTime.now();

			if (now == null || list.isEmpty())
				return;

			for (Marker mark : list) {

				LocalDateTime m = mark.getCurrentDate();
				Long diff = Duration.between(m, now).toMillis();
				int hour = (int) (diff / (60 * 60 * 1000));
				if (Math.abs(hour) >= 6) {
					String mark_id = mark.getId();
					updateNavigate(mark_id, "window.google.maps.Animation.DROP");
				} else if (hour == 0) {
					continue;
				} else {
					continue;
				}
			}

		} catch ( NullPointerException e) {
			e.printStackTrace();

		}
	}
	
	

	@RequestMapping(value = "/addrescue/{status}", method = RequestMethod.POST)
	public RescueEntity saveRescue(@RequestBody RescueEntity dataRequest, @PathVariable("status") Status status,
			@RequestParam("pickup") String param) throws FileNotFoundException {
		RescueEntity res = new RescueEntity();

		res = dataRequest;
		res.setPickupDescription(param);
		res.setStatus(status);
		return rescueRepos.save(res);

	}

	@RequestMapping(value = "/updatenavigation/{id}", method = RequestMethod.PUT)
	public Marker updateNavigate(@PathVariable("id") String id, @Param("status") String status) {

		Calendar calendar = Calendar.getInstance();
		LocalDateTime now = LocalDateTime.now();
		Marker marker = repos.findById(id).get();
		marker.setCurrentDate(now);
		marker.setNavigate_to(status);
		return repos.save(marker);
	}

	@RequestMapping(value = "/getrescued", method = RequestMethod.GET)
	public List<RescueEntity> getAllRescued() {
		List<RescueEntity> list = rescueRepos.findAll();
		return list;
	}

	@RequestMapping(value = "deleterescue", method = RequestMethod.DELETE)
	public void deleteRescue() {
		rescueRepos.deleteAll();
	}

	@RequestMapping(value = "/deleterescue/{id}", method = RequestMethod.DELETE)
	public RescueEntity deleteRescueById(@PathVariable("id") String id) {

		RescueEntity res = rescueRepos.findById(id).get();
		rescueRepos.deleteById(id);
		return res;
	}

	@RequestMapping(value = "/deleterescutofoster/{id}", method = RequestMethod.DELETE)
	public RescueEntity deleteToFoster(@RequestBody Foster dataRequest, @PathVariable("id") String id) {
		RescueEntity res = rescueRepos.findById(id).get();
		rescueRepos.deleteById(id);

		restTemplate.exchange("http://127.0.0.1:8081/api/fosters/savefoster", HttpMethod.POST, null,
				dataRequest.getClass());

		return res;
	}

	@RequestMapping(value = "/deletemarkers/{id}", method = RequestMethod.DELETE)
	public Marker deleteMarker(@PathVariable("id") String id) {

		Marker marker = repos.findById(id).get();
		repos.deleteById(id);
		return marker;
	}

	@RequestMapping(value = "deleteall", method = RequestMethod.DELETE)
	public void deleteMarkers() {
		repos.deleteAll();

	}

	@RequestMapping(value = "/weatherapi", method = RequestMethod.GET)
	public ClimateModel getWeather() {
		ClimateModel c = new ClimateModel();
		try {
			c = WeatherApi.parseWith();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	@RequestMapping(value = "/getuser/{user}", method = RequestMethod.GET)
	public String getUser(@PathVariable("user") String user, @RequestParam("password") String password) {

		Users userId = userDao.findByUsername(user);

		if (userId.getPassword().equals(password)) {
			return userId.getRole();
		} else {
			throw new ResourceNotFoundException();
		}

	}
}
